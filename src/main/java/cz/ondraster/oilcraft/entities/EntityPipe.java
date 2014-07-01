package cz.ondraster.oilcraft.entities;

import cz.ondraster.oilcraft.FluidTank;
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
   FluidTank tank;

   public EntityPipe() {
      tank = new FluidTank(2000);
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
      return tank.fill(resource, doFill);
   }

   @Override
   public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
      return tank.drain(resource.amount, doDrain);
   }

   @Override
   public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
      return tank.drain(maxDrain, doDrain);
   }

   @Override
   public boolean canFill(ForgeDirection from, Fluid fluid) {
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
}
