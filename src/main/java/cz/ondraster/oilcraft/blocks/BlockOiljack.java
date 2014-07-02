package cz.ondraster.oilcraft.blocks;

import cz.ondraster.oilcraft.OilCraft;
import cz.ondraster.oilcraft.References;
import cz.ondraster.oilcraft.entities.EntityOiljack;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockOiljack extends BlockContainer {
   public BlockOiljack() {
      super(Material.anvil);
      this.setCreativeTab(OilCraft.creativeTab);
      this.setBlockTextureName(References.Icons.ICONOILJACK);
      this.setBlockName(References.UnlocalizedNames.BLOCKOILJACK);
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