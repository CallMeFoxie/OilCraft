package cz.ondraster.oilcraft.factory.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cz.ondraster.oilcraft.OilCraft;
import cz.ondraster.oilcraft.References;
import cz.ondraster.oilcraft.items.OilItems;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.List;

public class MultiblockController extends BlockContainer {
   protected MultiblockController() {
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
      if (player.getCurrentEquippedItem().getItem() == OilItems.wrench) {
         TileEntityController tec = (TileEntityController) world.getTileEntity(x, y, z);
         if (!tec.isFormed())
            tec.checkMultiblock();
      }

      return false;
   }

   @Override
   public TileEntity createNewTileEntity(World world, int meta) {
      switch (meta) {
         case References.FactoryControllers.MEROXTREATER:
            return new TileEntityMeroxTreater();
         case References.FactoryControllers.ASPHALT:
            return null; // TODO ASPHALT Factory Controller
         case References.FactoryControllers.COKER:
            return null; // TODO COKER Factory Controller
         case References.FactoryControllers.CROCKER:
            return null; // TODO CROCKER Factory Controller
         case References.FactoryControllers.GASPROC:
            return null; // TODO GASPROC Factory Controller
         case References.FactoryControllers.HYDROTREATER:
            return null; // TODO HYDROTREATER Factory Controller
      }
      return null;
   }

   @SideOnly(Side.CLIENT)
   public void getSubBlocks(Item item, CreativeTabs tab, List list) {
      list.add(new ItemStack(item, 1, References.FactoryControllers.MEROXTREATER));
      list.add(new ItemStack(item, 1, References.FactoryControllers.ASPHALT));
      list.add(new ItemStack(item, 1, References.FactoryControllers.COKER));
      list.add(new ItemStack(item, 1, References.FactoryControllers.CROCKER));
      list.add(new ItemStack(item, 1, References.FactoryControllers.GASPROC));
      list.add(new ItemStack(item, 1, References.FactoryControllers.HYDROTREATER));
   }
}
