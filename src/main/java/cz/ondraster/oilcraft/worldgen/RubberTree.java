package cz.ondraster.oilcraft.worldgen;

import cpw.mods.fml.common.IWorldGenerator;
import cz.ondraster.oilcraft.blocks.OilBlocks;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Random;

public class RubberTree implements IWorldGenerator {

   public static boolean growTree(World world, int x, int y, int z, Random random) {
      int height = random.nextInt(2) + 4;

      boolean succeeded = true;

      for (int i = 0; i < height; i++) {
         if (!setBlockIfAir(world, x, y + i, z, OilBlocks.rubberWood)) {
            succeeded = false;
         }
      }

      int rubberSpot = random.nextInt(height);

      if (succeeded)
         world.setBlockMetadataWithNotify(x, y + rubberSpot, z, 0x03, 2);

      setBlockIfAir(world, x, y + height + 1, z, OilBlocks.rubberLeaf);

      for (int i = x - 1; i <= x + 1; i++)
         for (int j = z - 1; j <= z + 1; j++)
            setBlockIfAir(world, i, y + height, j, OilBlocks.rubberLeaf); // top layer

      for (int i = x - 2; i <= x + 2; i++)
         for (int j = z - 2; j <= z + 2; j++)
            if (!(i == x && j == z))
               for (int k = y + height - 1; k >= y + height - 2; k--)
                  setBlockIfAir(world, i, k, j, OilBlocks.rubberLeaf); // not top layer

      return succeeded;
   }

   public static boolean setBlockIfAir(World world, int x, int y, int z, Block newBlock) {
      if (world.isAirBlock(x, y, z)) {
         world.setBlock(x, y, z, newBlock);
         return true;
      }
      return false;
   }

   public boolean generate(World world, Random random, int x, int z) {
      int highest = world.getHeightValue(x, z);
      Block block = world.getTopBlock(x, z);

      if (block.canSustainPlant(world, x, highest, z, ForgeDirection.UP, (IPlantable) OilBlocks.rubberSapling)) {
         return growTree(world, x, highest + 1, z, random);
      }

      return false;
   }

   @Override
   public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
      if (random.nextInt(10) == 0)
         generate(world, random, chunkX * 16 + random.nextInt(16), chunkZ * 16 + random.nextInt(16));
   }
}
