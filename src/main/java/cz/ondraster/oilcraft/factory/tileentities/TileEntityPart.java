package cz.ondraster.oilcraft.factory.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityPart extends TileEntity {
   protected int xMaster;
   protected int yMaster;
   protected int zMaster;
   protected boolean isComplete = false;

   private int lastTick = 0;
   private Object master;


   @Override
   public void updateEntity() {
      ticker();
   }

   protected void ticker() {
      if (lastTick >= 20 && isComplete && worldObj != null) {
         // check if master structure is still complete
         TileEntity x = worldObj.getTileEntity(xMaster, yMaster, zMaster);
         if (x instanceof TileEntityController) {
            if (!((TileEntityController) x).isFormed) {
               this.isComplete = false;
            }
         }

         lastTick = 0;
      }

      lastTick++;
   }

   public void setMaster(int x, int y, int z) {
      this.xMaster = x;
      this.yMaster = y;
      this.zMaster = z;
      this.isComplete = true;
   }

   protected void save(NBTTagCompound nbt) {
      nbt.setInteger("xMaster", xMaster);
      nbt.setInteger("yMaster", yMaster);
      nbt.setInteger("zMaster", zMaster);
      nbt.setBoolean("isComplete", isComplete);
   }

   protected void load(NBTTagCompound nbt) {
      xMaster = nbt.getInteger("xMaster");
      yMaster = nbt.getInteger("yMaster");
      zMaster = nbt.getInteger("zMaster");
      isComplete = nbt.getBoolean("isComplete");
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

   public boolean isComplete() {
      return isComplete;
   }

   public TileEntity getMaster() {
      if (!isComplete())
         return null;

      return worldObj.getTileEntity(xMaster, yMaster, zMaster);
   }
}
