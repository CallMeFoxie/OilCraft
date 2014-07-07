package cz.ondraster.oilcraft.factory.blocks;

import cz.ondraster.oilcraft.References;

public class BlockMachineHeater extends MultiblockPart {
   public BlockMachineHeater() {
      super(RotationsAllowed.SIDEONLY);
      setBlockName(References.UnlocalizedNames.BLOCKELECTRICHEATER);
   }
}
