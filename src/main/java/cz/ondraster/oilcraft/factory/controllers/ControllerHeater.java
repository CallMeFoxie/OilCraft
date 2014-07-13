package cz.ondraster.oilcraft.factory.controllers;

import cz.ondraster.oilcraft.References;
import cz.ondraster.oilcraft.factory.multiblock.MultiblockController;
import cz.ondraster.oilcraft.factory.tileentities.TileEntityHeater;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ControllerHeater extends MultiblockController {
   public ControllerHeater() {
      super();
      setBlockName(References.UnlocalizedNames.CONTROLLERHEATER);
      setBlockTextureName(References.Textures.CONTROLLERHEATER);
   }

   @Override
   public TileEntity createNewTileEntity(World world, int meta) {
      return new TileEntityHeater();
   }
}
