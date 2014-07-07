package cz.ondraster.oilcraft.client.gui.slot;

import net.minecraft.block.Block;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class SlotFilterBlock extends Slot {

   Block block;

   public SlotFilterBlock(IInventory par1iInventory, int par2, int par3, int par4, Block block) {
      super(par1iInventory, par2, par3, par4);
      this.block = block;
   }

   @Override
   public boolean isItemValid(ItemStack item) {
      if (!(item.getItem() instanceof ItemBlock))
         return false;

      ItemBlock it = (ItemBlock) item.getItem();

      if (it.field_150939_a == this.block)
         return true;

      return false;
   }

}
