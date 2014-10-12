package cz.ondraster.oilcraft.blocks;

import cz.ondraster.oilcraft.References;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.Random;

public class BlockActualPipe extends Block {
   public BlockActualPipe() {
      super(Material.rock);
      setBlockName(References.UnlocalizedNames.Blocks.BLOCKACTUALPIPE);
      setBlockTextureName(References.Textures.BLOCKACTUALPIPE);
      setHardness(600000.0f);
      setBlockUnbreakable();
      setBlockBounds(0.1f, 0.0f, 0.1f, 0.9f, 1f, 0.9f);
   }

   @Override
   public int quantityDropped(Random p_149745_1_) {
      return 0;
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }
}
