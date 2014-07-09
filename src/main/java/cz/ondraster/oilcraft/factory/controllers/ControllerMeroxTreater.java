package cz.ondraster.oilcraft.factory.controllers;

import cz.ondraster.oilcraft.References;
import cz.ondraster.oilcraft.factory.multiblock.MultiblockController;
import cz.ondraster.oilcraft.factory.tileentities.TileEntityMeroxTreater;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ControllerMeroxTreater extends MultiblockController {
   public ControllerMeroxTreater() {
      super();
      setBlockName(References.UnlocalizedNames.CONTROLLERTREATER);
      setBlockTextureName(References.Textures.CONTROLLERTREATER);
   }

   @Override
   public TileEntity createNewTileEntity(World world, int meta) {
      return new TileEntityMeroxTreater();
   }
}
