package cz.ondraster.oilcraft.factory.blocks;

import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityTreater extends TileEntityController {
   // this multiblock is 2 deep, 2 high and 3 deep:
   /*
      (back)
      VALVEHT    CASINGHT      VALVEHT
      HEATER     HEATER        HEATER

      (front)
      CASINGHT   CONTROLLER    CASINGHT
      HEATER     HEATER        HEATER

    */
   @Override
   public void checkMultiblock() {
      if (worldObj.isRemote)
         return;

      // bottom row = either all of them are solid heaters or all of them are electric heaters. Should be 1 below us.

      ForgeDirection orientationController = ForgeDirection.getOrientation(worldObj.getBlockMetadata(xCoord, yCoord, zCoord)).getOpposite();

      for (int x = xCoord - orientationController.getOpposite().offsetX; x <= xCoord + orientationController.offsetX; x++) // check for the other orientation later
      {
         for (int z = zCoord - orientationController.offsetZ; z <= zCoord; z++) {
            System.out.println("Checking block at: x = " + x + ", y = " + yCoord + ", z = " + z);
         }
      }

   }
}
