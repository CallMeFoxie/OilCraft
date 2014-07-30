package cz.ondraster.oilcraft.items;

import cz.ondraster.oilcraft.OilCraft;
import cz.ondraster.oilcraft.References;
import cz.ondraster.oilcraft.blocks.OilBlocks;
import cz.ondraster.oilcraft.blocks.RubberWood;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ResinExtractor extends Item {
   public ResinExtractor() {
      setTextureName(References.Textures.RESINEXTRACTOR);
      setUnlocalizedName(References.UnlocalizedNames.RESINEXTRACTOR);
      setMaxStackSize(1);
      setMaxDamage(10);
      setCreativeTab(OilCraft.creativeTab);
   }

   @Override
   public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
      if (world.isRemote || item.getItemDamage() == 10)
         return false;

      Block block = world.getBlock(x, y, z);
      if (block == OilBlocks.rubberWood) {
         if (RubberWood.scoopResin(world, x, y, z)) {
            setDamage(item, getDamage(item) + 1);
            return true;
         }
      }
      return super.onItemUse(item, player, world, x, y, z, side, hitX, hitY, hitZ);
   }
}
