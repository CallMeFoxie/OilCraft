package cz.ondraster.oilcraft;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.*;

public class FluidTank implements IFluidTank {

   public boolean canAccept = true;
   protected int capacity;
   protected Fluid storedFluid;
   protected int storedAmount;
   private Fluid filter;

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
         if (storedFluid == null) {
            Helper.logWarn("Had non-empty tank with null fluid! Would have crashed...");
            tag.setInteger("amount", 0);
         } else
            tag.setString("fluid", storedFluid.getUnlocalizedName().substring(6));
      }
      return tag;
   }

   public void load(NBTTagCompound tag) {
      storedAmount = tag.getInteger("amount");
      if (storedAmount != 0) {
         storedFluid = FluidRegistry.getFluid(tag.getString("fluid"));
         if (storedFluid == null) {
            Helper.logWarn("Could not find fluid while loading tank (" + tag.getString("fluid") + ")");
            storedAmount = 0;
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
               this.storedAmount += toFill;
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
      if (maxDrain == 0 || storedAmount == 0 || storedFluid == null)
         return null;

      toRet = new FluidStack(storedFluid, Math.min(storedAmount, maxDrain));

      if (doDrain) {
         this.storedAmount -= toRet.amount;
         if (this.storedAmount <= 0)
            this.storedFluid = null;
      }

      return toRet;
   }

   public void safeDrain() {
      this.storedAmount = 0;
   }
}
