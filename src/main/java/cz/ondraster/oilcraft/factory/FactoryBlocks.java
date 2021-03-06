package cz.ondraster.oilcraft.factory;

import cz.ondraster.oilcraft.References;
import cz.ondraster.oilcraft.Registrator;
import cz.ondraster.oilcraft.factory.blocks.*;
import cz.ondraster.oilcraft.factory.controllers.*;
import cz.ondraster.oilcraft.factory.multiblock.MultiblockController;
import cz.ondraster.oilcraft.factory.tileentities.*;
import cz.ondraster.oilcraft.fluids.Fluids;
import cz.ondraster.oilcraft.items.OilItems;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class FactoryBlocks {
   public static Block blockMachineCasing;
   public static Block blockMachineCasingHT; // HT for High Temperature
   public static Block blockElectricFireboxMJ;
   public static Block blockElectricFireboxEU;
   public static Block blockElectricFireboxRF;
   public static Block blockSolidFirebox;
   //public static Block blockWindow;
   public static Block blockMeter;
   public static Block blockValve;
   public static Block blockValveHT;
   public static Block blockHatch;

   public static MultiblockController controllerHeater;
   public static MultiblockController controllerDistillator;
   public static MultiblockController controllerGasProcessor;
   public static MultiblockController controllerMeroxTreater;
   public static MultiblockController controllerHydrotreater;
   public static MultiblockController controllerCatalyticReformer;
   public static MultiblockController controllerCrocker;
   public static MultiblockController controllerAsphaltBlower;

   public static void init() {

      // Register all the parts
      Registrator.registerTileEntity(TileEntityValve.class, References.Entities.ENTITYVALVE);
      Registrator.registerTileEntity(TileEntityValveHT.class, References.Entities.ENTITYVALVEHT);
      Registrator.registerTileEntity(TileEntityPart.class, References.Entities.ENTITYBASE);
      Registrator.registerTileEntity(TileEntityFireboxMJ.class, References.Entities.ENTITYFIREBOXMJ);
      Registrator.registerTileEntity(TileEntityFireboxEU.class, References.Entities.ENTITYFIREBOXEU);
      Registrator.registerTileEntity(TileEntityFireboxRF.class, References.Entities.ENTITYFIREBOXRF);
      Registrator.registerTileEntity(TileEntityFireboxSolid.class, References.Entities.FIREBOXSOLID);
      Registrator.registerTileEntity(TileEntityHatch.class, References.Entities.HATCH);


      blockMachineCasing = new BlockMachineCasing();
      blockMachineCasing.setBlockName(References.UnlocalizedNames.Blocks.BLOCKMACHINECASING);
      blockMachineCasing.setBlockTextureName(References.Textures.BLOCKMACHINECASING);
      Registrator.registerBlock(blockMachineCasing);

      blockMachineCasingHT = new BlockMachineCasing();
      blockMachineCasingHT.setBlockName(References.UnlocalizedNames.Blocks.BLOCKMACHINECASINGHT);
      blockMachineCasingHT.setBlockTextureName(References.Textures.BLOCKMACHINECASINGHT);
      Registrator.registerBlock(blockMachineCasingHT);

      blockElectricFireboxMJ = new BlockMachineFireboxMJ();
      Registrator.registerBlock(blockElectricFireboxMJ);

      blockElectricFireboxEU = new BlockMachineFireboxEU();
      Registrator.registerBlock(blockElectricFireboxEU);

      blockElectricFireboxRF = new BlockMachineFireboxRF();
      Registrator.registerBlock(blockElectricFireboxRF);

      blockSolidFirebox = new BlockMachineFireboxSolid();
      blockSolidFirebox.setBlockName(References.UnlocalizedNames.Blocks.BLOCKSOLIDFIREBOX);
      blockSolidFirebox.setBlockTextureName(References.Textures.BLOCKSOLIDFIREBOX);
      Registrator.registerBlock(blockSolidFirebox);

      /*blockWindow = new BlockMachineCasing();
      blockWindow.setBlockName(References.UnlocalizedNames.BLOCKWINDOW);
      blockWindow.setBlockTextureName(References.Textures.BLOCKWINDOW);
      Registrator.registerBlock(blockWindow);  */

      blockMeter = new BlockMachineCasing();
      blockMeter.setBlockName(References.UnlocalizedNames.Blocks.BLOCKMETER);
      blockMeter.setBlockTextureName(References.Textures.BLOCKMETER);
      Registrator.registerBlock(blockMeter);

      blockValve = new BlockMachineValve();
      blockValve.setBlockName(References.UnlocalizedNames.Blocks.BLOCKVALVE);
      blockValve.setBlockTextureName(References.Textures.BLOCKVALVE);
      Registrator.registerBlock(blockValve);

      blockValveHT = new BlockMachineValveHT();
      blockValveHT.setBlockName(References.UnlocalizedNames.Blocks.BLOCKVALVEHT);
      blockValveHT.setBlockTextureName(References.Textures.BLOCKVALVEHT);
      Registrator.registerBlock(blockValveHT);

      blockHatch = new BlockHatch();
      Registrator.registerBlock(blockHatch);

      // register all the controllers
      controllerHeater = new ControllerHeater();
      Registrator.registerBlock(controllerHeater);
      controllerDistillator = new ControllerDistillator();
      Registrator.registerBlock(controllerDistillator);
      controllerAsphaltBlower = new ControllerAsphaltBlower();
      Registrator.registerBlock(controllerAsphaltBlower);
      controllerMeroxTreater = new ControllerMeroxTreater();
      Registrator.registerBlock(controllerMeroxTreater);
      controllerGasProcessor = new ControllerGasProcessor();
      Registrator.registerBlock(controllerGasProcessor);
      controllerCrocker = new ControllerCrocker();
      Registrator.registerBlock(controllerCrocker);
      controllerHydrotreater = new ControllerHydrotreater();
      Registrator.registerBlock(controllerHydrotreater);
      controllerCatalyticReformer = new ControllerCatalyticReformer();
      Registrator.registerBlock(controllerCatalyticReformer);

      // Register all the controller machines
      Registrator.registerTileEntity(TileEntityHeater.class, References.Entities.ENTITYHEATER);
      Registrator.registerTileEntity(TileEntityDistillator.class, References.Entities.ENTITYDISTILLATOR);
      Registrator.registerTileEntity(TileEntityAsphaltBlower.class, References.Entities.ENTITYASPHALTBLOWER);
      Registrator.registerTileEntity(TileEntityMeroxTreater.class, References.Entities.ENTITYMEROXTREATER);
      Registrator.registerTileEntity(TileEntityGasProcessor.class, References.Entities.ENTITYGASPROC);
      Registrator.registerTileEntity(TileEntityCrocker.class, References.Entities.ENTITYCROCKER);
      Registrator.registerTileEntity(TileEntityHydrotreater.class, References.Entities.ENTITYHYDROTREATER);
      Registrator.registerTileEntity(TileEntityCatalyticReformer.class, References.Entities.ENTITYCATALYTICREFORMER);
   }

   public static void addFactoryRecipes() {
      controllerHeater.addProcessingFluid(
            new MultiblockController.ProcessingFluid(
                  new FluidStack[]{new FluidStack(Fluids.fluidCrudeOil, 100)},
                  new FluidStack[]{new FluidStack(Fluids.fluidHeatedOil, 100)}
            )
      );

      controllerDistillator.addProcessingFluid(
            new MultiblockController.ProcessingFluid(
                  new FluidStack[]{new FluidStack(Fluids.fluidHeatedOil, 100)},
                  new FluidStack[]{new FluidStack(Fluids.fluidRAsphalt, 20), new FluidStack(Fluids.fluidLubricant, 5), new FluidStack(Fluids.fluidRFuel, 35), new FluidStack(Fluids.fluidRDiesel, 10), new FluidStack(Fluids.fluidRKerosene, 10), new FluidStack(Fluids.fluidRPetroleum, 10), new FluidStack(Fluids.fluidRButane, 10)},
                  new ItemStack[]{new ItemStack(OilItems.dustParaffin)}
            )
      );

      controllerAsphaltBlower.addProcessingFluid(
            new MultiblockController.ProcessingFluid(
                  new FluidStack[]{new FluidStack(Fluids.fluidRAsphalt, 20)},
                  new FluidStack[]{new FluidStack(Fluids.fluidAsphalt, 20)}
            )
      );

      controllerMeroxTreater.addProcessingFluid(new MultiblockController.ProcessingFluid(
            new FluidStack[]{new FluidStack(Fluids.fluidPButane, 20)},
            new FluidStack[]{new FluidStack(Fluids.fluidLPG, 20)}
      ));

      controllerMeroxTreater.addProcessingFluid(new MultiblockController.ProcessingFluid(
            new FluidStack[]{new FluidStack(Fluids.fluidRKerosene, 20)},
            new FluidStack[]{new FluidStack(Fluids.fluidKerosene, 20)}
      ));

      controllerGasProcessor.addProcessingFluid(new MultiblockController.ProcessingFluid(
            new FluidStack[]{new FluidStack(Fluids.fluidRButane, 10)},
            new FluidStack[]{new FluidStack(Fluids.fluidPButane, 10)},
            new ItemStack[]{new ItemStack(OilItems.dustSulfur)}
      ));

      controllerCrocker.addProcessingFluid(new MultiblockController.ProcessingFluid(
            new FluidStack[]{new FluidStack(Fluids.fluidAlkalyticFuel, 10)},
            new FluidStack[]{new FluidStack(Fluids.fluidNaphta, 8), new FluidStack(Fluids.fluidAlkalyte, 2)}
      ));

      controllerHydrotreater.addProcessingFluid(new MultiblockController.ProcessingFluid(
            new FluidStack[]{new FluidStack(Fluids.fluidRDiesel, 10), new FluidStack(Fluids.fluidHydrogen, 5)},
            new FluidStack[]{new FluidStack(Fluids.fluidDiesel, 8), new FluidStack(Fluids.fluidGas, 7)}
      ));

      controllerHydrotreater.addProcessingFluid(new MultiblockController.ProcessingFluid(
            new FluidStack[]{new FluidStack(Fluids.fluidRFuel, 10), new FluidStack(Fluids.fluidHydrogen, 5)},
            new FluidStack[]{new FluidStack(Fluids.fluidFuel, 8), new FluidStack(Fluids.fluidGas, 7)}
      ));

      controllerHydrotreater.addProcessingFluid(new MultiblockController.ProcessingFluid(
            new FluidStack[]{new FluidStack(Fluids.fluidNaphta, 10), new FluidStack(Fluids.fluidHydrogen, 5)},
            new FluidStack[]{new FluidStack(Fluids.fluidGasoline, 8), new FluidStack(Fluids.fluidGas, 7)}
      ));

      controllerHydrotreater.addProcessingFluid(new MultiblockController.ProcessingFluid(
            new FluidStack[]{new FluidStack(Fluids.fluidRPetroleum, 10), new FluidStack(Fluids.fluidHydrogen, 5)},
            new FluidStack[]{new FluidStack(Fluids.fluidPPetroleum, 8), new FluidStack(Fluids.fluidGas, 2)}
      ));

      controllerCatalyticReformer.addProcessingFluid(new MultiblockController.ProcessingFluid(
            new FluidStack[]{new FluidStack(Fluids.fluidPPetroleum, 15)},
            new FluidStack[]{new FluidStack(Fluids.fluidGas, 2), new FluidStack(Fluids.fluidHydrogen, 6), new FluidStack(Fluids.fluidPetroleum, 7)}
      ));
   }
}
