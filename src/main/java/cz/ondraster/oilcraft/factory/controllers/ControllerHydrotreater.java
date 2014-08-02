package cz.ondraster.oilcraft.factory.controllers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cz.ondraster.oilcraft.References;
import cz.ondraster.oilcraft.factory.FactoryBlocks;
import cz.ondraster.oilcraft.factory.multiblock.MultiblockController;
import cz.ondraster.oilcraft.factory.tileentities.TileEntityHydrotreater;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ControllerHydrotreater extends MultiblockController {
   public ControllerHydrotreater() {
      super();
      setBlockName(References.UnlocalizedNames.CONTROLLERHYDROTREATER);
      setBlockTextureName(References.Textures.CONTROLLERHYDROTREATER);
   }

   @Override
   public TileEntity createNewTileEntity(World world, int meta) {
      return new TileEntityHydrotreater();
   }

   @SideOnly(Side.CLIENT)
   public IIcon getIcon(int side, int meta) {
      if (side == meta)
         return this.blockIcon;
      else
         return FactoryBlocks.blockMachineCasingHT.getIcon(side, meta);
   }
}
