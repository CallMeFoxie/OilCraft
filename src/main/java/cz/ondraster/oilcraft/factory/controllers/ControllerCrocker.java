package cz.ondraster.oilcraft.factory.controllers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cz.ondraster.oilcraft.References;
import cz.ondraster.oilcraft.factory.FactoryBlocks;
import cz.ondraster.oilcraft.factory.multiblock.MultiblockController;
import cz.ondraster.oilcraft.factory.tileentities.TileEntityCrocker;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ControllerCrocker extends MultiblockController {
   public ControllerCrocker() {
      super();
      setBlockName(References.UnlocalizedNames.CONTROLLERCROCKER);
      setBlockTextureName(References.Textures.CONTROLLERCROCKER);
   }

   @Override
   public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
      return new TileEntityCrocker();
   }

   @SideOnly(Side.CLIENT)
   public IIcon getIcon(int side, int meta) {
      if (side == meta)
         return this.blockIcon;
      else
         return FactoryBlocks.blockMachineCasingHT.getIcon(side, meta);
   }
}
