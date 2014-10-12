package cz.ondraster.oilcraft.blocks;

import cz.ondraster.oilcraft.OilCraft;
import cz.ondraster.oilcraft.References;
import cz.ondraster.oilcraft.tileentities.TileEntityGeneratorGas;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class GasGeneratorRF extends BlockContainer {
   protected GasGeneratorRF() {
      super(Material.iron);
      setBlockName(References.UnlocalizedNames.Blocks.BLOCKGASGENERATOR);
      setCreativeTab(OilCraft.creativeTab);
      setBlockTextureName(References.Textures.BLOCKGASGENERATOR);
   }

   public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack stack) {
      int l = BlockPistonBase.determineOrientation(world, x, y, z, player);

      if (l == 1 || l == 0)
         l = 2;

      TileEntityGeneratorGas gas = (TileEntityGeneratorGas) world.getTileEntity(x, y, z);
      gas.setOrientation(ForgeDirection.getOrientation(l));
   }

   @Override
   public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
      return new TileEntityGeneratorGas();
   }

   public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l) {
      return false;
   }

   public boolean isOpaqueCube() {
      return false;
   }
}
