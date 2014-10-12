package cz.ondraster.oilcraft.tileentities;

import cofh.api.energy.IEnergyHandler;
import cpw.mods.fml.common.Optional;
import cz.ondraster.oilcraft.PowerTools;
import cz.ondraster.oilcraft.compatibility.rf.RFEnergyStorage;
import cz.ondraster.oilcraft.worldgen.Config;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergySink;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;

@Optional.InterfaceList({
      @Optional.Interface(iface = "cofh.api.energy.IEnergyStorage", modid = "CoFHAPI|energy"),
      @Optional.Interface(iface = "ic2.api.energy.tile.IEnergySink", modid = "IC2API")
})

public class TileEntityGenerator extends TileEntity implements IEnergyHandler, IEnergySink {
   RFEnergyStorage RFStorage;
   boolean notifiedIC2 = false;
   private int powerStored = 0;

   public TileEntityGenerator() {
      RFStorage = new RFEnergyStorage(Config.oiljackPowerCapacity, Config.oiljackMaxTransferRF, Config.powerPerAction);
   }

   @Override
   public void readFromNBT(NBTTagCompound tag) {
      super.readFromNBT(tag);
      powerStored = tag.getInteger("powerStored");
   }

   @Override
   public void writeToNBT(NBTTagCompound tag) {
      super.writeToNBT(tag);
      tag.setInteger("powerStored", powerStored);
   }

   @Override
   public void updateEntity() {
      super.updateEntity();

      if (!notifiedIC2 && !worldObj.isRemote) {
         EnergyTileLoadEvent event = new EnergyTileLoadEvent(this);
         MinecraftForge.EVENT_BUS.post(event);
         notifiedIC2 = true;
      }
   }

   public int getStoredEnergy() {
      return RFStorage.getEnergyStored();
   }

   /* RF API */
   @Override
   public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
      if (!canConnectEnergy(from))
         return 0;

      return RFStorage.receiveEnergy(maxReceive, simulate);
   }

   @Override
   public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
      return 0;
   }

   @Override
   public int getEnergyStored(ForgeDirection from) {
      if (!canConnectEnergy(from))
         return 0;

      return RFStorage.getEnergyStored();
   }

   @Override
   public int getMaxEnergyStored(ForgeDirection from) {
      if (!canConnectEnergy(from))
         return 0;

      return RFStorage.getMaxEnergyStored();
   }

   @Override
   public boolean canConnectEnergy(ForgeDirection from) {
      return true;
   }

   /* /RF API */

   /* IC2 API */
   @Override
   public double getDemandedEnergy() {
      return Math.min((RFStorage.getMaxEnergyStored() - RFStorage.getEnergyStored()) / (Config.powerPerEu / Config.powerPerRF), Config.oiljackMaxTransferEu);
   }

   @Override
   public int getSinkTier() {
      return 1;
   }

   @Override
   public double injectEnergy(ForgeDirection forgeDirection, double amount, double voltage) {
      double unused = amount - PowerTools.convertRFtoEU(RFStorage.receiveEnergy(PowerTools.convertEUtoRF(amount), false));
      return unused;
   }

   @Override
   public boolean acceptsEnergyFrom(TileEntity tileEntity, ForgeDirection from) {
      return canConnectEnergy(from);
   }

   @Optional.Method(modid = "IC2API")
   @Override
   public void onChunkUnload() {
      onUnload();
      super.onChunkUnload();
   }

   @Optional.Method(modid = "IC2API")
   public void onUnload() {
      EnergyTileUnloadEvent event = new EnergyTileUnloadEvent(this);
      MinecraftForge.EVENT_BUS.post(event);
   }

   /* /IC2 API */

   public void extractEnergy(int powerPerAction) {
      RFStorage.extractEnergy(powerPerAction, false);
   }
}
