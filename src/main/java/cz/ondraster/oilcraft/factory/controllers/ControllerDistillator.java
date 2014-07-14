package cz.ondraster.oilcraft.factory.controllers;

import cz.ondraster.oilcraft.factory.multiblock.MultiblockController;
import cz.ondraster.oilcraft.factory.tileentities.TIleEntityDistillator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ControllerDistillator extends MultiblockController {
   @Override
   public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
      return new TIleEntityDistillator();
   }
}
