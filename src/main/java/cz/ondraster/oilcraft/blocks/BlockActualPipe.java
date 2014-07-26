package cz.ondraster.oilcraft.blocks;

import cz.ondraster.oilcraft.References;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.Random;

public class BlockActualPipe extends Block {
   public BlockActualPipe() {
      super(Material.rock);
      setBlockName(References.UnlocalizedNames.BLOCKACTUALPIPE);
      setBlockTextureName(References.Textures.BLOCKACTUALPIPE);
   }

   @Override
   public int quantityDropped(Random p_149745_1_) {
      return 0;
   }
}
