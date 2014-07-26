package cz.ondraster.oilcraft.blocks;

import cz.ondraster.oilcraft.OilCraft;
import cz.ondraster.oilcraft.References;
import cz.ondraster.oilcraft.Registrator;
import cz.ondraster.oilcraft.entities.EntityOiljackPipe;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockOiljackPipe extends BlockContainer {
   protected BlockOiljackPipe() {
      super(Material.anvil);
      setBlockName(References.UnlocalizedNames.BLOCKOILJACKPIPE);
      setCreativeTab(OilCraft.creativeTab);
      setBlockTextureName(References.Textures.BLOCKOILJACKPIPE);
      setBlockBounds(0f, 0f, 0f, 1f, 1f, 1f);
      Registrator.registerTileEntity(EntityOiljackPipe.class, References.Entities.ENTITYOILJACKPIPE);
   }

   @Override
   public TileEntity createNewTileEntity(World world, int meta) {
      return new EntityOiljackPipe();
   }

   public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l) {
      return false;
   }

   public boolean isOpaqueCube() {
      return false;
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
