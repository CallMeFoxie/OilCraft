package cz.ondraster.oilcraft;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * Created by Ondra on 21.6.2014.
 */
public class BlockBeam extends BlockContainer {
   protected BlockBeam() {
      super(Material.anvil);
      this.setCreativeTab(CreativeTabs.tabBlock);
      this.setBlockTextureName("oilcraft:beam");
      this.setBlockName("beam");
   }

   //This will tell minecraft not to render any side of our cube.
   public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l)
   {
      return false;
   }

   //And this tell it that you can see through this block, and neighbor blocks should be rendered.
   public boolean isOpaqueCube()
   {
      return false;
   }

   @Override
   public TileEntity createNewTileEntity(World world, int meta) {
      return new TEBeam();
   }

   @Override
   public boolean hasTileEntity(int metadata) {
      return true;
   }
}
