package cz.ondraster.oilcraft.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public abstract class SmartContainer extends Container {

   private final int sacredSlots;
   protected TileEntity tileEntity;
   private int totalSlots;

   public SmartContainer(InventoryPlayer inv, TileEntity te, int sacredSlots) {
      tileEntity = te;
      this.sacredSlots = sacredSlots;
      totalSlots = sacredSlots;
   }

   protected void bindPlayerInventory(InventoryPlayer inv) {
      for (int i = 0; i < 3; i++) {
         for (int j = 0; j < 9; j++) {
            addSlotToContainer(new Slot(inv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            totalSlots++;
         }
      }

      for (int i = 0; i < 9; i++) {
         addSlotToContainer(new Slot(inv, i, 8 + i * 18, 142));
         totalSlots++;
      }

   }

   @Override
   public boolean canInteractWith(EntityPlayer var1) {
      return true;
   }

   @Override
   public ItemStack transferStackInSlot(EntityPlayer player, int sourceSlot) {

      ItemStack stack = null;
      Slot slotObject = (Slot) inventorySlots.get(sourceSlot);

      if (slotObject != null && slotObject.getHasStack()) {
         ItemStack stackInSlot = slotObject.getStack();
         stack = stackInSlot.copy();

         // items with source in Sacred Slots (c)
         if (sourceSlot < sacredSlots && totalSlots > sacredSlots) {
            if (!this.mergeItemStack(stackInSlot, sacredSlots, totalSlots, true)) {
               return null;
            }
         }
         // items with source in user's inventory...
         else {
            int slot = -1;
            for (int i = 0; i < sacredSlots; i++) {
               if (((Slot) inventorySlots.get(i)).isItemValid(stackInSlot)
                     && this.mergeItemStack(stackInSlot, i, i + 1, false))
                  slot = i;
            }

            if (slot == -1)
               return null;
         }

         if (stackInSlot.stackSize == 0) {
            slotObject.putStack(null);
         } else {
            slotObject.onSlotChanged();
         }

         if (stackInSlot.stackSize == stack.stackSize) {
            return null;
         }
         slotObject.onPickupFromSlot(player, stackInSlot);
      }
      return stack;

   }
}
