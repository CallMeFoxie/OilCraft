package cz.ondraster.oilcraft.factory.blocks;

import cz.ondraster.oilcraft.References;
import cz.ondraster.oilcraft.Registrator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ControllerTreater extends MultiblockController {
   protected ControllerTreater() {
      super();

      setBlockName(References.UnlocalizedNames.CONTROLLERTREATER);
      setBlockTextureName(References.Textures.CONTROLLERTREATER);
      Registrator.registerTileEntity(TileEntityTreater.class, References.Entities.TREATER);
   }

   @Override
   public TileEntity createNewTileEntity(World world, int meta) {
      return new TileEntityTreater();
   }
}
