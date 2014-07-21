package cz.ondraster.oilcraft.factory.tileentities;

import cz.ondraster.oilcraft.Helper;
import cz.ondraster.oilcraft.factory.multiblock.MultiblockController;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;

public abstract class TileEntityController extends TileEntity {

   protected boolean isFormed = false;
   protected int workTimeout = 10; // how often (how many ticks apart) will the machine work. Can be overridden in subclasses

   private int ticksSinceWork = 0;
   private int lastRecipe = -1;


   protected void resetBlock(int x, int y, int z) {
      TileEntity te = worldObj.getTileEntity(x, y, z);
      if (te instanceof TileEntityPart) {
         ((TileEntityPart) te).unmarkComplete();
      }
      if (te instanceof TileEntityPartWithInventory) {
         ((TileEntityPartWithInventory) te).unmarkComplete();
      }
   }

   public abstract void checkMultiblock();

   public abstract void resetMultiblock();

   public abstract boolean canWork();

   public abstract void beforeWork();

   public abstract void afterWork(boolean success);

   public MultiblockController.ProcessingFluid[] getRecipes() {
      Block block = worldObj.getBlock(xCoord, yCoord, zCoord);
      if (!(block instanceof MultiblockController))
         return null;

      List<MultiblockController.ProcessingFluid> processingFluids = ((MultiblockController) block).getProcessingFluids();

      MultiblockController.ProcessingFluid[] xrecipes = new MultiblockController.ProcessingFluid[processingFluids.size()];
      processingFluids.toArray(xrecipes);

      return xrecipes;
   }

   public void doWork() {
      if (ticksSinceWork >= workTimeout && canWork()) {

         beforeWork();

         MultiblockController.ProcessingFluid[] recipes = getRecipes();
         boolean didWork = false;

         if (lastRecipe != -1) {
            if (canApplyRecipe(recipes[lastRecipe])) {
               applyRecipe(recipes[lastRecipe]);
               didWork = true;
            }
         }

         for (int i = 0; i < recipes.length && !didWork; i++) {
            if (canApplyRecipe(recipes[i])) {
               applyRecipe(recipes[i]);
               lastRecipe = i;
               didWork = true;
            }
         }

         afterWork(didWork);

         ticksSinceWork = 0;
      }

      ticksSinceWork++;
   }

   private void applyRecipe(MultiblockController.ProcessingFluid recipe) {
      TileEntityValve[] inputs = findInputValves();
      TileEntityValve[] outputs = findOutputValves();
      TileEntityHatch[] itemOutputs = findOutputHatches();

      for (int i = 0; i < recipe.fluidInputs.length; i++) {
         inputs[i].drain(ForgeDirection.UNKNOWN, recipe.fluidInputs[i], true);
         inputs[i].markDirty();
         worldObj.markBlockForUpdate(inputs[i].xCoord, inputs[i].yCoord, inputs[i].zCoord);
      }


      for (int i = 0; i < recipe.fluidOutputs.length; i++) {
         outputs[i].fill(ForgeDirection.UNKNOWN, recipe.fluidOutputs[i], true);
         outputs[i].markDirty();
         worldObj.markBlockForUpdate(outputs[i].xCoord, outputs[i].yCoord, outputs[i].zCoord);
      }

      if (itemOutputs != null)
         for (int i = 0; i < recipe.itemOutputs.length; i++) {
            itemOutputs[i].setInventorySlotContents(0, Helper.mergeStacks(itemOutputs[i].getStackInSlot(0), recipe.itemOutputs[i]));
            itemOutputs[i].markDirty();
            worldObj.markBlockForUpdate(itemOutputs[i].xCoord, itemOutputs[i].yCoord, itemOutputs[i].zCoord);
         }

   }

   protected boolean canApplyRecipe(MultiblockController.ProcessingFluid recipe) {
      TileEntityValve[] inputs = findInputValves();
      if (inputs.length != 0 && inputs.length != recipe.fluidInputs.length)
         return false; // do not have enough inputs or have too many

      TileEntityValve[] outputs = findOutputValves();
      if (outputs.length != 0 && outputs.length != recipe.fluidOutputs.length)
         return false;

      TileEntityHatch[] itemOutputs = findOutputHatches();
      if (itemOutputs != null && itemOutputs.length != recipe.itemOutputs.length)
         return false;

      for (int i = 0; i < recipe.fluidInputs.length; i++)
         if (!Helper.isProperFluid(inputs[i].getTankInfo(ForgeDirection.UNKNOWN)[0], recipe.fluidInputs[i]))
            return false;

      for (int i = 0; i < recipe.fluidOutputs.length; i++)
         if (!Helper.canFitIntoTank(outputs[i].getTankInfo(ForgeDirection.UNKNOWN)[0], recipe.fluidOutputs[i]))
            return false;

      if (itemOutputs != null)
         for (int i = 0; i < recipe.itemOutputs.length; i++)
            if (!Helper.canMergeStacks(itemOutputs[i].getStackInSlot(0), recipe.itemOutputs[i]))
               return false;


      return true;
   }

   public boolean isFormed() {
      return isFormed;
   }

   public void reset() {
      if (isFormed) {
         isFormed = false;
         resetMultiblock();
      }
   }

   public void isComplete() {
      this.isFormed = true;
   }

   protected void markBlocks(List<TileEntity> checked) {
      for (TileEntity te : checked) {
         if (te instanceof TileEntityPart)
            ((TileEntityPart) te).setMaster(xCoord, yCoord, zCoord);
         else if (te instanceof TileEntityPartWithInventory)
            ((TileEntityPartWithInventory) te).setMaster(xCoord, yCoord, zCoord);

      }
   }

   @Override
   public void updateEntity() {
      super.updateEntity();
      if (isFormed)
         doWork();
   }

   protected void save(NBTTagCompound nbt) {
      nbt.setBoolean("isFormed", isFormed);
   }

   protected void load(NBTTagCompound nbt) {
      isFormed = nbt.getBoolean("isFormed");
   }

   @Override
   public void writeToNBT(NBTTagCompound nbt) {
      super.writeToNBT(nbt);
      save(nbt);
   }

   @Override
   public void readFromNBT(NBTTagCompound nbt) {
      super.readFromNBT(nbt);
      load(nbt);
   }

   protected abstract TileEntityValve[] findInputValves();

   protected abstract TileEntityValve[] findOutputValves();

   protected abstract TileEntityHatch[] findOutputHatches();

}
