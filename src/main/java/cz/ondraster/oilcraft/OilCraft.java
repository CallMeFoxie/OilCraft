package cz.ondraster.oilcraft;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cz.ondraster.oilcraft.blocks.OilBlocks;
import cz.ondraster.oilcraft.fluids.Fluids;
import cz.ondraster.oilcraft.items.OilItems;
import cz.ondraster.oilcraft.network.Network;

@Mod(modid = References.MODID, version = References.VERSION, name = References.MODNAME)
public class OilCraft {

   @Mod.Instance
   public static OilCraft instance;

   public static Network network;

   public static CreativeTabOilCraft creativeTab;

   @Mod.EventHandler
   public void preinit(FMLPreInitializationEvent event) {
   }


   @Mod.EventHandler
   public void init(FMLInitializationEvent event) {

      creativeTab = new CreativeTabOilCraft();


      // register all the blocks
      OilBlocks.init();
      Fluids.init();
      OilItems.init();


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

