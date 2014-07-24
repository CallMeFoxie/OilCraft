package cz.ondraster.oilcraft.factory.tileentities;

import cz.ondraster.oilcraft.factory.blocks.BlockMachineValve;
import cz.ondraster.oilcraft.factory.blocks.FactoryBlocks;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.List;

public class TileEntityGasProcessor extends TileEntityController {
   @Override
   public void checkMultiblock() {
      List<TileEntity> checked = new ArrayList<TileEntity>();
      if (worldObj.isRemote)
         return;

      boolean isOk = true;

      TileEntity[] valves = getValves();
      if (valves[0] == null || valves[1] == null)
         isOk = false;
      else if (!(valves[0] instanceof TileEntityValve) || !(valves[0] instanceof TileEntityValve))
         isOk = false;

      checked.add(valves[0]);
      checked.add(valves[1]);

      ForgeDirection orientationController = ForgeDirection.getOrientation(worldObj.getBlockMetadata(xCoord, yCoord, zCoord));
      Block block;

      if (orientationController == ForgeDirection.NORTH || orientationController == ForgeDirection.SOUTH) {
         checked.add(worldObj.getTileEntity(xCoord - 1, yCoord, zCoord));
         block = worldObj.getBlock(xCoord - 1, yCoord, zCoord);
         if (block != FactoryBlocks.blockMachineCasing)
            isOk = false;

         checked.add(worldObj.getTileEntity(xCoord + 1, yCoord, zCoord));
         block = worldObj.getBlock(xCoord + 1, yCoord, zCoord);
         if (block != FactoryBlocks.blockMachineCasing)
            isOk = false;
      } else {
         checked.add(worldObj.getTileEntity(xCoord, yCoord, zCoord - 1));
         block = worldObj.getBlock(xCoord, yCoord, zCoord - 1);
         if (block != FactoryBlocks.blockMachineCasing)
            isOk = false;

         checked.add(worldObj.getTileEntity(xCoord, yCoord, zCoord + 1));
         block = worldObj.getBlock(xCoord, yCoord, zCoord + 1);
         if (block != FactoryBlocks.blockMachineCasing)
            isOk = false;
      }

      checked.add(worldObj.getTileEntity(xCoord + orientationController.getOpposite().offsetX, yCoord, zCoord + orientationController.getOpposite().offsetZ));
      block = worldObj.getBlock(xCoord + orientationController.getOpposite().offsetX, yCoord, zCoord + orientationController.getOpposite().offsetZ);
      if (block != FactoryBlocks.blockMachineCasing)
         isOk = false;

      checked.add(worldObj.getTileEntity(xCoord + orientationController.getOpposite().offsetX, yCoord + 1, zCoord + orientationController.getOpposite().offsetZ));
      block = worldObj.getBlock(xCoord + orientationController.getOpposite().offsetX, yCoord + 1, zCoord + orientationController.getOpposite().offsetZ);
      if (block != FactoryBlocks.blockHatch)
         isOk = false;

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
      if (orientationController == ForgeDirection.EAST || orientationController == ForgeDirection.WEST) {
         resetBlock(xCoord - 1, yCoord, zCoord);
         resetBlock(xCoord + 1, yCoord, zCoord);
         resetBlock(xCoord - 1, yCoord + 1, zCoord);
         resetBlock(xCoord + 1, yCoord + 1, zCoord);
      } else {
         resetBlock(xCoord, yCoord, zCoord + 1);
         resetBlock(xCoord, yCoord, zCoord - 1);
         resetBlock(xCoord, yCoord + 1, zCoord + 1);
         resetBlock(xCoord, yCoord + 1, zCoord - 1);
      }

      resetBlock(getOrientation().getOpposite().offsetX + xCoord, yCoord, zCoord + getOrientation().getOpposite().offsetZ);
      resetBlock(getOrientation().getOpposite().offsetX + xCoord, yCoord + 1, zCoord + getOrientation().getOpposite().offsetZ);
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

   private TileEntity[] getValves() {
      TileEntity[] valves = new TileEntity[2];
      ForgeDirection orientationController = getOrientation();

      if (orientationController == ForgeDirection.EAST) {
         valves[0] = worldObj.getTileEntity(xCoord, yCoord + 1, zCoord - 1);
         valves[1] = worldObj.getTileEntity(xCoord, yCoord + 1, zCoord + 1);
      } else if (orientationController == ForgeDirection.WEST) {
         valves[0] = worldObj.getTileEntity(xCoord, yCoord + 1, zCoord + 1);
         valves[1] = worldObj.getTileEntity(xCoord, yCoord + 1, zCoord - 1);
      } else if (orientationController == ForgeDirection.NORTH) {
         valves[0] = worldObj.getTileEntity(xCoord - 1, yCoord + 1, zCoord);
         valves[1] = worldObj.getTileEntity(xCoord + 1, yCoord + 1, zCoord);
      } else if (orientationController == ForgeDirection.SOUTH) {
         valves[0] = worldObj.getTileEntity(xCoord + 1, yCoord + 1, zCoord);
         valves[1] = worldObj.getTileEntity(xCoord - 1, yCoord + 1, zCoord);
      }

      return valves;
   }

   @Override
   protected TileEntityValve[] findInputValves() {
      TileEntity[] valves = getValves();
      for (TileEntity valve : valves)
         if (!BlockMachineValve.isExport(worldObj.getBlockMetadata(valve.xCoord, valve.yCoord, valve.zCoord)))
            return new TileEntityValve[]{(TileEntityValve) valve};

      return null;
   }

   @Override
   protected TileEntityValve[] findOutputValves() {
      TileEntity[] valves = getValves();
      for (TileEntity valve : valves)
         if (BlockMachineValve.isExport(worldObj.getBlockMetadata(valve.xCoord, valve.yCoord, valve.zCoord)))
            return new TileEntityValve[]{(TileEntityValve) valve};

      return null;
   }

   @Override
   protected TileEntityHatch[] findOutputHatches() {
      ForgeDirection orientation = getOrientation().getOpposite();
      return new TileEntityHatch[]{(TileEntityHatch) worldObj.getTileEntity(xCoord + orientation.offsetX, yCoord + 1, zCoord + orientation.offsetZ)};
   }
}
