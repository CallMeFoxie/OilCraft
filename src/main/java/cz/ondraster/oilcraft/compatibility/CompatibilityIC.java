package cz.ondraster.oilcraft.compatibility;

import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.common.registry.GameRegistry;
import cz.ondraster.oilcraft.Helper;
import cz.ondraster.oilcraft.References;
import cz.ondraster.oilcraft.factory.FactoryBlocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class CompatibilityIC extends CompatibilityBase {
   @Override
   @Optional.Method(modid = "IC2API")
   public void addRecipes() {
      Helper.logInfo("[IC2 compat] Trying to load compatibility between OilCraft and IC2");
      Item x = GameData.getItemRegistry().getObject("IC2:itemRecipePart");

      if (x == null) {
         Helper.logWarn("[IC2 compat] could not find heating coil for recipe! Skipping :(");
         return;
      }

      ItemStack y = new ItemStack(x, 1, 5);
      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(FactoryBlocks.blockElectricFireboxEU), "iii", "shs", "iii", 'i', "ingotIron", 's', "ingotSteel", 'h', y));

      ic2.api.recipe.Recipes.semiFluidGenerator.addFluid(References.UnlocalizedNames.FLUIDGASOLINE, 2, 32);
      ic2.api.recipe.Recipes.semiFluidGenerator.addFluid(References.UnlocalizedNames.FLUIDDIESEL, 1, 20);
      ic2.api.recipe.Recipes.semiFluidGenerator.addFluid(References.UnlocalizedNames.FLUIDKEROSENE, 1, 24);

      Helper.logInfo("[IC2 compat] Done!");
   }
}
