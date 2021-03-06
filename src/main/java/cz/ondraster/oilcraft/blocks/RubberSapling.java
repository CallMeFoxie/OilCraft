package cz.ondraster.oilcraft.blocks;

import cz.ondraster.oilcraft.OilCraft;
import cz.ondraster.oilcraft.References;
import cz.ondraster.oilcraft.worldgen.RubberTreeGenerator;
import net.minecraft.block.BlockBush;
import net.minecraft.world.World;

import java.util.Random;

public class RubberSapling extends BlockBush {
   public RubberSapling() {
      super();
      this.setCreativeTab(OilCraft.creativeTab);
      this.setBlockName(References.UnlocalizedNames.Blocks.RUBBERSAPLING);
      this.setBlockTextureName(References.Textures.RUBBERSAPLING);
      setTickRandomly(true);
   }

   @Override
   public void updateTick(World world, int x, int y, int z, Random random) {
      if (random.nextInt(3) == 0) {
         RubberTreeGenerator.growTree(world, x, y, z, random);
      }
   }
}
