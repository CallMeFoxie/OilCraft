package cz.ondraster.oilcraft.factory.blocks;

import cz.ondraster.oilcraft.References;
import cz.ondraster.oilcraft.Registrator;
import net.minecraft.block.Block;

public class FactoryBlocks {
   public static Block blockMachineCasing;
   public static Block blockMachineCasingHT; // HT for High Temperature
   public static Block blockElectricHeater;
   public static Block blockSolidHeater;
   public static Block blockWindow;
   public static Block blockMeter;

   public static void init() {

      blockMachineCasing = new BlockMachineCasing();
      blockMachineCasing.setBlockName(References.UnlocalizedNames.BLOCKMACHINECASING);
      blockMachineCasing.setBlockTextureName(References.Textures.BLOCKMACHINECASING);
      Registrator.registerBlock(blockMachineCasing);

      blockMachineCasingHT = new BlockMachineCasing();
      blockMachineCasingHT.setBlockName(References.UnlocalizedNames.BLOCKMACHINECASINGHT);
      blockMachineCasingHT.setBlockTextureName(References.Textures.BLOCKMACHINECASINGHT);
      Registrator.registerBlock(blockMachineCasingHT);

      blockElectricHeater = new BlockMachineCasing();
      blockElectricHeater.setBlockName(References.UnlocalizedNames.BLOCKELECTRICHEATER);
      blockElectricHeater.setBlockTextureName(References.Textures.BLOCKELECTRICHEATER);
      Registrator.registerBlock(blockElectricHeater);

      blockSolidHeater = new BlockMachineCasing();
      blockSolidHeater.setBlockName(References.UnlocalizedNames.BLOCKSOLIDHEATER);
      blockSolidHeater.setBlockTextureName(References.Textures.BLOCKSOLIDHEATER);
      Registrator.registerBlock(blockSolidHeater);

      blockWindow = new BlockMachineCasing();
      blockWindow.setBlockName(References.UnlocalizedNames.BLOCKWINDOW);
      blockWindow.setBlockTextureName(References.Textures.BLOCKWINDOW);
      Registrator.registerBlock(blockWindow);

      blockMeter = new BlockMachineCasing();
      blockMeter.setBlockName(References.UnlocalizedNames.BLOCKMETER);
      blockMeter.setBlockTextureName(References.Textures.BLOCKMETER);
      Registrator.registerBlock(blockMeter);
   }
}
