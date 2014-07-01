package cz.ondraster.oilcraft.entities;

import cz.ondraster.oilcraft.fluids.Fluids;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.*;

public class EntityOiljackPipe extends TileEntity implements IFluidTank, IFluidHandler {

   public final static int MAXIMUM_SIZE = 2000;
   public final static int RANGE = 16;
   int internalTank = 0;
   int depth = 0;

   public boolean digOil() {
      if (worldObj.isRemote)
         return false;

      boolean didDig = false;
      if (internalTank + 1000 > MAXIMUM_SIZE) {
         return false;
      }

      for (int x = -RANGE; x <= RANGE; x++) {
         for (int z = -RANGE; z <= RANGE; z++) {
            if (!didDig) {
               Block block = worldObj.getBlock(xCoord + x, yCoord - depth - 1, zCoord + z);
               if (block == Fluids.blockFluidCrudeOil && worldObj.getBlockMetadata(xCoord + x, yCoord - depth - 1, zCoord + z) == 0) {
                  didDig = true;
                  worldObj.setBlock(xCoord + x, yCoord - depth - 1, zCoord + z, Blocks.air, 0, 3);
               }
            }
         }
      }

      if (didDig) {
         internalTank += 1000;
         return true;
      } else {
         Block block = worldObj.getBlock(xCoord, yCoord - depth - 2, zCoord);
         if (block == Blocks.air) {
            depth++;
         } else if (block == Fluids.blockFluidCrudeOil) {
            internalTank += 1000;
            didDig = true;
            worldObj.setBlock(xCoord, yCoord - depth - 2, zCoord, Blocks.air, 0, 3);
            depth++;
         }
      }

      return didDig;
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

      return new FluidStack(Fluids.fluidCrudeOil, internalTank);
   }

   @Override
   public int getFluidAmount() {
      return internalTank;
   }

   @Override
   public int getCapacity() {
      return MAXIMUM_SIZE;
   }

   @Override
   public FluidTankInfo getInfo() {
      FluidTankInfo fluidTankInfo = new FluidTankInfo(this);

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

   @Override
   public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
      return 0;
   }

   @Override
   public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
      if (resource.getFluid() == Fluids.fluidCrudeOil)
         return drain(resource.amount, doDrain);
      else
         return null;
   }

   @Override
   public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
      return drain(maxDrain, doDrain);
   }

   @Override
   public boolean canFill(ForgeDirection from, Fluid fluid) {
      return false;
   }

   @Override
   public boolean canDrain(ForgeDirection from, Fluid fluid) {
      return fluid == Fluids.fluidCrudeOil;
   }

   @Override
   public FluidTankInfo[] getTankInfo(ForgeDirection from) {
      FluidTankInfo info = getInfo();
      return new FluidTankInfo[]{info};
   }
}
