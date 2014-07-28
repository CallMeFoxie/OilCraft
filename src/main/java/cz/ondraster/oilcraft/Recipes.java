package cz.ondraster.oilcraft;

import cpw.mods.fml.common.registry.GameRegistry;
import cz.ondraster.oilcraft.blocks.OilBlocks;
import cz.ondraster.oilcraft.factory.blocks.FactoryBlocks;
import net.minecraft.init.Blocks;
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

   public static Item itemHeatingCoil;

   public static void addRecipes() {
      itemOiljackTop = new Item().setUnlocalizedName(References.UnlocalizedNames.ITEMOILJACKTOP).setTextureName(References.Icons.ITEMOILJACKTOP).setCreativeTab(OilCraft.creativeTab);
      itemOiljackBase = new Item().setUnlocalizedName(References.UnlocalizedNames.ITEMOILJACKBASE).setTextureName(References.Icons.ITEMOILJACKBASE).setCreativeTab(OilCraft.creativeTab);
      itemRubberGasket = new Item().setUnlocalizedName(References.UnlocalizedNames.ITEMRUBBERGASKET).setTextureName(References.Icons.ITEMRUBBERGASKET).setCreativeTab(OilCraft.creativeTab);
      itemSteelIngot = new Item().setUnlocalizedName(References.UnlocalizedNames.ITEMSTEELINGOT).setTextureName(References.Icons.ITEMSTEELINGOT).setCreativeTab(OilCraft.creativeTab);
      itemHeatingCoil = new Item().setUnlocalizedName(References.UnlocalizedNames.ITEMHEATINGCOIL).setTextureName(References.Icons.ITEMHEATINGCOIL).setCreativeTab(OilCraft.creativeTab);

      Registrator.registerItem(itemOiljackTop);
      Registrator.registerItem(itemOiljackBase);
      Registrator.registerItem(itemRubberGasket);
      Registrator.registerItem(itemSteelIngot);
      Registrator.registerItem(itemHeatingCoil);

      OreDictionary.registerOre("ingotSteel", itemSteelIngot);
      OreDictionary.registerOre("heatingCoil", itemHeatingCoil);

      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(itemOiljackBase), " x ", " x ", " x ", 'x', "ingotIron"));
      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(itemOiljackTop), "  g", "iig", "  g", 'i', "ingotIron", 'g', "ingotGold"));
      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(OilBlocks.oiljack), "b b", " t ", "b b", 'b', itemOiljackBase, 't', itemOiljackTop));

      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(OilBlocks.pipe, 2), "iii", "s s", "iii", 'i', "ingotIron", 's', itemRubberGasket));

      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(OilBlocks.oiljackPipe), "iii", " ps", "i i", 'i', "ingotIron", 'p', OilBlocks.pipe, 's', itemRubberGasket));

      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(FactoryBlocks.blockMachineCasing), "iii", "isi", "iii", 'i', "ingotIron", 's', itemRubberGasket));
      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(FactoryBlocks.blockMachineCasingHT), "iii", "isi", "iii", 'i', "ingotSteel", 's', itemRubberGasket));

      GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(FactoryBlocks.blockHatch), new ItemStack(FactoryBlocks.blockMachineCasing), new ItemStack(Blocks.chest)));
      GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(FactoryBlocks.blockValve), new ItemStack(FactoryBlocks.blockMachineCasing), new ItemStack(itemRubberGasket), new ItemStack(OilBlocks.pipe)));
      GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(FactoryBlocks.blockValveHT), new ItemStack(FactoryBlocks.blockMachineCasingHT), new ItemStack(itemRubberGasket), new ItemStack(OilBlocks.pipe)));

      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(itemHeatingCoil), "ggg", "gsg", "ggg", 'g', "ingotGold", 's', "ingotSteel"));

      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(FactoryBlocks.controllerAsphaltBlower), "sss", "gog", "sss", 's', itemSteelIngot, 'g', itemRubberGasket, 'o', "ingotGold"));
      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(FactoryBlocks.controllerHeater), "sss", "gcg", "sss", 's', itemSteelIngot, 'g', itemRubberGasket, 'c', "heatingCoil"));
   }
}
