package cz.ondraster.oilcraft;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.*;

public class FluidTank implements IFluidTank {

   public boolean canAccept = true;
   private int capacity;
   private int storedAmount;
   private Fluid filter;
   private Fluid storedFluid;

   public FluidTank(int capacity) {
      this(capacity, null);
   }

   public FluidTank(int capacity, Fluid filter) {
      this.capacity = capacity;
      this.filter = filter;
   }

   public NBTTagCompound save(NBTTagCompound tag) {
      if (storedAmount != 0) {
         tag.setInteger("amount", storedAmount);
         tag.setString("fluid", storedFluid.getUnlocalizedName());
      }
      return tag;
   }

   public void load(NBTTagCompound tag) {
      storedAmount = tag.getInteger("amount");
      if (storedAmount != 0) {
         storedFluid = FluidRegistry.getFluid(tag.getString("fluid"));
         if (storedFluid == null) {
            Helper.logWarn("Could not find fluid while loading tank (" + tag.getString("fluid") + ")");
         }
      }
   }

   @Override
   public FluidStack getFluid() {
      if (storedFluid == null)
         return null;

      return new FluidStack(storedFluid, storedAmount);
   }

   @Override
   public int getFluidAmount() {
      return storedAmount;
   }

   @Override
   public int getCapacity() {
      return capacity;
   }

   @Override
   public FluidTankInfo getInfo() {
      return new FluidTankInfo(this);
   }

   @Override
   public int fill(FluidStack resource, boolean doFill) {
      if (!canAccept)
         return 0;

      if (filter == null) {
         if (storedFluid == null && doFill)
            storedFluid = resource.getFluid();

         if (storedFluid == resource.getFluid() || (!doFill && storedFluid == null)) {
            int toFill = Math.min(capacity - storedAmount, resource.amount);
            if (doFill)
               this.storedAmount = toFill;
            return toFill;
         }
      } else if (filter != resource.getFluid())
         return 0;
      else if (filter == resource.getFluid()) {
         int toFill = Math.min(capacity - storedAmount, resource.amount);
         if (doFill) {
            this.storedAmount += toFill;
            this.storedFluid = resource.getFluid();
         }

         return toFill;

      }
      return 0;
   }

   @Override
   public FluidStack drain(int maxDrain, boolean doDrain) {
      FluidStack toRet = null;
      if (maxDrain == 0)
         return null;

      if (maxDrain > storedAmount) {
         toRet = new FluidStack(storedFluid, storedAmount);
      } else if (maxDrain <= storedAmount) {
         toRet = new FluidStack(storedFluid, maxDrain);
      }

      if (doDrain) {
         this.storedAmount -= toRet.amount;
         if (this.storedAmount == 0)
            this.storedFluid = null;
      }

      return toRet;
   }
}
