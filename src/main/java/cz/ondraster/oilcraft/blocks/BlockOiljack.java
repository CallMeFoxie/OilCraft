package cz.ondraster.oilcraft.blocks;

import cz.ondraster.oilcraft.OilCraft;
import cz.ondraster.oilcraft.References;
import cz.ondraster.oilcraft.Registrator;
import cz.ondraster.oilcraft.entities.EntityOiljack;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
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
      this.setBlockName(References.UnlocalizedNames.BLOCKOILJACK);
      Registrator.registerTileEntity(EntityOiljack.class, References.Entities.ENTITYOILJACK);
   }

   public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack stack) {
      int l = BlockPistonBase.determineOrientation(world, x, y, z, player);

      if (l == 1 || l == 0)
         l = 2;

      EntityOiljack oil = (EntityOiljack) world.getTileEntity(x, y, z);
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
      return new EntityOiljack();
   }

   @Override
   public boolean hasTileEntity(int metadata) {
      return true;
   }

   @Override
   public int getRenderType() {
      return 4;
   }
}