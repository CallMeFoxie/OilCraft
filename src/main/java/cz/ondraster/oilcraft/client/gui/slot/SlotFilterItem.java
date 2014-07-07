package cz.ondraster.oilcraft.client.gui.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SlotFilterItem extends Slot {

   private final Item itemFilter;

   public SlotFilterItem(IInventory par1iInventory, int par2, int par3, int par4, Item filter) {
      super(par1iInventory, par2, par3, par4);
      this.itemFilter = filter;
   }

   @Override
   public boolean isItemValid(ItemStack item) {
      Item it = item.getItem();
      return it == this.itemFilter;
   }

}
