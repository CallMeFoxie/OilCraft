package cz.ondraster.oilcraft.blocks;

import cz.ondraster.oilcraft.References;
import cz.ondraster.oilcraft.entities.EntityOiljackPipe;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockOiljackPipe extends BlockContainer {
   protected BlockOiljackPipe() {
      super(Material.anvil);
      setBlockName(References.UnlocalizedNames.BLOCKOILJACKPIPE);
      setCreativeTab(CreativeTabs.tabBlock);
      setBlockTextureName(References.Textures.BLOCKOILJACKPIPE);
      setBlockBounds(0f, 0f, 0f, 1f, 1f, 1f);
   }

   @Override
   public TileEntity createNewTileEntity(World world, int meta) {
      return new EntityOiljackPipe();
   }

   @Override
   public boolean hasTileEntity(int metadata) {
      return true;
   }

   @Override
   public boolean isOpaqueCube() {
      return true;
   }

   @Override
   public boolean renderAsNormalBlock() {
      return true;
   }

}