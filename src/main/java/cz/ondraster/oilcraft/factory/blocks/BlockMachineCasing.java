package cz.ondraster.oilcraft.factory.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.IIcon;

public class BlockMachineCasing extends MultiblockPart {
   public BlockMachineCasing() {
      super(RotationsAllowed.SIDEONLY);
   }

   @Override
   @SideOnly(Side.CLIENT)
   public IIcon getIcon(int side, int meta) {
      return this.blockIcon;
   }

}
