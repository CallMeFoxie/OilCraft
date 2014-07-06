package cz.ondraster.oilcraft.handlers;


import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cz.ondraster.oilcraft.fluids.BlockFluid;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.FillBucketEvent;

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

}
