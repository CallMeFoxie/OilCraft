package cz.ondraster.oilcraft.blocks;

import cpw.mods.fml.client.registry.ClientRegistry;
import cz.ondraster.oilcraft.References;
import cz.ondraster.oilcraft.Registrator;
import cz.ondraster.oilcraft.client.renderers.OiljackRenderer;
import cz.ondraster.oilcraft.client.renderers.PipeRenderer;
import cz.ondraster.oilcraft.entities.EntityOiljack;
import cz.ondraster.oilcraft.entities.EntityOiljackPipe;
import cz.ondraster.oilcraft.entities.EntityPipe;
import net.minecraft.block.Block;


public class OilBlocks {
   public static Block oiljack;
   public static Block pipe;
   public static Block oiljackPipe;

   public static void init() {
      oiljack = new BlockOiljack();
      Registrator.registerBlock(oiljack);
      Registrator.registerTileEntity(EntityOiljack.class, References.UnlocalizedNames.ENTITYOILJACK);
      ClientRegistry.bindTileEntitySpecialRenderer(EntityOiljack.class, new OiljackRenderer());

      pipe = new BlockPipe();
      Registrator.registerBlock(pipe);
      Registrator.registerTileEntity(EntityPipe.class, References.UnlocalizedNames.ENTITYPIPE);
      ClientRegistry.bindTileEntitySpecialRenderer(EntityPipe.class, new PipeRenderer());

      oiljackPipe = new BlockOiljackPipe();
      Registrator.registerBlock(oiljackPipe);
      Registrator.registerTileEntity(EntityOiljackPipe.class, References.UnlocalizedNames.ENTITYOILJACKPIPE);

   }
}