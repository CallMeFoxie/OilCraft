package cz.ondraster.oilcraft.handlers;


import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cz.ondraster.oilcraft.blocks.OilBlocks;
import cz.ondraster.oilcraft.fluids.BlockFluid;
import cz.ondraster.oilcraft.worldgen.RubberTree;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.FillBucketEvent;

import java.util.Random;

public class Events {

   @SubscribeEvent
   public void onBucketFill(FillBucketEvent event) {

      ItemStack result = fillCustomBucket(event.world, event.target);

      if (result == null)
         return;

      event.result = result;
      event.setResult(Event.Result.ALLOW);
   }

   public ItemStack fillCustomBucket(World world, MovingObjectPosition pos) {
      Block block = world.getBlock(pos.blockX, pos.blockY, pos.blockZ);

      if (block instanceof BlockFluid && world.getBlockMetadata(pos.blockX, pos.blockY, pos.blockZ) == 0) {
         world.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.air);
         return new ItemStack(((BlockFluid) block).getBucket());
      } else
         return null;
   }

   @SubscribeEvent
   public void onUseBonemeal(BonemealEvent event) {
      if (event.world.isRemote)
         return;

      Block block = event.world.getBlock(event.x, event.y, event.z);
      if (block != OilBlocks.rubberSapling)
         return;

      Random random = new Random(event.world.getSeed());
      if (random.nextInt(3) == 0) {
         RubberTree.growTree(event.world, event.x, event.y, event.z, random);
      }

      event.setResult(Event.Result.ALLOW);
   }

}
