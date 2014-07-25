package cz.ondraster.oilcraft.factory.blocks;

import cz.ondraster.oilcraft.References;
import cz.ondraster.oilcraft.Registrator;
import cz.ondraster.oilcraft.factory.controllers.*;
import cz.ondraster.oilcraft.factory.multiblock.MultiblockController;
import cz.ondraster.oilcraft.factory.tileentities.*;
import cz.ondraster.oilcraft.fluids.Fluids;
import cz.ondraster.oilcraft.items.OilItems;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class FactoryBlocks {
   public static Block blockMachineCasing;
   public static Block blockMachineCasingHT; // HT for High Temperature
   public static Block blockElectricFirebox;
   public static Block blockSolidFirebox;
   public static Block blockWindow;
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
      Registrator.registerTileEntity(TileEntityFireboxElectric.class, References.Entities.FIREBOXELECTRIC);
      Registrator.registerTileEntity(TileEntityFireboxSolid.class, References.Entities.FIREBOXSOLID);
      Registrator.registerTileEntity(TileEntityHatch.class, References.Entities.HATCH);


      blockMachineCasing = new BlockMachineCasing();
      blockMachineCasing.setBlockName(References.UnlocalizedNames.BLOCKMACHINECASING);
      blockMachineCasing.setBlockTextureName(References.Textures.BLOCKMACHINECASING);
      Registrator.registerBlock(blockMachineCasing);

      blockMachineCasingHT = new BlockMachineCasing();
      blockMachineCasingHT.setBlockName(References.UnlocalizedNames.BLOCKMACHINECASINGHT);
      blockMachineCasingHT.setBlockTextureName(References.Textures.BLOCKMACHINECASINGHT);
      Registrator.registerBlock(blockMachineCasingHT);

      blockElectricFirebox = new BlockMachineFirebox();
      blockElectricFirebox.setBlockName(References.UnlocalizedNames.BLOCKELECTRICFIREBOX);
      blockElectricFirebox.setBlockTextureName(References.Textures.BLOCKELECTRICFIREBOX);
      Registrator.registerBlock(blockElectricFirebox);

      blockSolidFirebox = new BlockMachineFireboxSolid();
      blockSolidFirebox.setBlockName(References.UnlocalizedNames.BLOCKSOLIDFIREBOX);
      blockSolidFirebox.setBlockTextureName(References.Textures.BLOCKSOLIDFIREBOX);
      Registrator.registerBlock(blockSolidFirebox);

      blockWindow = new BlockMachineCasing();
      blockWindow.setBlockName(References.UnlocalizedNames.BLOCKWINDOW);
      blockWindow.setBlockTextureName(References.Textures.BLOCKWINDOW);
      Registrator.registerBlock(blockWindow);

      blockMeter = new BlockMachineCasing();
      blockMeter.setBlockName(References.UnlocalizedNames.BLOCKMETER);
      blockMeter.setBlockTextureName(References.Textures.BLOCKMETER);
      Registrator.registerBlock(blockMeter);

      blockValve = new BlockMachineValve();
      blockValve.setBlockName(References.UnlocalizedNames.BLOCKVALVE);
      blockValve.setBlockTextureName(References.Textures.BLOCKVALVE);
      Registrator.registerBlock(blockValve);

      blockValveHT = new BlockMachineValveHT();
      blockValveHT.setBlockName(References.UnlocalizedNames.BLOCKVALVEHT);
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

      // Register all the controller machines
      Registrator.registerTileEntity(TileEntityHeater.class, References.Entities.ENTITYHEATER);
      Registrator.registerTileEntity(TileEntityDistillator.class, References.Entities.ENTITYDISTILLATOR);
      Registrator.registerTileEntity(TileEntityAsphaltBlower.class, References.Entities.ENTITYASPHALTBLOWER);
      Registrator.registerTileEntity(TileEntityMeroxTreater.class, References.Entities.ENTITYMEROXTREATER);
      Registrator.registerTileEntity(TileEntityGasProcessor.class, References.Entities.ENTITYGASPROC);
      Registrator.registerTileEntity(TileEntityCrocker.class, References.Entities.ENTITYCROCKER);
      Registrator.registerTileEntity(TileEntityHydrotreater.class, References.Entities.ENTITYHYDROTREATER);
   }

   public static void addFactoryRecipes() {
      controllerHeater.addProcessingFluid(
            new MultiblockController.ProcessingFluid(
                  new FluidStack[]{new FluidStack(Fluids.fluidCrudeOil, 100)},
                  new FluidStack[]{new FluidStack(Fluids.fluidHeatedOil, 100)}));

      // Buildcraft compatibility
      if (FluidRegistry.isFluidRegistered("oil")) {
         controllerHeater.addProcessingFluid(
               new MultiblockController.ProcessingFluid(
                     new FluidStack[]{new FluidStack(FluidRegistry.getFluid("oil"), 100)},
                     new FluidStack[]{new FluidStack(Fluids.fluidHeatedOil, 100)}));
      }

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
   }
}
