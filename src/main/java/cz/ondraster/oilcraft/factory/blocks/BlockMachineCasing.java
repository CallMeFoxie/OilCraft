package cz.ondraster.oilcraft.factory.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;

public class BlockMachineCasing extends MultiblockPart {
   public BlockMachineCasing() {
      super(RotationsAllowed.SIDEONLY);
   }

   @Override
   @SideOnly(Side.CLIENT)
   public IIcon getIcon(int side, int meta) {
      return this.blockIcon;
   }

   public void addInformation(ItemStack item, EntityPlayer player, List list, boolean noidea) {
      list.add(new String("Safe for decoration"));
   }

   @Override
   public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
      return new TileEntityPart();
   }
}
