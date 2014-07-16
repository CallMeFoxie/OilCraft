package cz.ondraster.oilcraft.factory.multiblock;

import cz.ondraster.oilcraft.OilCraft;
import cz.ondraster.oilcraft.factory.tileentities.TileEntityController;
import cz.ondraster.oilcraft.factory.tileentities.TileEntityPart;
import cz.ondraster.oilcraft.factory.tileentities.TileEntityPartWithInventory;
import cz.ondraster.oilcraft.items.OilItems;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

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

   public void notifyController(World world, int x, int y, int z) {
      TileEntity tileEntity = world.getTileEntity(x, y, z);
      TileEntity master = null;
      if (tileEntity instanceof TileEntityPart)
         master = ((TileEntityPart) tileEntity).getMaster();
      else if (tileEntity instanceof TileEntityPartWithInventory)
         master = ((TileEntityPartWithInventory) tileEntity).getMaster();

      if (master != null && master instanceof TileEntityController)
         ((TileEntityController) master).reset();
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

   public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
      if (player.getCurrentEquippedItem() == null || world.isRemote)
         return false;

      if (player.getCurrentEquippedItem().getItem() == OilItems.wrench) {
         TileEntity te = world.getTileEntity(x, y, z);
         if (te instanceof TileEntityPart)
            ((TileEntityPart) te).getMaster();
      }

      return false;
   }


   public enum RotationsAllowed {
      ALL,
      IGNORE,
      SIDEONLY
   }
}
