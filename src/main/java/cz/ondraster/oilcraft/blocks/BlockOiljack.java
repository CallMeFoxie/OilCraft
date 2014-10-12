package cz.ondraster.oilcraft.blocks;

import cz.ondraster.oilcraft.OilCraft;
import cz.ondraster.oilcraft.References;
import cz.ondraster.oilcraft.Registrator;
import cz.ondraster.oilcraft.tileentities.TileEntityOiljack;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockOiljack extends BlockContainer {
   public BlockOiljack() {
      super(Material.anvil);
      this.setCreativeTab(OilCraft.creativeTab);
      this.setBlockTextureName(References.Icons.ICONOILJACK);
      this.setBlockName(References.UnlocalizedNames.Blocks.BLOCKOILJACK);
      Registrator.registerTileEntity(TileEntityOiljack.class, References.Entities.ENTITYOILJACK);
   }

   public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack stack) {
      int l = BlockPistonBase.determineOrientation(world, x, y, z, player);

      if (l == 1 || l == 0)
         l = 2;

      TileEntityOiljack oil = (TileEntityOiljack) world.getTileEntity(x, y, z);
      oil.setOrientation(ForgeDirection.getOrientation(l));
   }

   //This will tell minecraft not to render any side of our cube.
   public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l) {
      return false;
   }

   //And this tell it that you can see through this block, and neighbor blocks should be rendered.
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public TileEntity createNewTileEntity(World world, int meta) {
      return new TileEntityOiljack();
   }

   @Override
   public boolean hasTileEntity(int metadata) {
      return true;
   }

   @Override
   public int getRenderType() {
      return 4;
   }

   @Override
   public void onPostBlockPlaced(World world, int x, int y, int z, int meta) {
      super.onPostBlockPlaced(world, x, y, z, meta);
      setBoundBlocks(world, x, y, z, OilBlocks.invisibleBlock);
   }

   private void setBoundBlocks(World world, int x, int y, int z, Block newBlock) {
      world.setBlock(x, y + 1, z, newBlock);
      world.setBlock(x, y + 2, z, newBlock);
      TileEntityOiljack oiljack = (TileEntityOiljack) world.getTileEntity(x, y, z);

      if (oiljack.getOrientation().offsetX != 0) {
         //world.setBlock(x + oiljack.getOrientation().offsetX, y + 0, z, newBlock);
         world.setBlock(x - oiljack.getOrientation().offsetX, y + 0, z, newBlock);
         world.setBlock(x + oiljack.getOrientation().offsetX, y + 1, z, newBlock);
         world.setBlock(x - oiljack.getOrientation().offsetX, y + 1, z, newBlock);
         world.setBlock(x + oiljack.getOrientation().offsetX, y + 2, z, newBlock);
         world.setBlock(x - oiljack.getOrientation().offsetX, y + 2, z, newBlock);
         if (newBlock == Blocks.air)
            world.setBlock(x + oiljack.getOrientation().getOpposite().offsetX * 2, y + 0, z, newBlock);
         else
            world.setBlock(x + oiljack.getOrientation().getOpposite().offsetX * 2, y + 0, z, OilBlocks.invisibleGenerator);
      }
      if (oiljack.getOrientation().offsetZ != 0) {
         //world.setBlock(x, y + 0, z + oiljack.getOrientation().offsetZ, newBlock);
         world.setBlock(x, y + 0, z - oiljack.getOrientation().offsetZ, newBlock);
         world.setBlock(x, y + 1, z + oiljack.getOrientation().offsetZ, newBlock);
         world.setBlock(x, y + 1, z - oiljack.getOrientation().offsetZ, newBlock);
         world.setBlock(x, y + 2, z + oiljack.getOrientation().offsetZ, newBlock);
         world.setBlock(x, y + 2, z - oiljack.getOrientation().offsetZ, newBlock);
         if (newBlock == Blocks.air)
            world.setBlock(x, y + 0, z + oiljack.getOrientation().getOpposite().offsetZ * 2, newBlock);
         else
            world.setBlock(x, y + 0, z + oiljack.getOrientation().getOpposite().offsetZ * 2, OilBlocks.invisibleGenerator);
      }
   }

   @Override
   public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
      setBoundBlocks(world, x, y, z, Blocks.air);
      super.breakBlock(world, x, y, z, block, meta);
   }
}