package cz.ondraster.oilcraft;

import cpw.mods.fml.common.registry.GameRegistry;
import cz.ondraster.oilcraft.blocks.OilBlocks;
import cz.ondraster.oilcraft.factory.FactoryBlocks;
import cz.ondraster.oilcraft.items.OilItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class Recipes {
   public static Item itemOiljackTop;
   public static Item itemOiljackBase;
   public static Item itemRubberGasket;
   public static Item itemSteelIngot;
   public static Item itemRubberBar;

   public static Item itemHeatingCoil;
   public static Item steelCompound;

   public static void addRecipes() {
      itemOiljackTop = new Item().setUnlocalizedName(References.UnlocalizedNames.ITEMOILJACKTOP).setTextureName(References.Icons.ITEMOILJACKTOP).setCreativeTab(OilCraft.creativeTab);
      itemOiljackBase = new Item().setUnlocalizedName(References.UnlocalizedNames.ITEMOILJACKBASE).setTextureName(References.Icons.ITEMOILJACKBASE).setCreativeTab(OilCraft.creativeTab);
      itemRubberGasket = new Item().setUnlocalizedName(References.UnlocalizedNames.ITEMRUBBERGASKET).setTextureName(References.Icons.ITEMRUBBERGASKET).setCreativeTab(OilCraft.creativeTab);
      itemSteelIngot = new Item().setUnlocalizedName(References.UnlocalizedNames.ITEMSTEELINGOT).setTextureName(References.Icons.ITEMSTEELINGOT).setCreativeTab(OilCraft.creativeTab);
      itemHeatingCoil = new Item().setUnlocalizedName(References.UnlocalizedNames.ITEMHEATINGCOIL).setTextureName(References.Icons.ITEMHEATINGCOIL).setCreativeTab(OilCraft.creativeTab);
      itemRubberBar = new Item().setUnlocalizedName(References.UnlocalizedNames.ITEMRUBBERBAR).setTextureName(References.Icons.ITEMRUBBERBAR).setCreativeTab(OilCraft.creativeTab);
      steelCompound = new Item().setUnlocalizedName(References.UnlocalizedNames.STEELCOMPOUND).setTextureName(References.Icons.STEELCOMPOUND).setCreativeTab(OilCraft.creativeTab);

      Registrator.registerItem(itemOiljackTop);
      Registrator.registerItem(itemOiljackBase);
      Registrator.registerItem(itemRubberGasket);
      Registrator.registerItem(itemSteelIngot);
      Registrator.registerItem(itemHeatingCoil);
      Registrator.registerItem(itemRubberBar);
      Registrator.registerItem(steelCompound);

      OreDictionary.registerOre("ingotSteel", itemSteelIngot);
      OreDictionary.registerOre("heatingCoil", itemHeatingCoil);
      OreDictionary.registerOre("barRubber", itemRubberBar);
      OreDictionary.registerOre("rubber", itemRubberBar);
      OreDictionary.registerOre("gasketRubber", itemRubberGasket);


      // supplier items
      GameRegistry.addSmelting(new ItemStack(OilItems.resinExtractor, 1, 10), new ItemStack(itemRubberBar), 0);
      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(itemRubberGasket, 2), "rrr", "r r", "rrr", 'r', "barRubber"));
      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(OilItems.resinExtractor), " s ", "tst", "iii", 's', Items.stick, 't', Items.string, 'i', "ingotIron"));

      // steel
      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(steelCompound), "ccc", "cic", "ccc", 'c', Items.coal, 'i', "ingotIron"));
      GameRegistry.addSmelting(new ItemStack(steelCompound), new ItemStack(itemSteelIngot), 0);

      // mining
      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(itemOiljackBase), " x ", " x ", " x ", 'x', "ingotIron"));
      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(itemOiljackTop), "  g", "iig", "  g", 'i', "ingotIron", 'g', "ingotGold"));
      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(OilBlocks.oiljack), "b b", " t ", "b b", 'b', itemOiljackBase, 't', itemOiljackTop));
      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(OilBlocks.oiljackPipe), "iii", " ps", "i i", 'i', "ingotIron", 'p', OilBlocks.pipe, 's', "gasketRubber"));

      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(OilBlocks.pipe, 2), "iii", "s s", "iii", 'i', "ingotIron", 's', "gasketRubber"));

      // multiblock parts
      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(FactoryBlocks.blockMachineCasing), "iii", "isi", "iii", 'i', "ingotIron", 's', "gasketRubber"));
      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(FactoryBlocks.blockMachineCasingHT), "iii", "isi", "iii", 'i', "ingotSteel", 's', "gasketRubber"));

      GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(FactoryBlocks.blockHatch), new ItemStack(FactoryBlocks.blockMachineCasing), new ItemStack(Blocks.chest)));
      GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(FactoryBlocks.blockValve), new ItemStack(FactoryBlocks.blockMachineCasing), "gasketRubber", new ItemStack(OilBlocks.pipe)));
      GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(FactoryBlocks.blockValveHT), new ItemStack(FactoryBlocks.blockMachineCasingHT), "gasketRubber", new ItemStack(OilBlocks.pipe)));
      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(FactoryBlocks.blockSolidFirebox), "iii", "shs", "sss", 'i', "ingotIron", 's', "ingotSteel", 'h', itemHeatingCoil));

      // controller parts
      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(itemHeatingCoil), "ggg", "gsg", "ggg", 'g', "ingotGold", 's', "ingotSteel"));

      // controllers
      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(FactoryBlocks.controllerAsphaltBlower), "sss", "gog", "sss", 's', "ingotSteel", 'g', "gasketRubber", 'o', "ingotGold"));
      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(FactoryBlocks.controllerHeater), "sss", "ghg", "sss", 's', "ingotSteel", 'h', itemHeatingCoil, 'g', "gasketRubber"));
      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(FactoryBlocks.controllerCatalyticReformer), "gsg", "gog", "sss", 's', "ingotSteel", 'g', "gasketRubber", 'o', "ingotGold"));
      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(FactoryBlocks.controllerCrocker), "sgs", "gog", "sss", 's', "ingotSteel", 'g', "gasketRubber", 'o', "ingotGold"));
      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(FactoryBlocks.controllerDistillator), "sss", "sos", "sss", 's', "ingotSteel", 'o', "ingotGold"));
      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(FactoryBlocks.controllerGasProcessor), "s s", "gog", "sss", 's', "ingotSteel", 'g', "gasketRubber", 'o', "ingotGold"));
      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(FactoryBlocks.controllerHydrotreater), "gsg", "gog", "sss", 's', "ingotSteel", 'g', "gasketRubber", 'o', Blocks.gold_block));
      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(FactoryBlocks.controllerMeroxTreater), "oso", "gog", "sss", 's', "ingotSteel", 'g', "gasketRubber", 'o', "ingotGold"));

      // other stuff
      // TODO - Matches

   }
}
