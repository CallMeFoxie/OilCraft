package cz.ondraster.oilcraft.compatibility.rf;

import cofh.api.energy.IEnergyStorage;
import cpw.mods.fml.common.Optional;
import net.minecraft.nbt.NBTTagCompound;

@Optional.Interface(iface = "cofh.api.energy.IEnergyStorage", modid = "CoFHAPI|energy")
public class RFEnergyStorage implements IEnergyStorage {

   private int maxStorage;
   private int curStorage;
   private int maxTransfer;
   private int maxExtract;

   public RFEnergyStorage(int maxStorage, int maxTransfer, int maxExtract) {
      this.maxStorage = maxStorage;
      this.maxTransfer = maxTransfer;
      this.maxExtract = maxExtract;
   }

   public void load(NBTTagCompound nbt) {
      this.curStorage = nbt.getInteger("rfStored");
   }

   public void save(NBTTagCompound nbt) {
      nbt.setInteger("rfStored", this.curStorage);
   }

   @Override
   public int receiveEnergy(int maxReceive, boolean simulate) {
      int rx = Math.min(maxStorage - curStorage, Math.min(maxReceive, this.maxTransfer));
      if (!simulate)
         this.curStorage += rx;

      return rx;
   }

   @Override
   public int extractEnergy(int maxExtract, boolean simulate) {
      int tx = Math.min(curStorage, Math.min(maxExtract, this.maxExtract));
      if (!simulate)
         this.curStorage -= tx;

      return tx;
   }

   @Override
   public int getEnergyStored() {
      return curStorage;
   }

   @Override
   public int getMaxEnergyStored() {
      return maxStorage;
   }
}
