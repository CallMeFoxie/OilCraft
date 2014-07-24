package cz.ondraster.oilcraft.factory.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cz.ondraster.oilcraft.References;
import cz.ondraster.oilcraft.factory.multiblock.MultiblockPart;
import cz.ondraster.oilcraft.factory.tileentities.TileEntityFireboxSolid;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Random;

public class BlockMachineFireboxSolid extends MultiblockPart {
   protected IIcon iconTop;
   protected IIcon iconSide;

   public BlockMachineFireboxSolid() {
      super(RotationsAllowed.SIDEONLY);
      setBlockName(References.UnlocalizedNames.BLOCKSOLIDFIREBOX);
      this.setTickRandomly(true);
   }

   @Override
   public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
      return new TileEntityFireboxSolid();
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
      iconSide = icreg.registerIcon(References.Textures.BLOCKSOLIDFIREBOX);
      iconTop = icreg.registerIcon(References.Textures.SOLIDTOP);
   }

   @Override
   @SideOnly(Side.CLIENT)
   public void randomDisplayTick(World world, int x, int y, int z, Random random) {
      double d0 = (double) ((float) x + 0.5F);
      double d1 = (double) ((float) y + 0.7F);
      double d2 = (double) ((float) z + 0.5F);
      double offset = 0.55f;
      int side = random.nextInt(4);

      if (side == 0) {
         world.spawnParticle("flame", d0 + offset, d1, d2 + offset, 0.0D, 0.0D, 0.0D);
      } else if (side == 1) {
         world.spawnParticle("flame", d0 - offset, d1, d2 + offset, 0.0D, 0.0D, 0.0D);
      } else if (side == 2) {
         world.spawnParticle("flame", d0 + offset, d1, d2 - offset, 0.0D, 0.0D, 0.0D);
      } else if (side == 3) {
         world.spawnParticle("flame", d0 - offset, d1, d2 - offset, 0.0D, 0.0D, 0.0D);
      }
   }
}
