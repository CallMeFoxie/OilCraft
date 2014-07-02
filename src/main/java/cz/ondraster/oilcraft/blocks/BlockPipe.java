package cz.ondraster.oilcraft.blocks;

import cz.ondraster.oilcraft.OilCraft;
import cz.ondraster.oilcraft.References;
import cz.ondraster.oilcraft.entities.EntityPipe;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.IFluidHandler;

public class BlockPipe extends BlockContainer {

   public BlockPipe() {
      super(Material.anvil);
      setBlockName(References.UnlocalizedNames.BLOCKPIPE);
      setCreativeTab(OilCraft.creativeTab);
   }

   @Override
   public TileEntity createNewTileEntity(World world, int p_149915_2_) {
      return new EntityPipe();
   }

   private void updateBlockStatus(World world, int x, int y, int z) {
      EntityPipe ourPipe = (EntityPipe) world.getTileEntity(x, y, z);
      for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
         if (world.getTileEntity(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ) instanceof IFluidHandler) {
            TileEntity pipe = world.getTileEntity(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ);
            if (pipe != null) {
               if (pipe instanceof EntityPipe)
                  ((EntityPipe) pipe).changeState(dir.getOpposite(), true);

               ourPipe.changeState(dir, true);
            }
         } else {
            ourPipe.changeState(dir, false);
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

   public void onBlockPreDestroy(World world, int x, int y, int z, int oldmeta) {

   }
}
