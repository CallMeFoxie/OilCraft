package cz.ondraster.oilcraft.blocks;

import cz.ondraster.oilcraft.References;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class InvisibleBlock extends Block {
   protected InvisibleBlock() {
      super(Material.wood);
      setResistance(6000000.0F);
      setBlockUnbreakable();
      setBlockName(References.UnlocalizedNames.Blocks.BLOCKINVISIBLE);
      setBlockTextureName(References.Textures.BLOCKINVISIBLE);
      setBlockBounds(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

}
