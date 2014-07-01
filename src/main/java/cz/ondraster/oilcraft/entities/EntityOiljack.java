package cz.ondraster.oilcraft.entities;

import cz.ondraster.oilcraft.OrientationSimple;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class EntityOiljack extends TileEntity {

   public int renderOffset = 0;
   private int lastUpdate = 0;
   private int orientation = OrientationSimple.North;

   public EntityOiljack() {

   }

   @Override
   public void readFromNBT(NBTTagCompound tag) {
      super.readFromNBT(tag);
      orientation = tag.getInteger("orientation");
   }

   @Override
   public void writeToNBT(NBTTagCompound tag) {
      super.writeToNBT(tag);
      tag.setInteger("orientation", orientation);
   }

   @Override
   public void updateEntity() {
      if (lastUpdate >= 40) {
         lastUpdate = 0;
         if (worldObj.getTileEntity(xCoord + OrientationSimple.getX(orientation), yCoord, zCoord + OrientationSimple.getZ(orientation)) instanceof EntityOiljackPipe) {
            ((EntityOiljackPipe) worldObj.getTileEntity(xCoord + OrientationSimple.getX(orientation), yCoord, zCoord + OrientationSimple.getZ(orientation))).digOil();
         }
      }

      lastUpdate++;
   }

   public boolean canWork() {
      // check for neighbour blocks. Should run client side only to help with server perfomance!
      if (worldObj.getTileEntity(xCoord + OrientationSimple.getX(orientation), yCoord, zCoord + OrientationSimple.getZ(orientation)) instanceof EntityOiljackPipe)
         return true;

      return false;
   }
}
