package cz.ondraster.oilcraft.entities;

import cz.ondraster.oilcraft.Helper;
import cz.ondraster.oilcraft.fluids.FluidTank;
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

import java.util.ArrayList;

public class EntityPipe extends TileEntity implements IFluidHandler {
   public static final int MAXMOVED = 500; // 500mB/10 ticks
   public int connections = 0;
   //public boolean hasDr = false;
   FluidTank tank;
   ForgeDirection fluidSourceSide;
   //PipeFluidNetwork network = null;
   //private int drX;
   //private int drY;
   //private int drZ;
   int ticksSinceLastMove = 0;
   private boolean sucking;

   public EntityPipe() {
      tank = new FluidTank(2000);
   }

  /* public PipeFluidNetwork getNetworkDr() {
      if (xCoord == drX && yCoord == drY && zCoord == drZ)
         return this.network;

      return ((EntityPipe) worldObj.getTileEntity(drX, drY, drZ)).getNetworkDr();
   }*/

   @Override
   public void readFromNBT(NBTTagCompound tag) {
      super.readFromNBT(tag);
      connections = tag.getInteger("connections");
      fluidSourceSide = ForgeDirection.getOrientation(tag.getInteger("fluidSourceSide"));
      ticksSinceLastMove = tag.getInteger("ticks");
      sucking = tag.getBoolean("sucking");
      tank.load(tag);
   }

   @Override
   public void writeToNBT(NBTTagCompound tag) {
      super.writeToNBT(tag);
      tag.setInteger("connections", connections);
      if (fluidSourceSide != null)
         tag.setInteger("fluidSourceSide", fluidSourceSide.ordinal());
      tag.setInteger("ticks", ticksSinceLastMove);
      tag.setBoolean("sucking", sucking);
      tank.save(tag);
   }

   @Override
   public void updateEntity() {
      //if (tank.getFluidAmount() > 0)
      ticksSinceLastMove++;

      if (ticksSinceLastMove > 20) {
         if (tank.getFluidAmount() > 0) {
            if (tank.getFluid() == null || tank.getFluid().getFluid() == null) {
               Helper.logWarn("Cleared " + tank.getFluidAmount() + " of NULL (!!) fluid! Prevented crash...");
               tank.safeDrain();
            }

            ArrayList<ForgeDirection> possibleDirs = new ArrayList<ForgeDirection>();

            for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
               if (dir != fluidSourceSide) {
                  if ((connections & (1 << dir.ordinal())) != 0 && tank.getFluidAmount() > 0) {
                     IFluidHandler handler = (IFluidHandler) worldObj.getTileEntity(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ);
                     /*if (!(handler instanceof EntityPipe) && worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord))
                        continue;                                                                                              */
                     if (handler == null)
                        continue;
                     if (handler.canFill(dir.getOpposite(), tank.getFluid().getFluid()) && (handler.fill(dir.getOpposite(), tank.getFluid(), false) > 0)) {
                        possibleDirs.add(dir);
                     }
                  }
               }
            }

            if (possibleDirs.size() == 0) {
               // send it back?
               if ((connections & (1 << fluidSourceSide.ordinal())) != 0) {
                  IFluidHandler handler = (IFluidHandler) worldObj.getTileEntity(xCoord + fluidSourceSide.offsetX, yCoord + fluidSourceSide.offsetY, zCoord + fluidSourceSide.offsetZ);
                  if (handler == null)
                     return; // wat? Safety check for random NULLs
                  if (handler.canFill(fluidSourceSide.getOpposite(), tank.getFluid().getFluid())) {
                     FluidStack maxMove = new FluidStack(tank.getFluid().getFluid(), Math.min(tank.getFluidAmount(), MAXMOVED));
                     int amount = handler.fill(fluidSourceSide.getOpposite(), maxMove, true);
                     this.tank.drain(amount, true);
                  }
               }
            } else {
               FluidStack fluidStack = new FluidStack(tank.getFluid(), Math.min(tank.getFluidAmount(), MAXMOVED) / possibleDirs.size());

               for (int i = 0; i < possibleDirs.size(); i++) {
                  IFluidHandler handler = (IFluidHandler) worldObj.getTileEntity(xCoord + possibleDirs.get(i).offsetX, yCoord + possibleDirs.get(i).offsetY, zCoord + possibleDirs.get(i).offsetZ);


                  int amount = 0;
                  if (i < possibleDirs.size() - 1)
                     amount = handler.fill(possibleDirs.get(i).getOpposite(), fluidStack, true);
                  else
                     amount = handler.fill(possibleDirs.get(i).getOpposite(), tank.getFluid(), true);
                  this.tank.drain(amount, true);
                  //possibleDirs.remove(i);
               }


            }
         }

         if (worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {
            for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
               TileEntity te = worldObj.getTileEntity(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ);
               if (te instanceof IFluidHandler && !(te instanceof EntityPipe)) {
                  IFluidHandler fluid = (IFluidHandler) te;
                  for (FluidTankInfo info : fluid.getTankInfo(dir)) {
                     if (info.fluid != null && info.fluid.getFluid() != null) {
                        if (fluid.canDrain(dir.getOpposite(), info.fluid.getFluid())) {
                           int filled = this.fill(ForgeDirection.UNKNOWN, info.fluid, true);
                           if (filled > 0) {
                              fluid.drain(dir, filled, true);
                              worldObj.markBlockForUpdate(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ);
                           }
                        }
                     }
                  }
               }
            }
         }
         ticksSinceLastMove = 0;
         markDirty();
      }
   }


   public void changeState(ForgeDirection dir, boolean nState) {
      connections &= ~(1 << dir.ordinal());
      if (nState)
         connections |= (1 << dir.ordinal());
   }

   public boolean isConnected(ForgeDirection dir) {
      return (connections & (1 << dir.ordinal())) != 0;
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
      //if (doFill && filled > 0) {
      //   getNetworkDr().fill(new FluidStack(resource.getFluid(), filled), true);
      //   getNetworkDr().rebalance(worldObj);
      //}
      if (resource != null)
         if (/*resource.amount > tank.getFluidAmount() && */doFill)
            fluidSourceSide = from;

      markDirty();
      return filled;

   }

   @Override
   public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
      FluidStack drained = tank.drain(resource.amount, doDrain);
      //if (doDrain && drained.amount > 0) {
      //   getNetworkDr().drain(drained.amount, true);
      //   getNetworkDr().rebalance(worldObj);
      //}
      markDirty();
      return drained;
   }

   @Override
   public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
      FluidStack drained = tank.drain(maxDrain, doDrain);
      //if (doDrain && drained.amount > 0) {
      //   getNetworkDr().drain(drained.amount, true);
      //   getNetworkDr().rebalance(worldObj);
      //}
      markDirty();
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

   public void setSucking(boolean sucking) {
      this.sucking = sucking;
   }
/*
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
   }*/
}
