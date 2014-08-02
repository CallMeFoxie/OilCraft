package cz.ondraster.oilcraft.factory.tileentities;

import cz.ondraster.oilcraft.factory.blocks.BlockMachineValve;
import cz.ondraster.oilcraft.factory.FactoryBlocks;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.List;

public class TileEntityCrocker extends TileEntityController {
   @Override
   public void checkMultiblock() {
      List<TileEntity> checked = new ArrayList<TileEntity>();
      if (worldObj.isRemote)
         return;

      // bottom row = either all of them are solid heaters or all of them are electric heaters. Should be 1 below us.

      ForgeDirection orientationController = ForgeDirection.getOrientation(worldObj.getBlockMetadata(xCoord, yCoord, zCoord));
      ForgeDirection opposite = orientationController.getOpposite();

      boolean isOk = true;

      if (orientationController == ForgeDirection.EAST || orientationController == ForgeDirection.WEST) {
         for (int z = zCoord - 1; z <= zCoord + 1; z++) {
            int a = xCoord + opposite.offsetX;
            int b = xCoord;
            for (int x = Math.min(a, b); x <= Math.max(a, b); x++) {
               Block block = worldObj.getBlock(x, yCoord - 1, z);
               if (block != FactoryBlocks.blockMachineCasingHT)
                  isOk = false;
            }

            // check for solid blocks behind, left and right
            checked.add(worldObj.getTileEntity(xCoord, yCoord, zCoord - 1));
            Block block = worldObj.getBlock(xCoord, yCoord, zCoord - 1);
            if (block != FactoryBlocks.blockValveHT)
               isOk = false;

            checked.add(worldObj.getTileEntity(xCoord, yCoord, zCoord + 1));
            block = worldObj.getBlock(xCoord, yCoord, zCoord + 1);
            if (block != FactoryBlocks.blockValveHT)
               isOk = false;

            checked.add(worldObj.getTileEntity(xCoord + orientationController.getOpposite().offsetX, yCoord, zCoord));
            block = worldObj.getBlock(xCoord + orientationController.getOpposite().offsetX, yCoord, zCoord);
            if (block != FactoryBlocks.blockMachineCasingHT)
               isOk = false;

            // check for HT valves on the behind left and right
            checked.add(worldObj.getTileEntity(xCoord + orientationController.getOpposite().offsetX, yCoord, zCoord - 1));
            block = worldObj.getBlock(xCoord + orientationController.getOpposite().offsetX, yCoord, zCoord - 1);
            if (block != FactoryBlocks.blockValveHT)
               isOk = false;

            checked.add(worldObj.getTileEntity(xCoord + orientationController.getOpposite().offsetX, yCoord, zCoord + 1));
            block = worldObj.getBlock(xCoord + orientationController.getOpposite().offsetX, yCoord, zCoord + 1);
            if (block != FactoryBlocks.blockValveHT)
               isOk = false;

         }
      } else {
         for (int x = xCoord - 1; x <= xCoord + 1; x++) {
            int a = zCoord + opposite.offsetZ;
            int b = zCoord;
            for (int z = Math.min(a, b); z <= Math.max(a, b); z++) {
               Block block = worldObj.getBlock(x, yCoord - 1, z);
               if (block != FactoryBlocks.blockMachineCasingHT)
                  isOk = false;
            }
         }

         // check for solid blocks behind, left and right
         checked.add(worldObj.getTileEntity(xCoord - 1, yCoord, zCoord));
         Block block = worldObj.getBlock(xCoord - 1, yCoord, zCoord);
         if (block != FactoryBlocks.blockValveHT)
            isOk = false;

         checked.add(worldObj.getTileEntity(xCoord + 1, yCoord, zCoord));
         block = worldObj.getBlock(xCoord + 1, yCoord, zCoord);
         if (block != FactoryBlocks.blockValveHT)
            isOk = false;

         checked.add(worldObj.getTileEntity(xCoord, yCoord, zCoord + orientationController.getOpposite().offsetZ));
         block = worldObj.getBlock(xCoord, yCoord, zCoord + orientationController.getOpposite().offsetZ);
         if (block != FactoryBlocks.blockMachineCasingHT)
            isOk = false;

         // check for HT valves on the behind left and right
         checked.add(worldObj.getTileEntity(xCoord - 1, yCoord, zCoord + orientationController.getOpposite().offsetZ));
         block = worldObj.getBlock(xCoord - 1, yCoord, zCoord + orientationController.getOpposite().offsetZ);
         if (block != FactoryBlocks.blockValveHT)
            isOk = false;

         checked.add(worldObj.getTileEntity(xCoord + 1, yCoord, zCoord + orientationController.getOpposite().offsetZ));
         block = worldObj.getBlock(xCoord + 1, yCoord, zCoord + orientationController.getOpposite().offsetZ);
         if (block != FactoryBlocks.blockValveHT)
            isOk = false;
      }

      for (TileEntity te : checked) {
         if (te instanceof TileEntityPart) {
            if (((TileEntityPart) te).isComplete())
               isOk = false;
         } else if (te instanceof TileEntityPartWithInventory)
            if (((TileEntityPartWithInventory) te).isComplete())
               isOk = false;
      }

      if (isOk) {
         isComplete();
         markBlocks(checked);
      } else {
         reset();
         // blocks will reset on their own within a second
      }
   }

   @Override
   public void resetMultiblock() {
      ForgeDirection orientationController = ForgeDirection.getOrientation(worldObj.getBlockMetadata(xCoord, yCoord, zCoord));
      ForgeDirection opposite = orientationController.getOpposite();

      if (orientationController == ForgeDirection.EAST || orientationController == ForgeDirection.WEST) {
         for (int z = zCoord - 1; z <= zCoord + 1; z++) {
            int a = xCoord + opposite.offsetX;
            int b = xCoord;
            for (int x = Math.min(a, b); x <= Math.max(a, b); x++) {
               resetBlock(x, yCoord - 1, z);
            }

            resetBlock(xCoord, yCoord, zCoord - 1);
            resetBlock(xCoord, yCoord, zCoord + 1);

            resetBlock(xCoord + orientationController.getOpposite().offsetX, yCoord, zCoord);
            resetBlock(xCoord + orientationController.getOpposite().offsetX, yCoord, zCoord - 1);
            resetBlock(xCoord + orientationController.getOpposite().offsetX, yCoord, zCoord + 1);
         }
      } else {
         for (int x = xCoord - 1; x <= xCoord + 1; x++) {
            int a = zCoord + opposite.offsetZ;
            int b = zCoord;
            for (int z = Math.min(a, b); z <= Math.max(a, b); z++) {
               resetBlock(x, yCoord - 1, z);
            }
         }
         resetBlock(xCoord - 1, yCoord, zCoord);
         resetBlock(xCoord + 1, yCoord, zCoord);

         resetBlock(xCoord, yCoord, zCoord + orientationController.getOpposite().offsetZ);
         resetBlock(xCoord - 1, yCoord, zCoord + orientationController.getOpposite().offsetZ);
         resetBlock(xCoord + 1, yCoord, zCoord + orientationController.getOpposite().offsetZ);
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

   @Override
   protected TileEntityValve[] findInputValves() {
      TileEntity[] valves = getValves();
      List<TileEntityValve> retValves = new ArrayList<TileEntityValve>();
      for (TileEntity valve : valves)
         if (!BlockMachineValve.isExport(worldObj.getBlockMetadata(valve.xCoord, valve.yCoord, valve.zCoord)))
            retValves.add((TileEntityValve) valve);

      valves = new TileEntityValve[retValves.size()];
      retValves.toArray(valves);

      return (TileEntityValve[]) valves;
   }

   @Override
   protected TileEntityValve[] findOutputValves() {
      TileEntity[] valves = getValves();
      List<TileEntityValve> retValves = new ArrayList<TileEntityValve>();
      for (TileEntity valve : valves)
         if (BlockMachineValve.isExport(worldObj.getBlockMetadata(valve.xCoord, valve.yCoord, valve.zCoord)))
            if (!BlockMachineValve.isExport(worldObj.getBlockMetadata(valve.xCoord, valve.yCoord, valve.zCoord)))
               retValves.add((TileEntityValve) valve);

      valves = new TileEntityValve[retValves.size()];
      retValves.toArray(valves);

      return (TileEntityValve[]) valves;
   }

   private TileEntity[] getValves() {
      TileEntity[] valves = new TileEntity[4];
      ForgeDirection orientationController = ForgeDirection.getOrientation(worldObj.getBlockMetadata(xCoord, yCoord, zCoord));
      if (orientationController == ForgeDirection.EAST || orientationController == ForgeDirection.WEST) {
         valves[0] = worldObj.getTileEntity(xCoord + orientationController.getOpposite().offsetX, yCoord, zCoord - 1);
         valves[1] = worldObj.getTileEntity(xCoord + orientationController.getOpposite().offsetX, yCoord, zCoord + 1);
         valves[2] = worldObj.getTileEntity(xCoord, yCoord, zCoord + 1);
         valves[3] = worldObj.getTileEntity(xCoord, yCoord, zCoord - 1);
      } else {
         valves[0] = worldObj.getTileEntity(xCoord - 1, yCoord, zCoord + orientationController.getOpposite().offsetZ);
         valves[1] = worldObj.getTileEntity(xCoord + 1, yCoord, zCoord + orientationController.getOpposite().offsetZ);
         valves[2] = worldObj.getTileEntity(xCoord, yCoord, zCoord + 1);
         valves[3] = worldObj.getTileEntity(xCoord, yCoord, zCoord - 1);
      }

      return valves;
   }

   @Override
   protected TileEntityHatch[] findOutputHatches() {
      return null;
   }
}
