package cz.ondraster.oilcraft.blocks;

import cz.ondraster.oilcraft.References;
import net.minecraft.block.BlockLeaves;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

import java.util.Random;

public class RubberLeaf extends BlockLeaves {
   public RubberLeaf() {
      setBlockTextureName(References.Textures.RUBBERLEAF);
      setBlockName(References.UnlocalizedNames.Blocks.RUBBERLEAF);
   }

   @Override
   public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
      return this.blockIcon;
   }

   @Override
   public String[] func_150125_e() {
      return new String[]{"rubber_leaf"};
   }

   @Override
   public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
      return Item.getItemFromBlock(OilBlocks.rubberSapling);
   }
}
