package cz.ondraster.oilcraft.fluids;

import cz.ondraster.oilcraft.References;
import cz.ondraster.oilcraft.Registrator;
import cz.ondraster.oilcraft.client.OilMaterials;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class Fluids {
   public static Fluid fluidCrudeOil;
   public static BlockFluid blockFluidCrudeOil;

   public static Fluid fluidHeatedOil;

   public static Fluid fluidHydrogen;

   public static Fluid fluidButaneRaw;
   public static Fluid fluidButane;

   public static Fluid fluidPetrolRaw;
   public static Fluid fluidPetrol;

   public static Fluid fluidKeroseneRaw;
   public static Fluid fluidKerosene;

   public static Fluid fluidDieselRaw;
   public static Fluid fluidDiesel;

   public static Fluid fluidFuelRaw;
   public static Fluid fluidFuel;

   public static Fluid fluidLubricant;

   public static Fluid fluidAsphaltRaw;
   public static Fluid fluidAsphalt;

   public static Fluid fluidParaffin;

   public static void init() {
      fluidCrudeOil = new Fluid(References.UnlocalizedNames.FLUIDCRUDEOIL);
      FluidRegistry.registerFluid(fluidCrudeOil);
      fluidHydrogen = new Fluid(References.UnlocalizedNames.FLUIDHYDROGEN).setGaseous(true);
      FluidRegistry.registerFluid(fluidHydrogen);
      fluidHeatedOil = new Fluid(References.UnlocalizedNames.FLUIDHEATEDOIL).setTemperature(671);
      FluidRegistry.registerFluid(fluidHeatedOil);
      fluidButaneRaw = new Fluid(References.UnlocalizedNames.FLUIDBUTANERAW).setGaseous(true);
      FluidRegistry.registerFluid(fluidButaneRaw);
      fluidButane = new Fluid(References.UnlocalizedNames.FLUIDBUTANE).setGaseous(true);
      FluidRegistry.registerFluid(fluidButane);
      fluidPetrolRaw = new Fluid(References.UnlocalizedNames.FLUIDPETROLRAW);
      FluidRegistry.registerFluid(fluidPetrolRaw);
      fluidPetrol = new Fluid(References.UnlocalizedNames.FLUIDPETROL);
      FluidRegistry.registerFluid(fluidPetrol);
      fluidKeroseneRaw = new Fluid(References.UnlocalizedNames.FLUIDKEROSENERAW);
      FluidRegistry.registerFluid(fluidKeroseneRaw);
      fluidKerosene = new Fluid(References.UnlocalizedNames.FLUIDKEROSENE);
      FluidRegistry.registerFluid(fluidKerosene);
      fluidDieselRaw = new Fluid(References.UnlocalizedNames.FLUIDDIESELRAW);
      FluidRegistry.registerFluid(fluidDieselRaw);
      fluidDiesel = new Fluid(References.UnlocalizedNames.FLUIDDIESEL);
      FluidRegistry.registerFluid(fluidDiesel);
      fluidFuelRaw = new Fluid(References.UnlocalizedNames.FLUIDFUELRAW);
      FluidRegistry.registerFluid(fluidFuelRaw);
      fluidFuel = new Fluid(References.UnlocalizedNames.FLUIDFUEL);
      FluidRegistry.registerFluid(fluidFuel);
      fluidLubricant = new Fluid(References.UnlocalizedNames.FLUIDLUBRICANT);
      FluidRegistry.registerFluid(fluidLubricant);
      fluidAsphaltRaw = new Fluid(References.UnlocalizedNames.FLUIDASPHALTRAW);
      FluidRegistry.registerFluid(fluidAsphaltRaw);
      fluidAsphalt = new Fluid(References.UnlocalizedNames.FLUIDASPHALT);
      FluidRegistry.registerFluid(fluidAsphalt);
      fluidParaffin = new Fluid(References.UnlocalizedNames.FLUIDPARAFFIN);
      FluidRegistry.registerFluid(fluidParaffin);

      blockFluidCrudeOil = new BlockFluid(fluidCrudeOil, OilMaterials.materialOil, References.UnlocalizedNames.FLUIDCRUDEOIL, References.Textures.FLUIDCRUDEOILSTILL, References.Textures.FLUIDCRUDEOILFLOWING);
      Registrator.registerBlock(blockFluidCrudeOil);
      /*blockFluidHydrogen = new BlockFluid(fluidHydrogen, OilMaterials.materialHydrogen, References.UnlocalizedNames.FLUIDHYDROGEN, References.Textures.FLUIDHYDROGENSTILL, References.Textures.FLUIDHYDROGENFLOWING);
      Registrator.registerBlock(blockFluidHydrogen);
      blockFluidHeatedOil = new BlockFluid(fluidHeatedOil, OilMaterials.materialOil, References.UnlocalizedNames.FLUIDHEATEDOIL, References.Textures.FLUIDCRUDEOILSTILL, References.Textures.FLUIDCRUDEOILFLOWING);
      Registrator.registerBlock(blockFluidHeatedOil);*/

   }
}
