package cz.ondraster.oilcraft;

import cpw.mods.fml.common.FMLLog;
import cz.ondraster.oilcraft.fluids.FluidTank;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;

public class Helper {
   public static void logWarn(String message) {
      FMLLog.warning("[" + References.MODNAME + "] " + message);
   }

   public static boolean canMergeStacks(ItemStack a, ItemStack b) {
      if (a == null || b == null)
         return true;

      if (a == b && a.getMaxStackSize() >= a.stackSize + b.stackSize)
         return true;

      return false;
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

   public static boolean canFitIntoTank(FluidTank tank, FluidStack stack) {
      if (tank.getFluid() == null && tank.getCapacity() <= stack.amount)
         return true;
      if (tank.getFluid() == null)
         return false;

      if (tank.getFluid().getFluid() != stack.getFluid())
         return false;

      if (tank.getFluid().amount + stack.amount > tank.getCapacity())
         return false;

      return true;
   }
}
