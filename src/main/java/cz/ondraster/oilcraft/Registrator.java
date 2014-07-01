package cz.ondraster.oilcraft;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.oredict.OreDictionary;

public class Registrator {
   public static void registerBlock(Block block) {
      GameRegistry.registerBlock(block, References.MODID + "_" + block.getUnlocalizedName().substring(5));
   }

   public static void registerItem(Item item) {
      GameRegistry.registerItem(item, References.MODID + "_" + item.getUnlocalizedName().substring(5));
   }

   public static void registerMultiBlock(Block block, Class myClass) {
      GameRegistry.registerBlock(block, myClass, References.MODID + "_" + block.getUnlocalizedName().substring(5));
   }

   public static void registerTileEntity(Class<? extends TileEntity> te, String unlocalizedname) {
      GameRegistry.registerTileEntity(te, References.MODID + "_" + unlocalizedname);
   }

   public static void registerOreDict(Item item, String oreName) {
      OreDictionary.registerOre(oreName, new ItemStack(item));
   }

   public static ForgeDirection findMatching(int x, int y, int z) {
      for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
         if (dir.offsetX == x && dir.offsetY == y && dir.offsetZ == z)
            return dir;
      }

      return ForgeDirection.DOWN;
   }
}
