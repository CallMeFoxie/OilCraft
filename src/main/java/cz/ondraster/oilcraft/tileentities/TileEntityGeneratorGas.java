package cz.ondraster.oilcraft.tileentities;

import cz.ondraster.oilcraft.fluids.FluidTank;
import cz.ondraster.oilcraft.worldgen.Config;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class TileEntityGeneratorGas extends TileEntityGeneratorRF implements IFluidHandler {

   public int rotationOffset = 0; // clientside only stuff, for rendering of the animation
   FluidTank tank;
   private ForgeDirection orientation;
   private boolean animationRunning = false;
   private int lastPercentSync = 0;

   public TileEntityGeneratorGas() {
      tank = new FluidTank(Config.GeneratorRF.gasTankCapacity);
   }

   @Override
   public void writeToNBT(NBTTagCompound tag) {
      super.writeToNBT(tag);
      tank.save(tag);
      tag.setInteger("orientation", orientation.ordinal());
   }

   @Override
   public void readFromNBT(NBTTagCompound tag) {
      super.readFromNBT(tag);
      tank.load(tag);
      orientation = ForgeDirection.getOrientation(tag.getInteger("orientation"));
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
   public void updateEntity() {
      super.updateEntity();

      if (tank.getFluidAmount() > 0 && RFStorage.receiveEnergy(Config.GeneratorRF.generatePerMBofGas, true) == Config.GeneratorRF.generatePerMBofGas) {
         tank.drain(1, true);
         RFStorage.receiveEnergy(Config.GeneratorRF.generatePerMBofGas, false);
      }

      if (animationRunning && RFStorage.getEnergyStored() == 0) {
         animationRunning = false;
         worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
         lastPercentSync = Math.abs(RFStorage.getEnergyStored() / RFStorage.getMaxEnergyStored() * 100);
      } else if (!animationRunning && RFStorage.getEnergyStored() > 0) {
         animationRunning = true;
         worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
         lastPercentSync = Math.abs(RFStorage.getEnergyStored() / RFStorage.getMaxEnergyStored() * 100);
      }

      if ((Math.abs(RFStorage.getEnergyStored() / RFStorage.getMaxEnergyStored() * 100) - lastPercentSync) > 10) {
         worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
         lastPercentSync = Math.abs(RFStorage.getEnergyStored() / RFStorage.getMaxEnergyStored() * 100);
      }
   }

   public ForgeDirection getOrientation() {
      return orientation;
   }

   public void setOrientation(ForgeDirection orientation) {
      this.orientation = orientation;
   }

   /* FLUID API */

   @Override
   public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
      return tank.fill(resource, doFill);
   }

   @Override
   public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
      return null;
   }

   @Override
   public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
      return null;
   }

   @Override
   public boolean canFill(ForgeDirection from, Fluid fluid) {
      return fluid == tank.getFluid().getFluid() || tank.getFluid().getFluid() == null;
   }

   @Override
   public boolean canDrain(ForgeDirection from, Fluid fluid) {
      return false;
   }

   @Override
   public FluidTankInfo[] getTankInfo(ForgeDirection from) {
      return new FluidTankInfo[]{tank.getInfo()};
   }

   /* /FLUID API */
}
