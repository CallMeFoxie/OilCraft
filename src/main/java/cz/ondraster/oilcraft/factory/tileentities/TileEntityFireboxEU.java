package cz.ondraster.oilcraft.factory.tileentities;

import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergySink;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityFireboxEU extends TileEntityFirebox implements IEnergySink {
   private static final int EU_CAPACITY = 4000;
   private static final int EU_PER_TICK = 32;
   boolean notified = false;
   private double EUstored = 0;

   public TileEntityFireboxEU() {
      super();
   }

   @Override
   public double getDemandedEnergy() {
      return Math.min(EU_CAPACITY - EUstored, EU_PER_TICK);
   }

   @Override
   public int getSinkTier() {
      return 1;
   }

   @Override
   public double injectEnergy(ForgeDirection forgeDirection, double amount, double voltage) {
      double retval = 0;
      if (EUstored + amount > EU_CAPACITY) {
         retval = EU_CAPACITY - (EUstored + amount);
         EUstored += retval;
      } else {
         retval = 0;
         EUstored += amount;
      }
      return retval;
   }

   @Override
   public boolean acceptsEnergyFrom(TileEntity tileEntity, ForgeDirection forgeDirection) {
      return true;
   }

   @Override
   public void updateEntity() {
      if (!notified && !worldObj.isRemote) {
         EnergyTileLoadEvent event = new EnergyTileLoadEvent(this);
         MinecraftForge.EVENT_BUS.post(event);
         notified = true;
      }
      if (EUstored >= 3 && storedPower + 20 <= POWER_CAPACITY) {
         storedPower += 20;
         EUstored -= 3;
      }
      super.updateEntity();
   }

   @Override
   public void onChunkUnload() {
      onUnload();
      super.onChunkUnload();
   }

   @Override
   public void readFromNBT(NBTTagCompound nbt) {
      super.readFromNBT(nbt);
      EUstored = nbt.getDouble("EUstored");
   }

   @Override
   public void writeToNBT(NBTTagCompound nbt) {
      super.writeToNBT(nbt);
      nbt.setDouble("EUstored", EUstored);
   }

   public void onUnload() {
      EnergyTileUnloadEvent event = new EnergyTileUnloadEvent(this);
      MinecraftForge.EVENT_BUS.post(event);
   }
}
