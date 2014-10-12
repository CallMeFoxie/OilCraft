package cz.ondraster.oilcraft.blocks;

import cz.ondraster.oilcraft.References;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

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

   @Override
   public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
      return AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 1, 1);
   }

   @Override
   public boolean shouldSideBeRendered(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_) {
      return false;
   }
}
