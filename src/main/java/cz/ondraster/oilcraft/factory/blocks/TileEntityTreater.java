package cz.ondraster.oilcraft.factory.blocks;

import net.minecraft.block.Block;
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

      ForgeDirection orientationController = ForgeDirection.getOrientation(worldObj.getBlockMetadata(xCoord, yCoord, zCoord));
      ForgeDirection opposite = orientationController.getOpposite();

      boolean isOk = true;
      Block bottomHeater = null;

      if (orientationController == ForgeDirection.EAST || orientationController == ForgeDirection.WEST) {
         for (int z = zCoord - 1; z <= zCoord + 1; z++) {
            int a = xCoord + opposite.offsetX;
            int b = xCoord;
            for (int x = Math.min(a, b); x <= Math.max(a, b); x++) {
               System.out.println("Checking block at: x = " + x + ", y = " + yCoord + ", z = " + z);
               Block block = worldObj.getBlock(x, yCoord - 1, z);
               if (bottomHeater != null && block != bottomHeater) {
                  isOk = false;
               } else if (!(block instanceof BlockMachineHeater)) {
                  isOk = false;
               } else {
                  bottomHeater = block;
               }
            }
         }
      } else {
         for (int x = xCoord - 1; x <= xCoord + 1; x++) {
            int a = zCoord + opposite.offsetZ;
            int b = zCoord;
            for (int z = Math.min(a, b); z <= Math.max(a, b); z++) {
               System.out.println("Checking block at: x = " + x + ", y = " + yCoord + ", z = " + z);
               Block block = worldObj.getBlock(x, yCoord - 1, z);
               if (bottomHeater != null && block != bottomHeater) {
                  isOk = false;
               } else if (!(block instanceof BlockMachineHeater)) {
                  isOk = false;
               } else {
                  bottomHeater = block;
               }
            }
         }
      }

   }
}
