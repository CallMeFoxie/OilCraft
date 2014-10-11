package cz.ondraster.oilcraft.factory.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cz.ondraster.oilcraft.References;
import cz.ondraster.oilcraft.factory.multiblock.MultiblockPart;
import cz.ondraster.oilcraft.factory.tileentities.TileEntityFireboxEU;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockMachineFireboxEU extends MultiblockPart {
   protected IIcon iconTop;
   protected IIcon iconSide;

   public BlockMachineFireboxEU() {
      super(RotationsAllowed.SIDEONLY);
      setBlockName(References.UnlocalizedNames.Blocks.BLOCKELECTRICFIREBOXEU);
   }

   @Override
   public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
      return new TileEntityFireboxEU();
   }

   @SideOnly(Side.CLIENT)
   @Override
   public IIcon getIcon(int side, int meta) {
      if (side == ForgeDirection.UP.ordinal() || side == ForgeDirection.DOWN.ordinal())
         return iconTop;
      else
         return iconSide;
   }

   @SideOnly(Side.CLIENT)
   @Override
   public void registerBlockIcons(IIconRegister icreg) {
      iconSide = icreg.registerIcon(References.Textures.BLOCKELECTRICFIREBOXMJ);
      iconTop = icreg.registerIcon(References.Textures.SOLIDTOP);
   }

   @Override
   public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
      TileEntityFireboxEU te = (TileEntityFireboxEU) world.getTileEntity(x, y, z);
      te.onUnload();
      super.breakBlock(world, x, y, z, block, meta);
   }
}
