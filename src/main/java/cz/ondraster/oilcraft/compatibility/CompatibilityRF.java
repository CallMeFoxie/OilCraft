package cz.ondraster.oilcraft.compatibility;

import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.registry.GameRegistry;
import cz.ondraster.oilcraft.factory.FactoryBlocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class CompatibilityRF extends CompatibilityBase {
   @Override
   @Optional.Method(modid = "CoFHAPI|energy")
   public void addRecipes() {
      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(FactoryBlocks.blockElectricFireboxRF), "iii", "shs", "iii", 'i', "ingotIron", 's', "ingotSteel", 'h', "blockRedstone"));
   }
}
