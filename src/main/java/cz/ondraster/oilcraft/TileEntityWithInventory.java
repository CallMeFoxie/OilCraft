package cz.ondraster.oilcraft;

import cz.ondraster.oilcraft.inventory.BasicInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public abstract class TileEntityWithInventory extends TileEntity implements IInventory {
   protected Container myContainer = null;
   protected BasicInventory inventory;

   public TileEntityWithInventory(int slots) {
      super();
      inventory = new BasicInventory(slots);
   }

   public void setContainer(Container c) {
      inventory.setContainer(c);
   }

   @Override
   public int getSizeInventory() {
      return inventory.getSizeInventory();
   }

   @Override
   public ItemStack getStackInSlot(int slot) {
      return inventory.getStackInSlot(slot);
   }

   @Override
   public ItemStack decrStackSize(int slot, int amount) {
      return inventory.decrStackSize(slot, amount);
   }

   @Override
   public ItemStack getStackInSlotOnClosing(int var1) {
      return inventory.getStackInSlotOnClosing(var1);
   }

   @Override
   public void setInventorySlotContents(int slot, ItemStack item) {
      inventory.setInventorySlotContents(slot, item);
   }

   @Override
   public String getInventoryName() {
      return inventory.getInventoryName();
   }

   @Override
   public boolean hasCustomInventoryName() {
      return inventory.hasCustomInventoryName();
   }

   @Override
   public int getInventoryStackLimit() {
      return inventory.getInventoryStackLimit();
   }

   @Override
   public boolean isUseableByPlayer(EntityPlayer var1) {
      return inventory.isUseableByPlayer(var1);
   }

   @Override
   public void openInventory() {
      inventory.openInventory();
   }

   @Override
   public void closeInventory() {
      inventory.closeInventory();
   }

   @Override
   public boolean isItemValidForSlot(int slot, ItemStack item) {
      return inventory.isItemValidForSlot(slot, item);
   }

   @Override
   public void readFromNBT(NBTTagCompound nbtTagCompound) {
      super.readFromNBT(nbtTagCompound);
      inventory.load(nbtTagCompound);
   }

   @Override
   public void writeToNBT(NBTTagCompound nbtTagCompound) {
      super.writeToNBT(nbtTagCompound);
      inventory.save(nbtTagCompound);
   }

}
