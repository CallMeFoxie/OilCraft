package cz.ondraster.oilcraft.factory.tileentities;

import buildcraft.api.mj.MjBattery;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityFireboxMJ extends TileEntityFirebox {

   @MjBattery(maxCapacity = 100, maxReceivedPerCycle = 16, minimumConsumption = 1)
   private double mjStored = 0;

   @Override
   public void readFromNBT(NBTTagCompound nbt) {
      super.readFromNBT(nbt);
      mjStored = nbt.getDouble("mjStored");
   }

   @Override
   public void writeToNBT(NBTTagCompound nbt) {
      super.writeToNBT(nbt);
      nbt.setDouble("mjStored", mjStored);
   }

   @Override
   public void updateEntity() {
      super.updateEntity();
      if (mjStored >= 1 && storedPower + 20 <= POWER_CAPACITY) {
         storedPower += 20;
         mjStored--;
      }
   }
}
