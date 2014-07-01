package cz.ondraster.oilcraft.entities;

import cz.ondraster.oilcraft.FluidTank;
import cz.ondraster.oilcraft.pipe.PipeFluidNetwork;
import cz.ondraster.oilcraft.pipe.PipeNode;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class EntityPipe extends TileEntity implements IFluidHandler {
   public int connections = 0;
   public boolean hasDr = false;
   FluidTank tank;
   PipeFluidNetwork network = null;
   private int drX;
   private int drY;
   private int drZ;

   public EntityPipe() {
      tank = new FluidTank(2000);
   }

   public PipeFluidNetwork getNetworkDr() {
      if (xCoord == drX && yCoord == drY && zCoord == drZ)
         return this.network;

      return ((EntityPipe) worldObj.getTileEntity(drX, drY, drZ)).getNetworkDr();
   }

   @Override
   public void readFromNBT(NBTTagCompound tag) {
      super.readFromNBT(tag);
      connections = tag.getInteger("connections");
      tank.load(tag);
   }

   @Override
   public void writeToNBT(NBTTagCompound tag) {
      super.writeToNBT(tag);
      tag.setInteger("connections", connections);
      tank.save(tag);
   }

   @Override
   public void updateEntity() {
      /*if (tank.getFluidAmount() > 0) {
         if (tank.getFluid() == null || tank.getFluid().getFluid() == null) {
            Helper.logWarn("Cleared " + tank.getFluidAmount() + " of NULL (!!) fluid! Prevented crash...");
            tank.safeDrain();
         }
         for (int i = 0; i < OrientationSimple.Directions; i++) {
            if ((connections & (1 << i)) != 0 && tank.getFluidAmount() > 0) {
               IFluidHandler handler = (IFluidHandler) worldObj.getTileEntity(xCoord + OrientationSimple.getX(i), yCoord + OrientationSimple.getY(i), zCoord + OrientationSimple.getZ(i));
               if (handler == null)
                  continue;
               if (handler.canFill(ForgeDirection.getOrientation(OrientationSimple.getOpposite(i)), tank.getFluid().getFluid())) {
                  int amount = handler.fill(ForgeDirection.getOrientation(OrientationSimple.getOpposite(i)), tank.getFluid(), true);
                  this.tank.drain(amount, true);
               }
            }
         }
      }*/
   }


   public void changeState(int side, boolean nState) {
      connections &= ~(1 << side);
      if (nState)
         connections |= (1 << side);
   }

   public boolean isConnected(int side) {
      return (connections & (1 << side)) != 0;
   }

   @Override
   public Packet getDescriptionPacket() {
      NBTTagCompound nbtTagCompound = new NBTTagCompound();
      this.writeToNBT(nbtTagCompound);
      return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, nbtTagCompound);
   }

   @Override
   public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
      readFromNBT(pkt.func_148857_g());
   }


   @Override
   public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
      int filled = tank.fill(resource, doFill);
      if (doFill && filled > 0) {
         getNetworkDr().fill(new FluidStack(resource.getFluid(), filled), true);
         getNetworkDr().rebalance(worldObj);
      }
      return filled;

   }

   @Override
   public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
      FluidStack drained = tank.drain(resource.amount, doDrain);
      if (doDrain && drained.amount > 0) {
         getNetworkDr().drain(drained.amount, true);
         getNetworkDr().rebalance(worldObj);
      }
      return drained;
   }

   @Override
   public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
      FluidStack drained = tank.drain(maxDrain, doDrain);
      if (doDrain && drained.amount > 0) {
         getNetworkDr().drain(drained.amount, true);
         getNetworkDr().rebalance(worldObj);
      }
      return drained;

   }

   @Override
   public boolean canFill(ForgeDirection from, Fluid fluid) {
      if (tank.getFluid() == null || tank.getFluid().getFluid() == null)
         return true;

      return tank.getFluid().getFluid() == fluid;
   }

   @Override
   public boolean canDrain(ForgeDirection from, Fluid fluid) {
      return tank.getFluidAmount() > 0 && tank.getFluid().getFluid() == fluid;
   }

   @Override
   public FluidTankInfo[] getTankInfo(ForgeDirection from) {
      return new FluidTankInfo[]{tank.getInfo()};
   }

   public void setFluidParams(Fluid storedFluid, int i) {
      this.tank.safeDrain();
      this.tank.fill(new FluidStack(storedFluid, i), true);
   }

   public void updateNeighbour() {
      if (this.hasDr)
         return;

      for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
         TileEntity tileEntity = worldObj.getTileEntity(xCoord + direction.offsetX, yCoord + direction.offsetY, zCoord + direction.offsetZ);
         if (tileEntity instanceof EntityPipe) {
            EntityPipe pipe = (EntityPipe) tileEntity;
            if (!pipe.hasDr) {
               if (getNetworkDr().addNode(new PipeNode(xCoord, yCoord, zCoord, tank.getCapacity(), tank.getFluidAmount()), worldObj)) {
                  pipe.drX = this.drX;
                  pipe.drY = this.drY;
                  pipe.drZ = this.drZ;
                  pipe.hasDr = true;
               }
            } else if (pipe.hasDr && getNetworkDr() != pipe.getNetworkDr()) {
               // try joining the Drs
            }
         }
      }

      if (!this.hasDr) {
         this.drX = xCoord;
         this.drY = yCoord;
         this.drZ = zCoord;
         this.hasDr = true;
         this.network = new PipeFluidNetwork();
         this.network.addNode(new PipeNode(xCoord, yCoord, zCoord, tank.getCapacity(), tank.getFluidAmount()), worldObj);
      }
   }

   public void setDr(int x, int y, int z) {

   }

   public void becomeDr(PipeFluidNetwork pipeFluidNetwork) {
      this.network = pipeFluidNetwork;
   }
}
