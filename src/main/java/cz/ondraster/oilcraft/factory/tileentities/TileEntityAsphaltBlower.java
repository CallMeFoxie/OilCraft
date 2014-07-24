package cz.ondraster.oilcraft.factory.tileentities;

import cz.ondraster.oilcraft.factory.blocks.BlockMachineValve;
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

      TileEntity[] valves = getValves();
      if (valves[0] == null || valves[1] == null)
         isOk = false;
      else if (!(valves[0] instanceof TileEntityValve) || !(valves[0] instanceof TileEntityValve))
         isOk = false;

      checked.add(valves[0]);
      checked.add(valves[1]);

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

   private TileEntity[] getValves() {
      TileEntity[] valves = new TileEntity[2];
      ForgeDirection orientationController = ForgeDirection.getOrientation(worldObj.getBlockMetadata(xCoord, yCoord, zCoord));
      if (orientationController == ForgeDirection.EAST) {
         valves[0] = worldObj.getTileEntity(xCoord, yCoord, zCoord - 1);
         valves[1] = worldObj.getTileEntity(xCoord, yCoord, zCoord + 1);
      } else if (orientationController == ForgeDirection.WEST) {
         valves[0] = worldObj.getTileEntity(xCoord, yCoord, zCoord + 1);
         valves[1] = worldObj.getTileEntity(xCoord, yCoord, zCoord - 1);
      } else if (orientationController == ForgeDirection.NORTH) {
         valves[0] = worldObj.getTileEntity(xCoord - 1, yCoord, zCoord);
         valves[1] = worldObj.getTileEntity(xCoord + 1, yCoord, zCoord);
      } else if (orientationController == ForgeDirection.SOUTH) {
         valves[0] = worldObj.getTileEntity(xCoord + 1, yCoord, zCoord);
         valves[1] = worldObj.getTileEntity(xCoord - 1, yCoord, zCoord);
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
      return null;
   }
}
