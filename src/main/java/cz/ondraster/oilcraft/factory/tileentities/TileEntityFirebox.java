package cz.ondraster.oilcraft.factory.tileentities;

import cz.ondraster.oilcraft.factory.IMachineRequiresHeat;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityFirebox extends TileEntityPart {
   protected static final int POWER_CAPACITY = 30000;

   protected double storedPower = 0;
   private int ticksSinceLastUpdate = 0;

   @Override
   public void updateEntity() {
      if (ticksSinceLastUpdate < 20) {
         ticksSinceLastUpdate++;
         return;
      }

      if (!(getMaster() instanceof IMachineRequiresHeat)) {
         return;
      }

      IMachineRequiresHeat heat = (IMachineRequiresHeat) getMaster();

      boolean temp = heat.increaseTemperature(1, false); // step
      if (temp && storedPower >= 20) {
         heat.increaseTemperature(2, true);
         storedPower -= 20;
      }
   }

   @Override
   public void readFromNBT(NBTTagCompound nbt) {
      super.readFromNBT(nbt);
      storedPower = nbt.getDouble("storedPower");
   }

   @Override
   public void writeToNBT(NBTTagCompound nbt) {
      super.writeToNBT(nbt);
      nbt.setDouble("storedPower", storedPower);
   }
}
