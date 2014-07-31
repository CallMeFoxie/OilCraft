package cz.ondraster.oilcraft.factory.tileentities;

import cz.ondraster.oilcraft.Helper;
import cz.ondraster.oilcraft.factory.multiblock.MultiblockPart;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;

public class TileEntityHatch extends TileEntityPartWithInventory implements ISidedInventory {
   public TileEntityHatch() {
      super(1);
   }

   @Override
   public int[] getAccessibleSlotsFromSide(int side) {
      int meta = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
      if (side == MultiblockPart.getDirection(meta).ordinal())
         return new int[]{0};
      else
         return null;
   }

   @Override
   public boolean canInsertItem(int slot, ItemStack item, int side) {
      if (slot > 0)
         return false;
      if (!Helper.canMergeStacks(item, getStackInSlot(0)))
         return false;
      if (getAccessibleSlotsFromSide(side) == null)
         return false;

      return true;
   }

   @Override
   public boolean canExtractItem(int slot, ItemStack item, int side) {
      if (slot > 0)
         return false;
      if (item.getItem() != getStackInSlot(0).getItem())
         return false;
      if (getAccessibleSlotsFromSide(side) == null)
         return false;

      return true;
   }
}
