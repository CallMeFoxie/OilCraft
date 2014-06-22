package cz.ondraster.oilcraft;

/**
 * Created by Ondra on 17.6.2014.
 */

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cz.ondraster.oilcraft.cz.ondraster.oilcraft.client.renderers.BeamRenderer;

@Mod(modid = OilCraft.MODID, version = OilCraft.VERSION)
public class OilCraft
{
   public static final String MODID = "oilcraft";
   public static final String VERSION = "1.0";

   @Mod.EventHandler
   public void init(FMLInitializationEvent event)
   {

      OilBlocks.init();

      GameRegistry.registerBlock(OilBlocks.Beam, OilBlocks.Beam.getUnlocalizedName());
      GameRegistry.registerTileEntity(TEBeam.class, "oilcraft_tebeam");
      ClientRegistry.bindTileEntitySpecialRenderer(TEBeam.class, new BeamRenderer());

   }
}

