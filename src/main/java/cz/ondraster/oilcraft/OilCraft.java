package cz.ondraster.oilcraft;

/**
 * Created by Ondra on 17.6.2014.
 */

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cz.ondraster.oilcraft.network.Network;

@Mod(modid = References.MODID, version = References.VERSION, name = References.MODNAME)
public class OilCraft {

   @Mod.Instance
   public static OilCraft instance;

   public static Network network;

   @Mod.EventHandler
   public void preinit(FMLPreInitializationEvent event) {
   }


   @Mod.EventHandler
   public void init(FMLInitializationEvent event) {

      // register all the blocks
      OilBlocks.init();


      // register recipes

      // register event buses

      network = new Network();

      // register GUI handlers

   }

   @Mod.EventHandler
   public void postInit(FMLPostInitializationEvent event) {
   }

   @Mod.EventHandler
   public void serverLoad(FMLServerStartingEvent event) {
      // register server commands
   }
}

