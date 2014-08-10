package cz.ondraster.oilcraft.compatibility;

import buildcraft.api.fuels.IronEngineFuel;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.registry.GameRegistry;
import cz.ondraster.oilcraft.Helper;
import cz.ondraster.oilcraft.factory.FactoryBlocks;
import cz.ondraster.oilcraft.factory.multiblock.MultiblockController;
import cz.ondraster.oilcraft.fluids.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class CompatibilityBuildcraft extends CompatibilityBase {
   @Override
   @Optional.Method(modid = "BuildCraftAPI|fuels")
   public void addRecipes() {
      Helper.logInfo("[BC compat] Trying to load compatibility between OilCraft and BC");
      IronEngineFuel.addFuel(Fluids.fluidCrudeOil, 3, 5000);
      IronEngineFuel.addFuel(Fluids.fluidGasoline, 3, 25000);
      IronEngineFuel.addFuel(Fluids.fluidDiesel, 2, 46875);
      IronEngineFuel.addFuel(Fluids.fluidKerosene, 4, 28125);

      FactoryBlocks.controllerHeater.addProcessingFluid(
            new MultiblockController.ProcessingFluid(
                  new FluidStack[]{new FluidStack(FluidRegistry.getFluid("oil"), 100)},
                  new FluidStack[]{new FluidStack(Fluids.fluidHeatedOil, 100)}));

      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(FactoryBlocks.blockElectricFireboxMJ), "iii", "shs", "iii", 'i', "ingotIron", 's', "ingotSteel", 'h', "gearGold"));

      Helper.logInfo("[BC compat] Done!");
   }
}
