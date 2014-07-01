package cz.ondraster.oilcraft.fluids;

import cz.ondraster.oilcraft.Registrator;
import net.minecraft.block.Block;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class Fluids {
   public static Fluid fluidCrudeOil;
   public static Block blockFluidCrudeOil;

   public static void init() {
      fluidCrudeOil = new FluidCrudeOil();
      FluidRegistry.registerFluid(fluidCrudeOil);

      blockFluidCrudeOil = new BlockFluidCrudeOil();
      Registrator.registerBlock(blockFluidCrudeOil);
   }
}
