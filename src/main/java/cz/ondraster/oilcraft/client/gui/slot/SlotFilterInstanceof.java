package cz.ondraster.oilcraft.client.gui.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotFilterInstanceof extends Slot {

   Class[] classType;

   public SlotFilterInstanceof(IInventory par1iInventory, int par2, int par3, int par4, Class type) {
      super(par1iInventory, par2, par3, par4);
      this.classType = new Class[] { type };
   }

   public SlotFilterInstanceof(IInventory par1iInventory, int par2, int par3, int par4, Class[] type) {
      super(par1iInventory, par2, par3, par4);
      this.classType = type;
   }

   @Override
   public boolean isItemValid(ItemStack item) {
      for (Class c : classType)
         if (c.isInstance(item.getItem()))
            return true;

      return false;
   }
}
