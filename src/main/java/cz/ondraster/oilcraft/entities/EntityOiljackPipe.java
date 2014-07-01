package cz.ondraster.oilcraft.entities;

import cz.ondraster.oilcraft.FluidTank;
import cz.ondraster.oilcraft.fluids.Fluids;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class EntityOiljackPipe extends TileEntity implements IFluidHandler {

   public final static int MAXIMUM_SIZE = 2000;
   public final static int RANGE = 16;
   int depth = 0;
   private FluidTank tank;
   private int ticksSinceOutput = 0;

   public EntityOiljackPipe() {
      this.tank = new FluidTank(MAXIMUM_SIZE, Fluids.fluidCrudeOil);
   }

   public boolean digOil() {
      if (worldObj.isRemote)
         return false;

      boolean didDig = false;
      if (tank.getFluidAmount() + 1000 > tank.getCapacity()) {
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
         tank.fill(new FluidStack(Fluids.fluidCrudeOil, 1000), true);
         return true;
      } else {
         Block block = worldObj.getBlock(xCoord, yCoord - depth - 2, zCoord);
         if (block == Blocks.air) {
            depth++;
         } else if (block == Fluids.blockFluidCrudeOil) {
            tank.fill(new FluidStack(Fluids.fluidCrudeOil, 1000), true);
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
      this.depth = nbt.getInteger("depth");
      tank.load(nbt);
   }

   @Override
   public void writeToNBT(NBTTagCompound nbt) {
      super.writeToNBT(nbt);
      nbt.setInteger("depth", depth);
      tank.save(nbt);
   }

   @Override
   public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
      return 0;
   }

   @Override
   public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
      return tank.drain(resource.amount, doDrain);
   }

   @Override
   public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
      return tank.drain(maxDrain, doDrain);
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
      FluidTankInfo info = tank.getInfo();
      return new FluidTankInfo[]{info};
   }

   @Override
   public void updateEntity() {
      ticksSinceOutput++;

      if (ticksSinceOutput % 20 == 0 && tank != null) {
         if (tank.getFluidAmount() > 0 && tank.getFluid().getFluid() != null) {
            for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
               if (worldObj.getTileEntity(xCoord + direction.offsetX, yCoord + direction.offsetY, zCoord + direction.offsetZ) instanceof IFluidHandler) {
                  IFluidHandler handler = (IFluidHandler) worldObj.getTileEntity(xCoord + direction.offsetX, yCoord + direction.offsetY, zCoord + direction.offsetZ);
                  if (handler.canFill(direction.getOpposite(), tank.getFluid().getFluid())) {
                     int amount = handler.fill(direction.getOpposite(), tank.getFluid(), true);
                     this.tank.drain(amount, true);
                  }
               }
            }
         }
         ticksSinceOutput = 0;
      }


   }

}
