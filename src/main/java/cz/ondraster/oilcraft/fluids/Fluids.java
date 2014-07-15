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

   public static Fluid fluidRButane;
   public static Fluid fluidButane;

   public static Fluid fluidRPetroleum;
   public static Fluid fluidPetroleum;
   public static Fluid fluidPPetroleum;

   public static Fluid fluidRKerosene;
   public static Fluid fluidKerosene;

   public static Fluid fluidRDiesel;
   public static Fluid fluidDiesel;

   public static Fluid fluidRFuel;
   public static Fluid fluidFuel;

   public static Fluid fluidLubricant;

   public static Fluid fluidRAsphalt;
   public static Fluid fluidAsphalt;

   public static Fluid fluidParaffin;

   public static Fluid fluidNaphta;

   public static Fluid fluidGasoline;

   public static Fluid fluidPButane;
   public static Fluid fluidLPG;


   public static void init() {
      fluidCrudeOil = new Fluid(References.UnlocalizedNames.FLUIDCRUDEOIL);
      FluidRegistry.registerFluid(fluidCrudeOil);
      fluidHydrogen = new Fluid(References.UnlocalizedNames.FLUIDHYDROGEN).setGaseous(true);
      FluidRegistry.registerFluid(fluidHydrogen);
      fluidHeatedOil = new Fluid(References.UnlocalizedNames.FLUIDHEATEDOIL).setTemperature(671);
      FluidRegistry.registerFluid(fluidHeatedOil);
      fluidRButane = new Fluid(References.UnlocalizedNames.FLUIDBUTANERAW).setGaseous(true);
      FluidRegistry.registerFluid(fluidRButane);
      fluidButane = new Fluid(References.UnlocalizedNames.FLUIDBUTANE).setGaseous(true);
      FluidRegistry.registerFluid(fluidButane);
      fluidRPetroleum = new Fluid(References.UnlocalizedNames.FLUIDPETROLRAW);
      FluidRegistry.registerFluid(fluidRPetroleum);
      fluidPPetroleum = new Fluid(References.UnlocalizedNames.FLUIDPPETROLEUM);
      FluidRegistry.registerFluid(fluidPPetroleum);
      fluidPetroleum = new Fluid(References.UnlocalizedNames.FLUIDPETROL);
      FluidRegistry.registerFluid(fluidPetroleum);
      fluidRKerosene = new Fluid(References.UnlocalizedNames.FLUIDKEROSENERAW);
      FluidRegistry.registerFluid(fluidRKerosene);
      fluidKerosene = new Fluid(References.UnlocalizedNames.FLUIDKEROSENE);
      FluidRegistry.registerFluid(fluidKerosene);
      fluidRDiesel = new Fluid(References.UnlocalizedNames.FLUIDDIESELRAW);
      FluidRegistry.registerFluid(fluidRDiesel);
      fluidDiesel = new Fluid(References.UnlocalizedNames.FLUIDDIESEL);
      FluidRegistry.registerFluid(fluidDiesel);
      fluidRFuel = new Fluid(References.UnlocalizedNames.FLUIDFUELRAW);
      FluidRegistry.registerFluid(fluidRFuel);
      fluidFuel = new Fluid(References.UnlocalizedNames.FLUIDFUEL);
      FluidRegistry.registerFluid(fluidFuel);
      fluidLubricant = new Fluid(References.UnlocalizedNames.FLUIDLUBRICANT);
      FluidRegistry.registerFluid(fluidLubricant);
      fluidRAsphalt = new Fluid(References.UnlocalizedNames.FLUIDASPHALTRAW);
      FluidRegistry.registerFluid(fluidRAsphalt);
      fluidAsphalt = new Fluid(References.UnlocalizedNames.FLUIDASPHALT);
      FluidRegistry.registerFluid(fluidAsphalt);
      fluidParaffin = new Fluid(References.UnlocalizedNames.FLUIDPARAFFIN);
      FluidRegistry.registerFluid(fluidParaffin);
      fluidNaphta = new Fluid(References.UnlocalizedNames.FLUIDNAPHTA);
      FluidRegistry.registerFluid(fluidNaphta);
      fluidGasoline = new Fluid(References.UnlocalizedNames.FLUIDGASOLINE);
      FluidRegistry.registerFluid(fluidGasoline);
      fluidPButane = new Fluid(References.UnlocalizedNames.FLUIDPBUTANE);
      FluidRegistry.registerFluid(fluidPButane);
      fluidLPG = new Fluid(References.UnlocalizedNames.FLUIDLPG);
      FluidRegistry.registerFluid(fluidLPG);

      blockFluidCrudeOil = new BlockFluid(fluidCrudeOil, OilMaterials.materialOil, References.UnlocalizedNames.FLUIDCRUDEOIL, References.Textures.FLUIDCRUDEOILSTILL, References.Textures.FLUIDCRUDEOILFLOWING);
      Registrator.registerBlock(blockFluidCrudeOil);
      /*blockFluidHydrogen = new BlockFluid(fluidHydrogen, OilMaterials.materialHydrogen, References.UnlocalizedNames.FLUIDHYDROGEN, References.Textures.FLUIDHYDROGENSTILL, References.Textures.FLUIDHYDROGENFLOWING);
      Registrator.registerBlock(blockFluidHydrogen);
      blockFluidHeatedOil = new BlockFluid(fluidHeatedOil, OilMaterials.materialOil, References.UnlocalizedNames.FLUIDHEATEDOIL, References.Textures.FLUIDCRUDEOILSTILL, References.Textures.FLUIDCRUDEOILFLOWING);
      Registrator.registerBlock(blockFluidHeatedOil);*/

   }
}
