package cz.ondraster.oilcraft.compatibility;

import buildcraft.api.fuels.IronEngineFuel;
import cpw.mods.fml.common.Optional;
import cz.ondraster.oilcraft.factory.blocks.FactoryBlocks;
import cz.ondraster.oilcraft.factory.multiblock.MultiblockController;
import cz.ondraster.oilcraft.fluids.Fluids;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class CompatibilityBuildcraft extends CompatibilityBase {
   @Override
   @Optional.Method(modid = "BuildCraftAPI|fuels")
   public void addRecipes() {
      IronEngineFuel.addFuel(Fluids.fluidCrudeOil, 3, 5000);
      IronEngineFuel.addFuel(Fluids.fluidGasoline, 3, 25000);

      FactoryBlocks.controllerHeater.addProcessingFluid(
            new MultiblockController.ProcessingFluid(
                  new FluidStack[]{new FluidStack(FluidRegistry.getFluid("oil"), 100)},
                  new FluidStack[]{new FluidStack(Fluids.fluidHeatedOil, 100)}));
   }
}
