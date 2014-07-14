package cz.ondraster.oilcraft.factory.multiblock;

import cz.ondraster.oilcraft.OilCraft;
import cz.ondraster.oilcraft.factory.tileentities.TileEntityController;
import cz.ondraster.oilcraft.items.OilItems;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class MultiblockController extends BlockContainer {
   public MultiblockController() {
      super(Material.iron);
      setCreativeTab(OilCraft.creativeTab);
   }

   public void onBlockPreDestroy(World world, int x, int y, int z, int meta) {
      TileEntityController tec = (TileEntityController) world.getTileEntity(x, y, z);
      tec.reset();
   }

   public void onPostBlockPlaced(World world, int x, int y, int z, int meta) {
      TileEntityController tec = (TileEntityController) world.getTileEntity(x, y, z);
      tec.checkMultiblock();
   }

   public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack stack) {
      int l = BlockPistonBase.determineOrientation(world, x, y, z, player);
      if (l == 1 || l == 0)
         l = 2;

      world.setBlockMetadataWithNotify(x, y, z, l, 3);
   }

   public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
      if (player.getCurrentEquippedItem() == null)
         return false;

      if (player.getCurrentEquippedItem().getItem() == OilItems.wrench) {
         TileEntityController tec = (TileEntityController) world.getTileEntity(x, y, z);
         if (!tec.isFormed())
            tec.checkMultiblock();
      }

      return false;
   }
}
