package cz.ondraster.oilcraft.factory.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cz.ondraster.oilcraft.References;
import cz.ondraster.oilcraft.factory.multiblock.MultiblockPart;
import cz.ondraster.oilcraft.factory.tileentities.TileEntityFireboxElectric;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockMachineFirebox extends MultiblockPart {
   protected IIcon iconTop;
   protected IIcon iconSide;

   public BlockMachineFirebox() {
      super(RotationsAllowed.SIDEONLY);
      setBlockName(References.UnlocalizedNames.BLOCKELECTRICFIREBOX);
   }

   @Override
   public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
      return new TileEntityFireboxElectric();
   }

   @SideOnly(Side.CLIENT)
   public IIcon getIcon(int side, int meta) {
      if (side == ForgeDirection.UP.ordinal() || side == ForgeDirection.DOWN.ordinal())
         return iconTop;
      else
         return iconSide;
   }

   @SideOnly(Side.CLIENT)
   public void registerBlockIcons(IIconRegister icreg) {
      iconSide = icreg.registerIcon(References.Textures.BLOCKELECTRICFIREBOX);
      iconTop = icreg.registerIcon(References.Textures.SOLIDTOP);
   }
}
