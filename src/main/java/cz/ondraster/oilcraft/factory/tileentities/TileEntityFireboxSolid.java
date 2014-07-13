package cz.ondraster.oilcraft.factory.tileentities;

import cz.ondraster.oilcraft.factory.IMachineRequiresPower;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

public class TileEntityFireboxSolid extends TileEntityPartWithInventory {

   int ticksSinceLastUpdate = 0;

   public TileEntityFireboxSolid() {
      super(1);
   }

   @Override
   public boolean isItemValidForSlot(int slot, ItemStack item) {
      if (slot >= 1 || item == null || item.stackSize == 0)
         return false;

      if (TileEntityFurnace.getItemBurnTime(item) > 0)
         return true;

      return false;
   }

   @Override
   public void updateEntity() {
      super.updateEntity();
      if (isComplete && ticksSinceLastUpdate > 100 && getStackInSlot(0) != null)
         if (getMaster() instanceof IMachineRequiresPower) {
            boolean acceptedPower;
            do {
               acceptedPower = ((IMachineRequiresPower) getMaster()).addPower(TileEntityFurnace.getItemBurnTime(getStackInSlot(0)));
               if (acceptedPower)
                  decrStackSize(0, 1);
            } while (getStackInSlot(0) != null && acceptedPower);

            ticksSinceLastUpdate = 0;
         }

      ticksSinceLastUpdate++;
   }
}
