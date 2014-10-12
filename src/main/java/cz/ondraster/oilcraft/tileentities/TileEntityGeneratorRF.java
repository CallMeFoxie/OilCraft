package cz.ondraster.oilcraft.tileentities;

import cofh.api.energy.IEnergyHandler;
import cz.ondraster.oilcraft.compatibility.rf.RFEnergyStorage;
import cz.ondraster.oilcraft.worldgen.Config;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public abstract class TileEntityGeneratorRF extends TileEntity implements IEnergyHandler {

   protected RFEnergyStorage RFStorage;

   public TileEntityGeneratorRF() {
      RFStorage = new RFEnergyStorage(Config.GeneratorRF.maxStorage, Config.GeneratorRF.maxTransfer, Config.GeneratorRF.maxExtract);
   }

   @Override
   public void readFromNBT(NBTTagCompound tag) {
      super.readFromNBT(tag);
      RFStorage.load(tag);
   }

   @Override
   public void writeToNBT(NBTTagCompound tag) {
      super.writeToNBT(tag);
      RFStorage.save(tag);
   }

   @Override
   public void updateEntity() {

   }

   /* RF API */
   @Override
   public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
      return 0;
   }

   @Override
   public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
      return RFStorage.extractEnergy(maxExtract, simulate);
   }

   @Override
   public int getEnergyStored(ForgeDirection from) {
      return RFStorage.getEnergyStored();
   }

   @Override
   public int getMaxEnergyStored(ForgeDirection from) {
      return RFStorage.getMaxEnergyStored();
   }

   @Override
   public boolean canConnectEnergy(ForgeDirection from) {
      return true;
   }

   /* /RF API */
}
