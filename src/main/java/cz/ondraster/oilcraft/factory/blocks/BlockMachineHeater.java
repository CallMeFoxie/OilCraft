package cz.ondraster.oilcraft.factory.blocks;

import cz.ondraster.oilcraft.References;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockMachineHeater extends MultiblockPart {
   public BlockMachineHeater() {
      super(RotationsAllowed.SIDEONLY);
      setBlockName(References.UnlocalizedNames.BLOCKELECTRICHEATER);
   }

   @Override
   public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
      return new TileEntityPart();
   }
}
