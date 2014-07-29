package cz.ondraster.oilcraft.blocks;

import cpw.mods.fml.client.registry.ClientRegistry;
import cz.ondraster.oilcraft.Registrator;
import cz.ondraster.oilcraft.client.renderers.OiljackRenderer;
import cz.ondraster.oilcraft.client.renderers.PipeRenderer;
import cz.ondraster.oilcraft.entities.EntityOiljack;
import cz.ondraster.oilcraft.entities.EntityPipe;
import net.minecraft.block.Block;


public class OilBlocks {
   public static Block oiljack;
   public static Block pipe;
   public static Block oiljackPipe;
   public static Block blockActualPipe;

   public static Block rubberWood;
   public static Block rubberLeaf;
   public static Block rubberSapling;


   public static void init() {
      oiljack = new BlockOiljack();
      Registrator.registerBlock(oiljack);
      ClientRegistry.bindTileEntitySpecialRenderer(EntityOiljack.class, new OiljackRenderer());

      pipe = new BlockPipe();
      Registrator.registerBlock(pipe);
      ClientRegistry.bindTileEntitySpecialRenderer(EntityPipe.class, new PipeRenderer());

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
   }
}
