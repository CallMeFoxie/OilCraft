package cz.ondraster.oilcraft.factory.tileentities;

import buildcraft.api.mj.MjBattery;

public class TileEntityFireboxMJ extends TileEntityFirebox {

   @MjBattery(maxCapacity = 100, maxReceivedPerCycle = 16, minimumConsumption = 1)
   private double mjStored = 0;

   @Override
   public void updateEntity() {
      super.updateEntity();
      if (mjStored >= 1) {
         storedPower += 20;
      }
   }
}
