package cz.ondraster.oilcraft.factory.multiblock;

import cz.ondraster.oilcraft.OilCraft;
import cz.ondraster.oilcraft.factory.tileentities.TileEntityController;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public abstract class MultiblockPart extends BlockContainer {

   private RotationsAllowed allowRotation = RotationsAllowed.IGNORE;

   protected MultiblockPart(RotationsAllowed rotation) {
      super(Material.iron);
      allowRotation = rotation;
      setCreativeTab(OilCraft.creativeTab);
   }

   //protected MultiblockPart() {
   //   super(Material.iron);
   //}

   public void onBlockPreDestroy(World world, int x, int y, int z, int meta) {
      notifyController(world, x, y, z);
   }

   public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
      return meta;
   }

   public void resetStatus(World world, int x, int y, int z) {
      int meta = world.getBlockMetadata(x, y, z);
      world.setBlockMetadataWithNotify(x, y, z, 0, 3);

      for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
         Block block = world.getBlock(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ);
         if (block instanceof MultiblockPart) {
            if (world.getBlockMetadata(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ) == meta + 1)
               ((MultiblockPart) block).resetStatus(world, x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ);
         }
      }
   }

   public void notifyController(World world, int x, int y, int z) {
      int meta = world.getBlockMetadata(x, y, z);
      world.setBlockMetadataWithNotify(x, y, z, 0, 0);

      for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
         Block block = world.getBlock(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ);
         if (block instanceof MultiblockPart) {
            int blockmeta = world.getBlockMetadata(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ);
            if ((blockmeta == meta - 1 && meta != 0) || blockmeta == 0)
               ((MultiblockPart) block).resetStatus(world, x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ);
         } else if (block instanceof MultiblockController) {
            TileEntityController tec = (TileEntityController) world.getTileEntity(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ);
            tec.reset();
         }
      }
   }

   public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack stack) {
      if (allowRotation == RotationsAllowed.IGNORE)
         return;

      int l = BlockPistonBase.determineOrientation(world, x, y, z, player);

      if (allowRotation == RotationsAllowed.SIDEONLY)
         if (l == 1 || l == 0)
            l = 2;

      world.setBlockMetadataWithNotify(x, y, z, l, 3);
   }


   public enum RotationsAllowed {
      ALL,
      IGNORE,
      SIDEONLY
   }
}
