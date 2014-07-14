package cz.ondraster.oilcraft.factory.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cz.ondraster.oilcraft.OilCraft;
import cz.ondraster.oilcraft.References;
import cz.ondraster.oilcraft.factory.multiblock.MultiblockPartEntity;
import cz.ondraster.oilcraft.factory.tileentities.TileEntityValve;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockMachineValve extends MultiblockPartEntity {

   protected IIcon iconOutlet;

   public BlockMachineValve() {
      super(RotationsAllowed.SIDEONLY);

   }

   protected BlockMachineValve(boolean noRegister) {
      super(RotationsAllowed.SIDEONLY);
   }

   @Override
   public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
      return new TileEntityValve();
   }

   @Override
   public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
                                   float hitY, float hitZ) {
      if (!player.isSneaking()) {
         player.openGui(OilCraft.instance, References.GUIs.guiValve, world, x, y, z);
         return true;
      }

      return false;
   }

   @SideOnly(Side.CLIENT)
   @Override
   public void registerBlockIcons(IIconRegister icreg) {
      iconOutlet = icreg.registerIcon(References.Textures.VALVEOUTLET);
   }

   @SideOnly(Side.CLIENT)
   @Override
   public IIcon getIcon(int side, int meta) {
      if (side == meta)
         return iconOutlet;
      else
         return FactoryBlocks.blockMachineCasing.getIcon(side, meta);
   }
}
