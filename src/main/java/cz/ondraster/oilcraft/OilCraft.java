package cz.ondraster.oilcraft;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cz.ondraster.oilcraft.blocks.OilBlocks;
import cz.ondraster.oilcraft.client.gui.GuiHandlers;
import cz.ondraster.oilcraft.compatibility.CompatibilityBase;
import cz.ondraster.oilcraft.factory.FactoryBlocks;
import cz.ondraster.oilcraft.fluids.Fluids;
import cz.ondraster.oilcraft.handlers.Events;
import cz.ondraster.oilcraft.items.OilItems;
import cz.ondraster.oilcraft.network.Network;
import cz.ondraster.oilcraft.worldgen.OilGenerator;
import cz.ondraster.oilcraft.worldgen.RubberTreeGenerator;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = References.MODID, version = References.VERSION, name = References.MODNAME)
public class OilCraft {

   @Mod.Instance
   public static OilCraft instance;

   public static Network network;

   public static CreativeTabOilCraft creativeTab;

   @Mod.EventHandler
   public void preinit(FMLPreInitializationEvent event) {
      Helper.logInfo("Hello!");
      creativeTab = new CreativeTabOilCraft();


      // register all the blocks
      OilBlocks.init();
      FactoryBlocks.init();
      Fluids.init();
      OilItems.init();

      MinecraftForge.EVENT_BUS.register(new Events());

      // register recipes

      network = new Network();

      NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandlers());

      // add recipes
      FactoryBlocks.addFactoryRecipes();
      Recipes.addRecipes();

   }


   @Mod.EventHandler
   public void init(FMLInitializationEvent event) {

      // compatibility with other mods
      CompatibilityBase.init();

      GameRegistry.registerWorldGenerator(new RubberTreeGenerator(), 10);
      GameRegistry.registerWorldGenerator(new OilGenerator(), 1);

   }

   @Mod.EventHandler
   public void postInit(FMLPostInitializationEvent event) {
   }

   @Mod.EventHandler
   public void serverLoad(FMLServerStartingEvent event) {
      // register server commands
   }
}

