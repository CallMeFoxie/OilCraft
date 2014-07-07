package cz.ondraster.oilcraft;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public abstract class TileEntityWithInventory extends TileEntity implements IInventory {
   protected final ItemStack[] inventory;
   protected Container         myContainer = null;

   public TileEntityWithInventory(int slots) {
      super();
      inventory = new ItemStack[slots];
   }

   public void setContainer(Container c) {
      this.myContainer = c;
   }

   @Override
   public int getSizeInventory() {
      return inventory.length;
   }

   @Override
   public ItemStack getStackInSlot(int slot) {
      if (slot >= inventory.length)
         return null;

      return onItemPulledOut(slot, inventory[slot]);
   }

   @Override
   public ItemStack decrStackSize(int slot, int amount) {
      ItemStack stack = null;

      if (inventory[slot] != null) {
         if (inventory[slot].stackSize <= amount) {
            stack = inventory[slot];
            inventory[slot] = null;
         } else {
            stack = inventory[slot].splitStack(amount);
            if (inventory[slot].stackSize == 0) {
               inventory[slot] = null;
            }
         }
      }

      onInventoryChanged(slot);

      return stack;

   }

   @Override
   public ItemStack getStackInSlotOnClosing(int var1) {
      ItemStack items = getStackInSlot(var1);
      if (items != null)
         setInventorySlotContents(var1, null);

      onInventoryChanged(var1);

      return items;
   }

   @Override
   public void setInventorySlotContents(int slot, ItemStack item) {
      inventory[slot] = item;
      onInventoryChanged(slot);
   }

   @Override
   public String getInventoryName() {
      return this.toString();
   }

   @Override
   public boolean hasCustomInventoryName() {
      return false;
   }

   @Override
   public int getInventoryStackLimit() {
      return 64;
   }

   @Override
   public boolean isUseableByPlayer(EntityPlayer var1) {
      return true;
   }

   @Override
   public void openInventory() {
   }

   @Override
   public void closeInventory() {
   }

   @Override
   public boolean isItemValidForSlot(int slot, ItemStack item) {
      if (this.myContainer == null)
         return false;

      if (this.myContainer.getSlot(slot).isItemValid(item)) {
         return true;
      }

      return false;
   }

   @Override
   public void readFromNBT(NBTTagCompound nbt) {
      super.readFromNBT(nbt);
      NBTTagList list = nbt.getTagList("Inventory", 10);
      for (int i = 0; i < list.tagCount(); i++) {
         NBTTagCompound tag = list.getCompoundTagAt(i);
         byte slot = tag.getByte("Slot");
         if (slot >= 0 && slot < inventory.length) {
            inventory[slot] = ItemStack.loadItemStackFromNBT(tag);
         }
      }

      onInventoryChanged(-1);

   }

   @Override
   public void writeToNBT(NBTTagCompound nbt) {
      super.writeToNBT(nbt);

      NBTTagList itemList = new NBTTagList();
      for (int i = 0; i < inventory.length; i++) {
         ItemStack stack = inventory[i];
         if (stack != null) {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setByte("Slot", (byte) i);
            stack.writeToNBT(tag);
            itemList.appendTag(tag);
         }
      }
      nbt.setTag("Inventory", itemList);
   }

   /**
    * Called when inventory slot has been changed
    * 
    * @param slot
    *           Slot that has been changed, -1 for whole inventory
    */

   protected void onInventoryChanged(int slot) {
   }

   /**
    * Called when an item is being pulled from the inventory
    * 
    * @param slot
    * @param stack
    *           ItemStack of the item
    * @return new ItemStack
    */
   protected ItemStack onItemPulledOut(int slot, ItemStack stack) {
      return stack;
   }
}
