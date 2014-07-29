package cz.ondraster.oilcraft.blocks;

import cz.ondraster.oilcraft.OilCraft;
import cz.ondraster.oilcraft.References;
import cz.ondraster.oilcraft.worldgen.RubberTree;
import net.minecraft.block.BlockBush;
import net.minecraft.world.World;

import java.util.Random;

public class RubberSapling extends BlockBush {
   public RubberSapling() {
      super();
      this.setCreativeTab(OilCraft.creativeTab);
      this.setBlockName(References.UnlocalizedNames.RUBBERSAPLING);
      this.setBlockTextureName(References.Textures.RUBBERSAPLING);
      setTickRandomly(true);
   }

   @Override
   public void updateTick(World world, int x, int y, int z, Random random) {
      if (random.nextInt(3) == 0) {
         RubberTree.growTree(world, x, y, z, random);
      }
   }
}
