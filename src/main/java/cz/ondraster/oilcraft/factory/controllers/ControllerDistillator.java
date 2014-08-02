package cz.ondraster.oilcraft.factory.controllers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cz.ondraster.oilcraft.References;
import cz.ondraster.oilcraft.factory.FactoryBlocks;
import cz.ondraster.oilcraft.factory.multiblock.MultiblockController;
import cz.ondraster.oilcraft.factory.tileentities.TileEntityDistillator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ControllerDistillator extends MultiblockController {
   public ControllerDistillator() {
      super();
      setBlockName(References.UnlocalizedNames.CONTROLLERDISTILLATOR);
      setBlockTextureName(References.Textures.CONTROLLERDISTILLATOR);
   }

   @Override
   public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
      return new TileEntityDistillator();
   }

   @SideOnly(Side.CLIENT)
   public IIcon getIcon(int side, int meta) {
      if (side == meta)
         return this.blockIcon;
      else
         return FactoryBlocks.blockMachineCasingHT.getIcon(side, meta);
   }
}
