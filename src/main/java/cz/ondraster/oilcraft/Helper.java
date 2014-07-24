package cz.ondraster.oilcraft;

import cpw.mods.fml.common.FMLLog;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;

public class Helper {
   public static void logWarn(String message) {
      FMLLog.warning("[" + References.MODNAME + "] " + message);
   }

   public static boolean canMergeStacks(ItemStack a, ItemStack b) {
      if (a == null || b == null)
         return true;

      if (a.getItem() == b.getItem() && a.getMaxStackSize() >= a.stackSize + b.stackSize)
         return true;

      return false;
   }

   public static ItemStack mergeStacks(ItemStack a, ItemStack b) {
      if (a == null && b != null)
         return b.copy();
      else if (b == null && a != null)
         return a.copy();
      else if (a == null && b == null)
         return null;

      a.stackSize += b.stackSize;
      return a;
   }

   public static ItemStack getEmptyContainer(ItemStack filledContainer) {
      FluidContainerRegistry.FluidContainerData[] datas = FluidContainerRegistry.getRegisteredFluidContainerData();
      for (FluidContainerRegistry.FluidContainerData data : datas) {
         if (data.filledContainer.getItem() == filledContainer.getItem())
            return data.emptyContainer;
      }

      return null;
   }

   public static ItemStack getFilled(ItemStack emptyContainer) {
      FluidContainerRegistry.FluidContainerData[] datas = FluidContainerRegistry.getRegisteredFluidContainerData();
      for (FluidContainerRegistry.FluidContainerData data : datas) {
         if (data.emptyContainer.getItem() == emptyContainer.getItem())
            return data.filledContainer;
      }

      return null;
   }

   public static boolean canFitIntoTank(FluidTankInfo tank, FluidStack stack) {
      if (tank.fluid == null && tank.capacity <= stack.amount)
         return true;
      if (tank.fluid == null)
         return true;

      if (tank.fluid.getFluid() != stack.getFluid())
         return false;

      if (tank.fluid.amount + stack.amount > tank.capacity)
         return false;

      return true;
   }

   public static boolean isProperFluid(FluidTankInfo tankInfo, FluidStack stack) {
      if (tankInfo.fluid == null)
         return false;
      if (tankInfo.fluid.getFluid() != stack.getFluid())
         return false;
      if (tankInfo.fluid.amount < stack.amount)
         return false;

      return true;
   }
}
