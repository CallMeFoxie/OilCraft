package cz.ondraster.oilcraft.factory.blocks;

import net.minecraft.tileentity.TileEntity;

public abstract class TileEntityController extends TileEntity {
   protected boolean isFormed = false;

   public abstract void checkMultiblock();

   public boolean isFormed() {
      return isFormed;
   }

   public void reset() {
      isFormed = false;
      System.out.println("DEformed");
   }
}
