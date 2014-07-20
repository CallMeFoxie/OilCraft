package cz.ondraster.oilcraft.factory.tileentities;

import cz.ondraster.oilcraft.factory.blocks.FactoryBlocks;
import cz.ondraster.oilcraft.fluids.Fluids;
import cz.ondraster.oilcraft.items.OilItems;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;

public class TileEntityDistillator extends TileEntityController {
   // this multiblock is 3x3 and on any level there has to be exactly ONE valve - for bottom 5 HT stuff,
   //   for upper 4 normal stuff.
   // Second layer also has to have one HATCH. Controller is on the bottom row, middle. Input valve has to be in
   // any bottom middle.           Sides can be made out of HT/normal blocks or valves, nobody cares.
   // middle column has to be empty.

   private static final int IN_MB_PER_OPERATION = 100;
   private static final int BUTANE_PER_OPERATION = 5;
   private static final int PETROLEUM_PER_OPERATION = 10;
   private static final int KEROSENE_PER_OPERATION = 10;
   private static final int DIESEL_PER_OPERATION = 10;
   private static final int FUEL_PER_OPERATION = 35;
   private static final int LUBRICANT_PER_OPERATION = 5;
   private static final int ASPHALT_PER_OPERATION = 20;
   private static final int[] additions = {ASPHALT_PER_OPERATION, LUBRICANT_PER_OPERATION,
         FUEL_PER_OPERATION, DIESEL_PER_OPERATION, KEROSENE_PER_OPERATION,
         PETROLEUM_PER_OPERATION, BUTANE_PER_OPERATION};
   private static final int PARAFFIN_PER_OPERATION = 5;

   private static final Fluid[] addFluids = {Fluids.fluidRAsphalt, Fluids.fluidLubricant, Fluids.fluidRFuel, Fluids.fluidRDiesel,
         Fluids.fluidRKerosene, Fluids.fluidRPetroleum, Fluids.fluidRButane};

   private int bufferParaffin = 0;
   private int ticksSinceUpdate = 0;

   @Override
   public void checkMultiblock() {
      List<TileEntity> checked = new ArrayList<TileEntity>();
      if (worldObj.isRemote)
         return;

      ForgeDirection orientation = ForgeDirection.getOrientation(worldObj.getBlockMetadata(xCoord, yCoord, zCoord));
      ForgeDirection opposite = orientation.getOpposite();

      boolean isOk = true;

      int xMiddle = xCoord + opposite.offsetX;
      int yMiddle = yCoord;
      int zMiddle = zCoord + opposite.offsetZ;

      // check bottom   -- 1x valve in the middle, 1x
      boolean foundValve = false, foundHatch = false;
      for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
         if (dir != ForgeDirection.UP && dir != ForgeDirection.DOWN) {
            TileEntity te = worldObj.getTileEntity(xMiddle + dir.offsetX, yCoord, zMiddle + dir.offsetZ);
            if (te instanceof TileEntityValveHT) {
               if (!foundValve)
                  foundValve = true;
               else {
                  isOk = false;
               }
            }
         }
      }

      if (!foundValve)                 // found >1 valve or 0 valves.
         isOk = false;

      // now re-check the bottom layer
      for (DirectionsExpanded dir : DirectionsExpanded.DIRECTIONS) {
         TileEntity te = worldObj.getTileEntity(xMiddle + dir.offsetX, yMiddle, zMiddle + dir.offsetZ);
         Block block = worldObj.getBlock(xMiddle + dir.offsetX, yMiddle, zMiddle + dir.offsetZ);
         if (!(block == FactoryBlocks.blockValveHT || block == FactoryBlocks.controllerDistillator || block == FactoryBlocks.blockMachineCasingHT)) {
            isOk = false;
         }
         checked.add(te);
      }
      // check middle piece
      Block block = worldObj.getBlock(xMiddle, yMiddle, zMiddle);
      checked.add(worldObj.getTileEntity(xMiddle, yMiddle, zMiddle));
      if (block != FactoryBlocks.blockMachineCasingHT)
         isOk = false;

      foundValve = false;
      foundHatch = false;

      // check second row -- valve + one hatch
      for (DirectionsExpanded dir : DirectionsExpanded.DIRECTIONS) {
         Block b = worldObj.getBlock(xMiddle + dir.offsetX, yMiddle + 1, zMiddle + dir.offsetZ);
         checked.add(worldObj.getTileEntity(xMiddle + dir.offsetX, yMiddle + 1, zMiddle + dir.offsetZ));
         if (b == FactoryBlocks.blockValveHT) {
            if (foundValve)
               isOk = false;
            else
               foundValve = true;
         } else if (b == FactoryBlocks.blockMachineCasingHT) {
            // OK
         } else if (b == FactoryBlocks.blockHatch) {
            if (foundHatch)
               isOk = false;
            else
               foundHatch = true;
         } else
            isOk = false;

      }
      if (!foundValve || !foundHatch)
         isOk = false;

      if (!worldObj.isAirBlock(xMiddle, yMiddle + 1, zMiddle))
         isOk = false;

      // now check bottom yMaster+2    +4 layers for HT blocks
      boolean retval = true;
      for (int i = 0; i < 3; i++) {
         retval = checkLevel(xMiddle, yMiddle + i + 2, zMiddle, true, checked);
         if (!retval)
            isOk = false;
      }
      // now check the remaining yMaster+4  +3 layers for nonHT blocks -- last one has the filling in the middle
      for (int i = 0; i < 3; i++) {
         retval = checkLevel(xMiddle, yMiddle + i + 5, zMiddle, false, checked);
         if (!retval)
            isOk = false;
      }

      foundValve = false;
      // check the top layer
      for (DirectionsExpanded dir : DirectionsExpanded.DIRECTIONS) {
         Block b = worldObj.getBlock(xMiddle + dir.offsetX, yMiddle + 8, zMiddle + dir.offsetZ);
         if (b == FactoryBlocks.blockMachineCasing) {
            // OK
         } else
            isOk = false;
         checked.add(worldObj.getTileEntity(xMiddle + dir.offsetX, yMiddle + 8, zMiddle + dir.offsetZ));
      }
      Block b = worldObj.getBlock(xMiddle, yMiddle + 8, zMiddle);
      checked.add(worldObj.getTileEntity(xMiddle, yMiddle + 8, zMiddle));
      if (b != FactoryBlocks.blockMachineCasing)
         isOk = false;


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
      if (worldObj.isRemote)
         return;

      ForgeDirection orientation = ForgeDirection.getOrientation(worldObj.getBlockMetadata(xCoord, yCoord, zCoord));
      ForgeDirection opposite = orientation.getOpposite();

      int xMiddle = xCoord + opposite.offsetX;
      int yMiddle = yCoord;
      int zMiddle = zCoord + opposite.offsetZ;

      for (int i = 0; i < 9; i++) {
         resetLevel(xMiddle, yMiddle + i, zMiddle);
         resetBlock(xMiddle, yMiddle + i, zMiddle);
      }
   }

   private boolean checkLevel(int xMaster, int yLevel, int zMaster, boolean isHT, List<TileEntity> checked) {
      boolean isOk = true;
      boolean foundValve = false;
      for (DirectionsExpanded dir : DirectionsExpanded.DIRECTIONS) {
         Block b = worldObj.getBlock(xMaster + dir.offsetX, yLevel, zMaster + dir.offsetZ);
         if ((isHT && b == FactoryBlocks.blockValveHT) || (!isHT && b == FactoryBlocks.blockValve)) {
            if (foundValve)
               isOk = false;
            else
               foundValve = true;
         } else if ((isHT && b == FactoryBlocks.blockMachineCasingHT) || (!isHT && b == FactoryBlocks.blockMachineCasing)) {
            // OK
         } else
            isOk = false;
         checked.add(worldObj.getTileEntity(xMaster + dir.offsetX, yLevel, zMaster + dir.offsetZ));
      }

      return isOk && worldObj.isAirBlock(xMaster, yLevel, zMaster);
   }

   private void resetLevel(int xMaster, int yLevel, int zMaster) {
      for (DirectionsExpanded dir : DirectionsExpanded.DIRECTIONS) {
         resetBlock(xMaster + dir.offsetX, yLevel, zMaster + dir.offsetZ);
      }
   }

   private TileEntityValve findValve(int yLevel) {
      ForgeDirection orientation = ForgeDirection.getOrientation(worldObj.getBlockMetadata(xCoord, yCoord, zCoord));
      ForgeDirection opposite = orientation.getOpposite();

      int xMiddle = xCoord + opposite.offsetX;
      int yMiddle = yCoord;
      int zMiddle = zCoord + opposite.offsetZ;

      for (DirectionsExpanded dir : DirectionsExpanded.DIRECTIONS) {
         TileEntity te = worldObj.getTileEntity(xMiddle + dir.offsetX, yMiddle + yLevel, zMiddle + dir.offsetZ);
         if (te instanceof TileEntityValve)
            return (TileEntityValve) te;
      }


      return null;
   }

   private TileEntityHatch findHatch(int yLevel) {
      ForgeDirection orientation = ForgeDirection.getOrientation(worldObj.getBlockMetadata(xCoord, yCoord, zCoord));
      ForgeDirection opposite = orientation.getOpposite();

      int xMiddle = xCoord + opposite.offsetX;
      int yMiddle = yCoord;
      int zMiddle = zCoord + opposite.offsetZ;

      for (DirectionsExpanded dir : DirectionsExpanded.DIRECTIONS) {
         TileEntity te = worldObj.getTileEntity(xMiddle + dir.offsetX, yMiddle + yLevel, zMiddle + dir.offsetZ);
         if (te instanceof TileEntityHatch)
            return (TileEntityHatch) te;
      }

      return null;
   }

   @Override
   public void doWork() {
      if (ticksSinceUpdate >= 10) {
         ticksSinceUpdate = 0;
         boolean isOk = true;

         TileEntityValve input = findValve(0);
         TileEntityHatch hatch = findHatch(1);

         if (!(hatch.getStackInSlot(0) == null || (hatch.getStackInSlot(0).stackSize < 64 && hatch.getStackInSlot(0).getItem() == OilItems.dustParaffin))) {

         } else {
            if (input.drain(ForgeDirection.UNKNOWN, IN_MB_PER_OPERATION, false).amount == IN_MB_PER_OPERATION) {

               // pre-flight checks for all the valves... *yawn*
               for (int i = 0; i < additions.length; i++) {
                  TileEntityValve valve = findValve(i + 1);
                  if (valve.fill(ForgeDirection.UNKNOWN, new FluidStack(addFluids[i], additions[i]), false) < additions[i]) {
                     isOk = false;
                  }
               }

               if (isOk) {
                  for (int i = 0; i < additions.length; i++) {
                     TileEntityValve valve = findValve(i + 1);
                     valve.fill(ForgeDirection.UNKNOWN, new FluidStack(addFluids[i], additions[i]), true);
                     worldObj.markBlockForUpdate(valve.xCoord, valve.yCoord, valve.zCoord);
                  }

                  input.drain(ForgeDirection.UNKNOWN, IN_MB_PER_OPERATION, true);
                  worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                  bufferParaffin += PARAFFIN_PER_OPERATION;
                  if (bufferParaffin >= 1000) {
                     if (hatch.getStackInSlot(0) == null)
                        hatch.setInventorySlotContents(0, new ItemStack(OilItems.dustParaffin, 1));
                     else
                        hatch.setInventorySlotContents(0, new ItemStack(OilItems.dustParaffin, hatch.getStackInSlot(0).stackSize + 1));

                     bufferParaffin -= 1000;
                  }
               }
            }
         }
      }

      ticksSinceUpdate++;
   }

   @Override
   protected void save(NBTTagCompound nbt) {
      super.save(nbt);
      nbt.setInteger("bufferParaffin", bufferParaffin);
   }

   @Override
   protected void load(NBTTagCompound nbt) {
      super.load(nbt);
      nbt.setInteger("bufferParaffin", bufferParaffin);

   }

   @Override
   protected TileEntityValve[] findInputValves() {
      return new TileEntityValve[]{findValve(0)};
   }

   @Override
   protected TileEntityValve[] findOutputValves() {
      TileEntityValve[] valves = new TileEntityValve[additions.length];
      for (int i = 0; i < additions.length; i++)
         valves[i] = findValve(i + 1);
      return valves;
   }

   @Override
   protected TileEntityHatch[] findOutputHatches() {
      return new TileEntityHatch[]{findHatch(1)};
   }

   private enum DirectionsExpanded {

      EAST(1, 0),
      WEST(-1, 0),
      NORTH(0, -1),
      SOUTH(0, 1),
      NORTHEAST(1, -1),
      SOUTHEAST(1, 1),
      NORTHWEST(-1, -1),
      SOUTHWEST(-1, 1);

      public static final DirectionsExpanded[] DIRECTIONS = {EAST, WEST, NORTH, SOUTH, NORTHEAST, SOUTHEAST, NORTHWEST, SOUTHWEST};

      public int offsetX;
      public int offsetZ;

      private DirectionsExpanded(int offsetX, int offsetZ) {
         this.offsetX = offsetX;
         this.offsetZ = offsetZ;
      }
   }
}
