package cz.ondraster.oilcraft.factory.blocks;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockMachineValveHT extends BlockMachineValve {
   public BlockMachineValveHT() {
      super(true);

   }

   @Override
   public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
      return new TileEntityValveHT();
   }
}
