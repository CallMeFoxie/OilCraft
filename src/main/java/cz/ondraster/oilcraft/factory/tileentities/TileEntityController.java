package cz.ondraster.oilcraft.factory.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import java.util.List;

public abstract class TileEntityController extends TileEntity {
   protected boolean isFormed = false;

   protected void resetBlock(int x, int y, int z) {
      TileEntity te = worldObj.getTileEntity(x, y, z);
      if (te instanceof TileEntityPart) {
         ((TileEntityPart) te).unmarkComplete();
      }
      if (te instanceof TileEntityPartWithInventory) {
         ((TileEntityPartWithInventory) te).unmarkComplete();
      }
   }

   public abstract void checkMultiblock();

   public abstract void resetMultiblock();

   public abstract void doWork();

   public boolean isFormed() {
      return isFormed;
   }

   public void reset() {
      if (isFormed) {
         isFormed = false;
         resetMultiblock();
      }
   }

   public void isComplete() {
      this.isFormed = true;
   }

   protected void markBlocks(List<TileEntity> checked) {
      for (TileEntity te : checked) {
         if (te instanceof TileEntityPart)
            ((TileEntityPart) te).setMaster(xCoord, yCoord, zCoord);
         else if (te instanceof TileEntityPartWithInventory)
            ((TileEntityPartWithInventory) te).setMaster(xCoord, yCoord, zCoord);

      }
   }

   @Override
   public void updateEntity() {
      super.updateEntity();
      if (isFormed)
         doWork();
   }

   protected void save(NBTTagCompound nbt) {
      nbt.setBoolean("isFormed", isFormed);
   }

   protected void load(NBTTagCompound nbt) {
      isFormed = nbt.getBoolean("isFormed");
   }

   @Override
   public void writeToNBT(NBTTagCompound nbt) {
      super.writeToNBT(nbt);
      save(nbt);
   }

   @Override
   public void readFromNBT(NBTTagCompound nbt) {
      super.readFromNBT(nbt);
      load(nbt);
   }

}
