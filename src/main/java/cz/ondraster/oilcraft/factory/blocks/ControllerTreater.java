package cz.ondraster.oilcraft.factory.blocks;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ControllerTreater extends MultiblockController {
   protected ControllerTreater() {
      super();
   }

   @Override
   public TileEntity createNewTileEntity(World world, int meta) {
      return new TileEntityMeroxTreater();
   }
}
