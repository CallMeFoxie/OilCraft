package cz.ondraster.oilcraft.factory.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cz.ondraster.oilcraft.References;
import cz.ondraster.oilcraft.factory.tileentities.TileEntityValveHT;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockMachineValveHT extends BlockMachineValve {
   public BlockMachineValveHT() {
      super(true);
   }

   @Override
   public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
      return new TileEntityValveHT();
   }


   @SideOnly(Side.CLIENT)
   @Override
   public void registerBlockIcons(IIconRegister icreg) {
      iconOutlet = icreg.registerIcon(References.Textures.VALVEOUTLETHT);
      iconInlet = icreg.registerIcon(References.Textures.VALVEINLETHT);
   }

   @SideOnly(Side.CLIENT)
   @Override
   public IIcon getIcon(int side, int meta) {
      if (side == getDirection(meta).ordinal()) {
         if (isExport(meta))
            return iconOutlet;
         return iconInlet;
      } else
         return FactoryBlocks.blockMachineCasingHT.getIcon(side, meta);
   }
}
