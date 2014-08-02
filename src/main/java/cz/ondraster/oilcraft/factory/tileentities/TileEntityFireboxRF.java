package cz.ondraster.oilcraft.factory.tileentities;

import cofh.api.energy.IEnergyHandler;
import cpw.mods.fml.common.Optional;
import cz.ondraster.oilcraft.compatibility.rf.RFEnergyStorage;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

@Optional.Interface(iface = "cofh.api.energy.IEnergyHandler", modid = "CoFHAPI|energy")
public class TileEntityFireboxRF extends TileEntityFirebox implements IEnergyHandler {

   private RFEnergyStorage storage;

   public TileEntityFireboxRF() {
      storage = new RFEnergyStorage(30000, 200, 0);
   }

   @Override
   public void readFromNBT(NBTTagCompound nbt) {
      super.readFromNBT(nbt);
      storage.load(nbt);
   }

   @Override
   public void writeToNBT(NBTTagCompound nbt) {
      super.writeToNBT(nbt);
      storage.save(nbt);
   }

   @Override
   public void updateEntity() {
      super.updateEntity();
      if (storage.getEnergyStored() >= 1 && storedPower + 20 <= POWER_CAPACITY) {
         storedPower += 20;
         storage.extractEnergy(20, false);
      }
   }

   @Override
   public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
      return storage.receiveEnergy(maxReceive, simulate);
   }

   @Override
   public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
      return storage.extractEnergy(maxExtract, simulate);
   }

   @Override
   public int getEnergyStored(ForgeDirection from) {
      return storage.getEnergyStored();
   }

   @Override
   public int getMaxEnergyStored(ForgeDirection from) {
      return storage.getMaxEnergyStored();
   }

   @Override
   public boolean canConnectEnergy(ForgeDirection from) {
      return true;
   }
}
