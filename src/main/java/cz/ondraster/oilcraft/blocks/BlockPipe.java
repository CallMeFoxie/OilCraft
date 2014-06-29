package cz.ondraster.oilcraft.blocks;

import cz.ondraster.oilcraft.OilBlocks;
import cz.ondraster.oilcraft.OrientationSimple;
import cz.ondraster.oilcraft.References;
import cz.ondraster.oilcraft.entities.EntityPipe;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * Created by Ondra on 29.6.2014.
 */
public class BlockPipe extends BlockContainer {

   public BlockPipe() {
      super(Material.anvil);
      setBlockName(References.UnlocalizedNames.BLOCKPIPE);
      setCreativeTab(CreativeTabs.tabBlock);
   }

   @Override
   public TileEntity createNewTileEntity(World world, int p_149915_2_) {
      return new EntityPipe();
   }

   private void updateBlockStatus(World world, int x, int y, int z) {
      EntityPipe ourPipe = (EntityPipe) world.getTileEntity(x, y, z);

      for (int i = 0; i < OrientationSimple.Directions; i++) {
         if (world.getBlock(x + OrientationSimple.getX(i), y + OrientationSimple.getY(i), z + OrientationSimple.getZ(i)) == OilBlocks.pipe) {
            System.out.println("adding to side " + i);

            EntityPipe pipe = (EntityPipe) world.getTileEntity(x + OrientationSimple.getX(i), y + OrientationSimple.getY(i), z + OrientationSimple.getZ(i));
            pipe.changeState(OrientationSimple.getOpposite(i), true);

            ourPipe.changeState(i, true);
         } else {
            ourPipe.changeState(i, false);
         }
      }
      world.markBlockForUpdate(x, y, z);
   }

   @Override
   public void onBlockAdded(World world, int x, int y, int z) {
      updateBlockStatus(world, x, y, z);
   }

   //This will tell minecraft not to render any side of our cube.
   @Override
   public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l) {
      return false;
   }

   //And this tell it that you can see through this block, and neighbor blocks should be rendered.
   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public void onNeighborChange(IBlockAccess world, int x, int y, int z, int tileX, int tileY, int tileZ) {
      if (world instanceof World && ((World) world).isRemote)
         return;

      if (world instanceof World)
         updateBlockStatus((World) world, x, y, z);
   }
}