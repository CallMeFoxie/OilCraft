package cz.ondraster.oilcraft.blocks;

import cz.ondraster.oilcraft.OilBlocks;
import cz.ondraster.oilcraft.References;
import cz.ondraster.oilcraft.entities.EntityPipe;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

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


      for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
         //System.out.println(world.getBlock(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ).getUnlocalizedName());
         if (world.getBlock(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ) == OilBlocks.pipe) {
            System.out.println("adding to side " + direction.toString());

            EntityPipe pipe = (EntityPipe) world.getTileEntity(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ);
            pipe.changeState(direction.getOpposite(), true);
            //Network.networkChannel.sendToDimension(new MessagePipeUpdate(pipe), world.provider.dimensionId);

            ourPipe.changeState(direction, true);
         } else {
            ourPipe.changeState(direction, false);
         }


      }

      //Network.networkChannel.sendToDimension(new MessagePipeUpdate(ourPipe), world.provider.dimensionId);
      System.out.println("====");

      world.markBlockForUpdate(x, y, z);
   }

   @Override
   public void onBlockAdded(World world, int x, int y, int z) {
      //System.out.println("onBlockAdded");
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
      //System.out.println("onNeighbour Changed! + " + world.getBlock(tileX, tileY, tileZ).getUnlocalizedName());
      if (world instanceof World && ((World) world).isRemote)
         return;

     /* if (world.getBlock(tileX, tileY, tileZ) == Blocks.air || world.getBlock(tileX, tileY, tileZ) == OilBlocks.pipe) {
         ForgeDirection direction = Registrator.findMatching(tileX - x, tileY - y, tileZ - z);
         System.out.println("Found match: " + direction.toString());
         EntityPipe pipe = (EntityPipe) world.getTileEntity(x, y, z);
         Block block = world.getBlock(tileX, tileY, tileZ);
         pipe.changeState(direction, (block == Blocks.air ? false : true));
         Network.networkChannel.sendToDimension(new MessagePipeUpdate(pipe), pipe.getWorldObj().provider.dimensionId);
      }*/
      if (world instanceof World)
         updateBlockStatus((World) world, x, y, z);
   }
}
