package cz.ondraster.oilcraft.factory.tileentities;

import cz.ondraster.oilcraft.factory.IMachineRequiresPower;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityFirebox extends TileEntityPart {
   protected static final int POWER_CAPACITY = 1000;

   protected int temperature = 0;
   protected double storedPower = 0;
   private int ticksSinceLastUpdate = 0;

   @Override
   public void updateEntity() {
      if (isComplete && ticksSinceLastUpdate > 20 && storedPower >= 20)
         if (getMaster() instanceof IMachineRequiresPower) {
            boolean acceptedPower;
            do {
               acceptedPower = ((IMachineRequiresPower) getMaster()).addPower(20);
               if (acceptedPower)
                  storedPower -= 20;
            } while (storedPower >= 20 && acceptedPower);
            ticksSinceLastUpdate = 0;
         }

      ticksSinceLastUpdate++;
   }

   @Override
   public void readFromNBT(NBTTagCompound nbt) {
      super.readFromNBT(nbt);
      temperature = nbt.getInteger("temperature");
      storedPower = nbt.getDouble("storedPower");
   }

   @Override
   public void writeToNBT(NBTTagCompound nbt) {
      super.writeToNBT(nbt);
      nbt.setInteger("temperature", temperature);
      nbt.setDouble("storedPower", storedPower);
   }
}
