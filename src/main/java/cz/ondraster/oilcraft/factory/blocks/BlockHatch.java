package cz.ondraster.oilcraft.factory.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cz.ondraster.oilcraft.OilCraft;
import cz.ondraster.oilcraft.References;
import cz.ondraster.oilcraft.factory.FactoryBlocks;
import cz.ondraster.oilcraft.factory.multiblock.MultiblockPartEntity;
import cz.ondraster.oilcraft.factory.tileentities.TileEntityHatch;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockHatch extends MultiblockPartEntity {
   public BlockHatch() {
      super(RotationsAllowed.SIDEONLY);
      setBlockTextureName(References.Textures.BLOCKHATCH);
      setBlockName(References.UnlocalizedNames.Blocks.BLOCKHATCH);
   }

   @Override
   public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
      return new TileEntityHatch();
   }


   @SideOnly(Side.CLIENT)
   @Override
   public IIcon getIcon(int side, int meta) {
      if (side == getDirection(meta).ordinal())
         return blockIcon;
      else
         return FactoryBlocks.blockMachineCasing.getIcon(side, meta);
   }

   @Override
   public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
                                   float hitY, float hitZ) {
      if (!player.isSneaking()) {
         player.openGui(OilCraft.instance, References.GUIs.guiHatch, world, x, y, z);
         return true;
      }

      return false;
   }
}
