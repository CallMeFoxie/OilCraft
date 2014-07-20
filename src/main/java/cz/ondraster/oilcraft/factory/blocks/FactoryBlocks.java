package cz.ondraster.oilcraft.factory.blocks;

import cz.ondraster.oilcraft.References;
import cz.ondraster.oilcraft.Registrator;
import cz.ondraster.oilcraft.factory.controllers.ControllerDistillator;
import cz.ondraster.oilcraft.factory.controllers.ControllerHeater;
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
   public static Block blockElectricFirebox;
   public static Block blockSolidFirebox;
   public static Block blockWindow;
   public static Block blockMeter;
   public static Block blockValve;
   public static Block blockValveHT;
   public static Block blockHatch;

   public static MultiblockController controllerHeater;
   public static MultiblockController controllerDistillator;
   public static MultiblockController controllerHydrotreater;
   public static MultiblockController controllerMeroxTreater;
   public static MultiblockController controllerGasProcessor;
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

      // Register all the controller machines
      Registrator.registerTileEntity(TileEntityHeater.class, References.Entities.ENTITYHEATER);
      Registrator.registerTileEntity(TileEntityDistillator.class, References.Entities.ENTITYDISTILLATOR);
   }

   public static void addFactoryRecipes() {
      controllerHeater.addProcessingFluid(
            new MultiblockController.ProcessingFluid(
                  new FluidStack[]{new FluidStack(Fluids.fluidCrudeOil, 100)},
                  new FluidStack[]{new FluidStack(Fluids.fluidHeatedOil, 100)}));

      controllerDistillator.addProcessingFluid(
            new MultiblockController.ProcessingFluid(
                  new FluidStack[]{new FluidStack(Fluids.fluidHeatedOil, 100)},
                  new FluidStack[]{new FluidStack(Fluids.fluidRAsphalt, 20), new FluidStack(Fluids.fluidLubricant, 5), new FluidStack(Fluids.fluidRFuel, 35), new FluidStack(Fluids.fluidRDiesel, 10), new FluidStack(Fluids.fluidRKerosene, 10), new FluidStack(Fluids.fluidRPetroleum, 10), new FluidStack(Fluids.fluidRButane, 10)},
                  new ItemStack[]{new ItemStack(OilItems.dustParaffin)}
            )
      );
   }
}
