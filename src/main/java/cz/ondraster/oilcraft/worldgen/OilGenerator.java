package cz.ondraster.oilcraft.worldgen;

import cpw.mods.fml.common.IWorldGenerator;
import cz.ondraster.oilcraft.blocks.OilBlocks;
import cz.ondraster.oilcraft.fluids.Fluids;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.BiomeDictionary;

import java.util.Random;

public class OilGenerator implements IWorldGenerator {
   public static void generateOil(World world, int x, int z, Random random) {
      //int middleY = 64; // against which to generate
      int height = world.getHeightValue(x, z);
      int middleY = (height / 2) + random.nextInt(Math.min(15, height - 1));

      int radius = 6;

      if (random.nextDouble() <= 0.1) // 10%
         radius = 7;
      if (random.nextDouble() <= 0.03) // 3%
         radius = 8;

      // generate the main column

      for (int i = middleY - (height / 2); i < middleY + (height / 2); i++) {
         setBlockIfAir(world, x, i, z, Fluids.blockFluidCrudeOil);
      }

      // now generate the rings.

      for (int r = radius; r > 0; r--) {
         for (int X = x - r; X <= x + r; X++) {
            //int maxZ = (int) (Math.round(Math.sqrt(radius * radius - (radius - r) * (radius - r)))) - 2;
            //setBlockIfAir(world, X, middleY - (height / 2) + r, x - 1, Fluids.blockFluidCrudeOil);
            //setBlockIfAir(world, X, middleY - (height / 2) + r, x + 1, Fluids.blockFluidCrudeOil);
            for (int Z = 0; Z <= r; Z++) {
               setBlockIfAir(world, X, middleY - (height / 2) + r, z + Z, Fluids.blockFluidCrudeOil);
               setBlockIfAir(world, X, middleY - (height / 2) + r, z - Z, Fluids.blockFluidCrudeOil);
            }
         }
      }

      Block stone = Blocks.stonebrick;
      int metaMossy = 1;
      int metaCracked = 2;

      // now the mining rig platform..
      if (random.nextDouble() <= 0.5) {
         for (int i = middleY + 1; i <= middleY + (height / 2); i++) {
            setBlockWithProbability(world, x - 1, i, z - 1, random);
            setBlockWithProbability(world, x + 1, i, z - 1, random);
            setBlockWithProbability(world, x - 1, i, z + 1, random);
            setBlockWithProbability(world, x + 1, i, z + 1, random);
         }

         // now the main platform
         setBlockWithProbability(world, x, middleY + (height / 2), z - 1, random);
         setBlockWithProbability(world, x, middleY + (height / 2), z + 1, random);
         setBlockWithProbability(world, x - 1, middleY + (height / 2), z, random);
         setBlockWithProbability(world, x + 1, middleY + (height / 2), z, random);

         for (int ix = x - 2; ix <= x + 2; ix++) {
            setBlockWithProbability(world, ix, middleY + (height / 2), z - 2, random);
            setBlockWithProbability(world, ix, middleY + (height / 2), z + 2, random);
         }
         for (int iz = z - 2; iz <= z + 2; iz++) {
            setBlockWithProbability(world, x - 2, middleY + (height / 2), iz, random);
            setBlockWithProbability(world, x + 2, middleY + (height / 2), iz, random);
         }

         if (random.nextDouble() <= 0.3) {
            setBlockIfAir(world, x, middleY + (height / 2) + 1, z, OilBlocks.oiljackPipe);
         }

         if (random.nextDouble() <= 0.3) {
            setBlockIfAir(world, x, middleY + (height / 2) + 1, z + 1, OilBlocks.oiljack);
         }
      }
   }

   private static void setBlockWithProbability(World world, int x, int y, int z, Random random) {
      if (random.nextDouble() <= 0.8) {
         setBlockIfAir(world, x, y, z, Blocks.stonebrick);
         setMossyWithProbability(world, x, y, z, random);
      }
   }

   private static void setMossyWithProbability(World world, int x, int y, int z, Random random) {
      if (random.nextDouble() <= 0.3) {
         world.setBlockMetadataWithNotify(x, y, z, 1, 2);
      } else if (random.nextDouble() <= 0.3) {
         world.setBlockMetadataWithNotify(x, y, z, 2, 2);
      }
   }


   public static boolean setBlockIfAir(World world, int x, int y, int z, Block newBlock) {
      world.setBlock(x, y, z, newBlock);
      return true;
   }

   @Override
   public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
      int x = chunkX * 16 + random.nextInt(16);
      int z = chunkZ * 16 + random.nextInt(16);

      if (random.nextInt(50) == 0 && BiomeDictionary.isBiomeOfType(world.getWorldChunkManager().getBiomeGenAt(x, z), BiomeDictionary.Type.SANDY)) {
         generateOil(world, x, z, random);
      }


   }
}
