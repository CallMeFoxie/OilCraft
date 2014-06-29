package cz.ondraster.oilcraft.entities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import java.util.Random;

/**
 * Created by Ondra on 22.6.2014.
 */
public class EntityOiljack extends TileEntity {

   public int renderOffset = 0;
   public int bridleLength = 0;

   public EntityOiljack() {
      setBridleLength(new Random().nextInt(5));
   }

   @Override
   public void readFromNBT(NBTTagCompound tag) {
      super.readFromNBT(tag);
      int tmp = tag.getInteger("bridleLength");
      if (tmp != 0)
         this.bridleLength = tmp;
   }

   @Override
   public void writeToNBT(NBTTagCompound tag) {
      super.writeToNBT(tag);
      tag.setInteger("bridleLength", bridleLength);
   }

   @Override
   public void updateEntity() {
      renderOffset += 4;
      if (renderOffset >= 360) {
         renderOffset = 0;
      }
   }

   public void setBridleLength(int n) {
      bridleLength = n;
   }

}
