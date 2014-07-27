package cz.ondraster.oilcraft.factory.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cz.ondraster.oilcraft.factory.multiblock.MultiblockPart;
import net.minecraft.world.World;

import java.util.Random;

public abstract class BlockMachineFirebox extends MultiblockPart {
   protected BlockMachineFirebox(RotationsAllowed rotation) {
      super(rotation);
   }

   @Override
   @SideOnly(Side.CLIENT)
   public void randomDisplayTick(World world, int x, int y, int z, Random random) {
      double d0 = (double) ((float) x + 0.5F);
      double d1 = (double) ((float) y + 0.7F);
      double d2 = (double) ((float) z + 0.5F);
      double offset = 0.55f;
      int side = random.nextInt(4);

      if (side == 0) {
         world.spawnParticle("flame", d0 + offset, d1, d2 + offset, 0.0D, 0.0D, 0.0D);
      } else if (side == 1) {
         world.spawnParticle("flame", d0 - offset, d1, d2 + offset, 0.0D, 0.0D, 0.0D);
      } else if (side == 2) {
         world.spawnParticle("flame", d0 + offset, d1, d2 - offset, 0.0D, 0.0D, 0.0D);
      } else if (side == 3) {
         world.spawnParticle("flame", d0 - offset, d1, d2 - offset, 0.0D, 0.0D, 0.0D);
      }
   }
}
