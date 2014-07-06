package cz.ondraster.oilcraft.factory.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cz.ondraster.oilcraft.OilCraft;
import net.minecraft.util.IIcon;

public class BlockMachineCasing extends MultiblockPart {
   public BlockMachineCasing() {
      super();
      setCreativeTab(OilCraft.creativeTab);
   }

   @Override
   @SideOnly(Side.CLIENT)
   public IIcon getIcon(int side, int meta) {
      return this.blockIcon;
   }

}
