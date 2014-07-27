package cz.ondraster.oilcraft.factory.tileentities;

import cz.ondraster.oilcraft.inventory.BasicInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;

public class TileEntityFireboxSolid extends TileEntityFirebox implements IInventory {

   int ticksSinceLastUpdate = 0;
   BasicInventory inventory;

   public TileEntityFireboxSolid() {
      inventory = new BasicInventory(1);
   }

   @Override
   public void writeToNBT(NBTTagCompound nbt) {
      super.writeToNBT(nbt);
      inventory.save(nbt);
   }

   @Override
   public void readFromNBT(NBTTagCompound nbt) {
      super.readFromNBT(nbt);
      inventory.load(nbt);
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
   public ItemStack decrStackSize(int p_70298_1_, int p_70298_2_) {
      return inventory.decrStackSize(p_70298_1_, p_70298_2_);
   }

   @Override
   public ItemStack getStackInSlotOnClosing(int p_70304_1_) {
      return inventory.getStackInSlotOnClosing(p_70304_1_);
   }

   @Override
   public void setInventorySlotContents(int p_70299_1_, ItemStack p_70299_2_) {
      inventory.setInventorySlotContents(p_70299_1_, p_70299_2_);
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
   public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
      return inventory.isUseableByPlayer(p_70300_1_);
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
      if (slot >= 1 || item == null || item.stackSize == 0)
         return false;

      if (TileEntityFurnace.getItemBurnTime(item) > 0)
         return true;

      return false;
   }

   @Override
   public void updateEntity() {
      super.updateEntity();
      if (isComplete && ticksSinceLastUpdate > 20 && getStackInSlot(0) != null) {
         int power = TileEntityFurnace.getItemBurnTime(getStackInSlot(0));
         if (storedPower + power <= POWER_CAPACITY) {
            storedPower += power;
            decrStackSize(0, 1);
         }

         ticksSinceLastUpdate = 0;
      }

      ticksSinceLastUpdate++;
   }
}
