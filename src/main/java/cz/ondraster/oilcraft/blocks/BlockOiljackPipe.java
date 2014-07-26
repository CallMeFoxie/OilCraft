package cz.ondraster.oilcraft.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cz.ondraster.oilcraft.OilCraft;
import cz.ondraster.oilcraft.References;
import cz.ondraster.oilcraft.Registrator;
import cz.ondraster.oilcraft.entities.EntityOiljackPipe;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
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

   @Override
   public boolean hasTileEntity(int metadata) {
      return true;
   }

   @Override
   public void breakBlock(World world, int x, int y, int z, Block block, int meta) {

      TileEntity tileEntity = world.getTileEntity(x, y, z);
      if (tileEntity instanceof EntityOiljackPipe)
         ((EntityOiljackPipe) tileEntity).clearActualPipes();

      super.breakBlock(world, x, y, z, block, meta);
   }

   @SideOnly(Side.CLIENT)
   @Override
   public IIcon getIcon(int side, int meta) {
      if (side == 0 || side == 1) {
         return OilBlocks.blockActualPipe.getIcon(side, meta);
      } else
         return blockIcon;
   }


}
