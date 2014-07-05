package cz.ondraster.oilcraft.fluids;

import cz.ondraster.oilcraft.References;
import cz.ondraster.oilcraft.Registrator;
import cz.ondraster.oilcraft.client.OilMaterials;
import net.minecraft.block.Block;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class Fluids {
   public static Fluid fluidCrudeOil;
   public static Block blockFluidCrudeOil;

   public static Fluid fluidHydrogen;
   public static Block blockFluidHydrogen;

   public static void init() {
      fluidCrudeOil = new Fluid(References.UnlocalizedNames.FLUIDCRUDEOIL);
      FluidRegistry.registerFluid(fluidCrudeOil);

      blockFluidCrudeOil = new BlockFluid(fluidCrudeOil, OilMaterials.materialOil, References.UnlocalizedNames.FLUIDCRUDEOIL, References.Textures.FLUIDCRUDEOILSTILL, References.Textures.FLUIDCRUDEOILFLOWING);
      Registrator.registerBlock(blockFluidCrudeOil);

      fluidHydrogen = new Fluid(References.UnlocalizedNames.FLUIDHYDROGEN).setGaseous(true);
      FluidRegistry.registerFluid(fluidHydrogen);

      blockFluidHydrogen = new BlockFluid(fluidHydrogen, OilMaterials.materialHydrogen, References.UnlocalizedNames.FLUIDHYDROGEN, References.Textures.FLUIDHYDROGENSTILL, References.Textures.FLUIDHYDROGENFLOWING);
      Registrator.registerBlock(blockFluidHydrogen);

   }
}
