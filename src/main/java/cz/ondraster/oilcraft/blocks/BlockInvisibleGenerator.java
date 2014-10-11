package cz.ondraster.oilcraft.blocks;

import cz.ondraster.oilcraft.References;
import cz.ondraster.oilcraft.tileentities.TileEntityGenerator;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockInvisibleGenerator extends BlockContainer {
   public BlockInvisibleGenerator() {
      super(Material.wood);
      setBlockName(References.UnlocalizedNames.Blocks.BLOCKINVISIBLEGENERATOR);
      setResistance(6000000.0F);
      setBlockUnbreakable();
      setBlockTextureName(References.Textures.BLOCKINVISIBLE);
      setBlockBounds(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }


   @Override
   public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
      return new TileEntityGenerator();
   }
}
