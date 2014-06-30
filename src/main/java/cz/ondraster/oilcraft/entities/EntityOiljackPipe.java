package cz.ondraster.oilcraft.entities;

import cz.ondraster.oilcraft.fluids.Fluids;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidTank;

/**
 * Created by Ondra on 30.6.2014.
 */
public class EntityOiljackPipe extends TileEntity implements IFluidTank {

   int internalTank = 0;
   int depth = 0;

   public boolean digOil() {
      boolean canDig = false;

      for (int x = -1; x < 1; x++) {
         for (int z = -1; z < 1; z++) {
            if (worldObj.getBlock(xCoord + x, yCoord - depth - 1, zCoord + z) != Blocks.air) {
               System.out.println("Found " + worldObj.getBlock(xCoord + x, yCoord - depth - 1, zCoord + z).toString());
            }
         }
      }

      if (canDig) {
         internalTank += 1000;
         return true;
      }

      return false;
   }

   @Override
   public void readFromNBT(NBTTagCompound nbt) {
      super.readFromNBT(nbt);
      this.internalTank = nbt.getInteger("internalTank");
      this.depth = nbt.getInteger("depth");
   }

   @Override
   public void writeToNBT(NBTTagCompound nbt) {
      super.writeToNBT(nbt);
      nbt.setInteger("internalTank", internalTank);
      nbt.setInteger("depth", depth);
   }

   @Override
   public FluidStack getFluid() {
      if (internalTank == 0)
         return null;

      return new FluidStack(Fluids.fluidCrudeOil, 0);
   }

   @Override
   public int getFluidAmount() {
      return internalTank;
   }

   @Override
   public int getCapacity() {
      return 2000;
   }

   @Override
   public FluidTankInfo getInfo() {
      FluidTankInfo fluidTankInfo = new FluidTankInfo(new FluidStack(Fluids.fluidCrudeOil, 0), 2000);

      return fluidTankInfo;
   }

   @Override
   public int fill(FluidStack resource, boolean doFill) {
      return 0;
   }

   @Override
   public FluidStack drain(int maxDrain, boolean doDrain) {
      FluidStack toReturn = null;

      if (internalTank == 0) {
         toReturn = null;
      } else if (maxDrain > internalTank) {
         toReturn = new FluidStack(Fluids.fluidCrudeOil, internalTank);
      } else if (maxDrain <= internalTank) {
         toReturn = new FluidStack(Fluids.fluidCrudeOil, maxDrain);
      }

      if (toReturn != null && doDrain) {
         internalTank -= toReturn.amount;
      }

      return toReturn;
   }
}
