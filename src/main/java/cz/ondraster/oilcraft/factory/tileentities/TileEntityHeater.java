package cz.ondraster.oilcraft.factory.tileentities;

import cz.ondraster.oilcraft.factory.IMachineRequiresPower;
import cz.ondraster.oilcraft.factory.blocks.BlockMachineFirebox;
import cz.ondraster.oilcraft.factory.blocks.BlockMachineFireboxSolid;
import cz.ondraster.oilcraft.factory.blocks.FactoryBlocks;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.List;

public class TileEntityHeater extends TileEntityController implements IMachineRequiresPower {
   private final static int POWER_MAX = 100000;
   private static final int PROCESS_MAX = 200;
   private static final int POWER_PER_OPERATION = 1000;
   private int power;

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
      List<TileEntity> checked = new ArrayList<TileEntity>();
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
               Block block = worldObj.getBlock(x, yCoord - 1, z);
               if (bottomHeater != null && block != bottomHeater) {
                  isOk = false;
               } else if (!((block instanceof BlockMachineFirebox) || (block instanceof BlockMachineFireboxSolid))) {
                  isOk = false;
               } else {
                  bottomHeater = block;
                  checked.add(worldObj.getTileEntity(x, yCoord - 1, z));
               }
            }

            // check for solid blocks behind, left and right
            checked.add(worldObj.getTileEntity(xCoord, yCoord, zCoord - 1));
            Block block = worldObj.getBlock(xCoord, yCoord, zCoord - 1);
            if (block != FactoryBlocks.blockMachineCasingHT)
               isOk = false;

            checked.add(worldObj.getTileEntity(xCoord, yCoord, zCoord + 1));
            block = worldObj.getBlock(xCoord, yCoord, zCoord + 1);
            if (block != FactoryBlocks.blockMachineCasingHT)
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
               if (bottomHeater != null && block != bottomHeater) {
                  isOk = false;
               } else if (!((block instanceof BlockMachineFirebox) || (block instanceof BlockMachineFireboxSolid))) {
                  isOk = false;
               } else {
                  checked.add(worldObj.getTileEntity(x, yCoord - 1, z));
                  bottomHeater = block;
               }
            }
         }

         // check for solid blocks behind, left and right
         checked.add(worldObj.getTileEntity(xCoord - 1, yCoord, zCoord));
         Block block = worldObj.getBlock(xCoord - 1, yCoord, zCoord);
         if (block != FactoryBlocks.blockMachineCasingHT)
            isOk = false;

         checked.add(worldObj.getTileEntity(xCoord + 1, yCoord, zCoord));
         block = worldObj.getBlock(xCoord + 1, yCoord, zCoord);
         if (block != FactoryBlocks.blockMachineCasingHT)
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
            if (((TileEntityPartWithInventory) te).isComplete)
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
      return getPower() >= POWER_PER_OPERATION;
   }

   @Override
   public void beforeWork() {

   }

   @Override
   public void afterWork(boolean success) {
      if (success)
         drainPower(POWER_PER_OPERATION);
   }


   @Override
   public boolean addPower(int amount) {
      if (amount + power <= POWER_MAX) {
         power += amount;
         return true;
      }

      return false;
   }

   public int getPower() {
      return power;
   }

   @Override
   public void save(NBTTagCompound nbt) {
      super.save(nbt);
      nbt.setInteger("power", power);
   }

   @Override
   public void load(NBTTagCompound nbtTagCompound) {
      super.load(nbtTagCompound);
      power = nbtTagCompound.getInteger("power");
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

   private TileEntity getInputTE() {
      ForgeDirection orientationController = ForgeDirection.getOrientation(worldObj.getBlockMetadata(xCoord, yCoord, zCoord));
      if (orientationController == ForgeDirection.EAST || orientationController == ForgeDirection.WEST) {
         return worldObj.getTileEntity(xCoord + orientationController.getOpposite().offsetX, yCoord, zCoord - 1);
      } else
         return worldObj.getTileEntity(xCoord - 1, yCoord, zCoord + orientationController.getOpposite().offsetZ);
   }

   private TileEntity getOutputTE() {
      ForgeDirection orientationController = ForgeDirection.getOrientation(worldObj.getBlockMetadata(xCoord, yCoord, zCoord));
      if (orientationController == ForgeDirection.EAST || orientationController == ForgeDirection.WEST) {
         return worldObj.getTileEntity(xCoord + orientationController.getOpposite().offsetX, yCoord, zCoord + 1);
      } else
         return worldObj.getTileEntity(xCoord + 1, yCoord, zCoord + orientationController.getOpposite().offsetZ);
   }

   private void drainPower(int i) {
      this.power -= i;
      if (power < 0)
         power = 0;
   }
}
