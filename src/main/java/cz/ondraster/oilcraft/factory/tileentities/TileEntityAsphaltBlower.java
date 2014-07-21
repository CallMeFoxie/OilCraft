package cz.ondraster.oilcraft.factory.tileentities;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.List;

public class TileEntityAsphaltBlower extends TileEntityController {
   @Override
   public void checkMultiblock() {
      List<TileEntity> checked = new ArrayList<TileEntity>();
      if (worldObj.isRemote)
         return;

      boolean isOk = true;

      TileEntity xte = getInputTE();
      checked.add(xte);
      if (!(xte instanceof TileEntityValve)) {
         isOk = false;
      }

      xte = getOutputTE();
      checked.add(xte);
      if (!(xte instanceof TileEntityValve)) {
         isOk = false;
      }

      for (TileEntity te : checked) {
         if (te instanceof TileEntityPart) {
            if (((TileEntityPart) te).isComplete())
               isOk = false;
         } else if (te instanceof TileEntityPartWithInventory)
            if (((TileEntityPartWithInventory) te).isComplete)
               isOk = false;
      }

      if (isOk) {
         isComplete();
         markBlocks(checked);
      } else {
         reset();
      }
   }

   @Override
   public void resetMultiblock() {
      ForgeDirection orientationController = ForgeDirection.getOrientation(worldObj.getBlockMetadata(xCoord, yCoord, zCoord));
      if (orientationController == ForgeDirection.WEST || orientationController == ForgeDirection.EAST) {
         resetBlock(xCoord, yCoord, zCoord - 1);
         resetBlock(xCoord, yCoord, zCoord + 1);
      } else if (orientationController == ForgeDirection.NORTH || orientationController == ForgeDirection.SOUTH) {
         resetBlock(xCoord - 1, yCoord, zCoord);
         resetBlock(xCoord + 1, yCoord, zCoord);
      }
   }

   @Override
   public boolean canWork() {
      return true;
   }

   @Override
   public void beforeWork() {
   }

   @Override
   public void afterWork(boolean success) {
   }

   private TileEntity getInputTE() {
      ForgeDirection orientationController = ForgeDirection.getOrientation(worldObj.getBlockMetadata(xCoord, yCoord, zCoord));
      if (orientationController == ForgeDirection.EAST)
         return worldObj.getTileEntity(xCoord, yCoord, zCoord - 1);
      else if (orientationController == ForgeDirection.WEST)
         return worldObj.getTileEntity(xCoord, yCoord, zCoord + 1);
      else if (orientationController == ForgeDirection.NORTH)
         return worldObj.getTileEntity(xCoord - 1, yCoord, zCoord);
      else if (orientationController == ForgeDirection.SOUTH)
         return worldObj.getTileEntity(xCoord + 1, yCoord, zCoord);

      return null;
   }

   private TileEntity getOutputTE() {
      ForgeDirection orientationController = ForgeDirection.getOrientation(worldObj.getBlockMetadata(xCoord, yCoord, zCoord));
      if (orientationController == ForgeDirection.EAST)
         return worldObj.getTileEntity(xCoord, yCoord, zCoord + 1);
      else if (orientationController == ForgeDirection.WEST)
         return worldObj.getTileEntity(xCoord, yCoord, zCoord - 1);
      else if (orientationController == ForgeDirection.NORTH)
         return worldObj.getTileEntity(xCoord + 1, yCoord, zCoord);
      else if (orientationController == ForgeDirection.SOUTH)
         return worldObj.getTileEntity(xCoord - 1, yCoord, zCoord);

      return null;
   }


   @Override
   protected TileEntityValve[] findInputValves() {
      return new TileEntityValve[]{(TileEntityValve) getInputTE()};
   }

   @Override
   protected TileEntityValve[] findOutputValves() {
      return new TileEntityValve[]{(TileEntityValve) getOutputTE()};
   }

   @Override
   protected TileEntityHatch[] findOutputHatches() {
      return null;
   }
}
