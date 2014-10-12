package cz.ondraster.oilcraft.blocks;

import cpw.mods.fml.client.registry.ClientRegistry;
import cz.ondraster.oilcraft.References;
import cz.ondraster.oilcraft.Registrator;
import cz.ondraster.oilcraft.client.renderers.GasGeneratorRenderer;
import cz.ondraster.oilcraft.client.renderers.OiljackRenderer;
import cz.ondraster.oilcraft.client.renderers.PipeRenderer;
import cz.ondraster.oilcraft.tileentities.TileEntityGeneratorGas;
import cz.ondraster.oilcraft.tileentities.TileEntityGeneratorPower;
import cz.ondraster.oilcraft.tileentities.TileEntityOiljack;
import cz.ondraster.oilcraft.tileentities.TileEntityPipe;
import net.minecraft.block.Block;


public class OilBlocks {
   public static Block oiljack;
   public static Block pipe;
   public static Block oiljackPipe;
   public static Block blockActualPipe;

   public static Block rubberWood;
   public static Block rubberLeaf;
   public static Block rubberSapling;

   public static Block invisibleBlock;
   public static Block invisibleGenerator;

   public static Block gasGeneratorRF;


   public static void init() {
      oiljack = new BlockOiljack();
      Registrator.registerBlock(oiljack);
      ClientRegistry.bindTileEntitySpecialRenderer(TileEntityOiljack.class, new OiljackRenderer());

      pipe = new BlockPipe();
      Registrator.registerBlock(pipe);
      ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPipe.class, new PipeRenderer());

      oiljackPipe = new BlockOiljackPipe();
      Registrator.registerBlock(oiljackPipe);

      blockActualPipe = new BlockActualPipe();
      Registrator.registerBlock(blockActualPipe);

      rubberWood = new RubberWood();
      Registrator.registerBlock(rubberWood);

      rubberLeaf = new RubberLeaf();
      Registrator.registerBlock(rubberLeaf);

      rubberSapling = new RubberSapling();
      Registrator.registerBlock(rubberSapling);

      invisibleBlock = new InvisibleBlock();
      Registrator.registerBlock(invisibleBlock);

      invisibleGenerator = new BlockInvisibleGenerator();
      Registrator.registerBlock(invisibleGenerator);
      Registrator.registerTileEntity(TileEntityGeneratorPower.class, References.Entities.ENTITYGENERATOR);

      gasGeneratorRF = new GasGeneratorRF();
      Registrator.registerBlock(gasGeneratorRF);
      ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGeneratorGas.class, new GasGeneratorRenderer());
      Registrator.registerTileEntity(TileEntityGeneratorGas.class, References.Entities.ENTITYGASGENERATOR);

   }
}
