package cz.ondraster.oilcraft.items;

import cz.ondraster.oilcraft.OilCraft;
import cz.ondraster.oilcraft.References;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ItemMatchBox extends Item {
   public ItemMatchBox() {
      super();
      setUnlocalizedName(References.UnlocalizedNames.ITEMMATCHES);
      setTextureName(References.Textures.ITEMMATCHES);
      setMaxDamage(10);
      setCreativeTab(OilCraft.creativeTab);
      setMaxStackSize(1);
   }

   public void addInformation(ItemStack item, EntityPlayer player, List list, boolean noidea) {
      list.add(new String("Do not put used matches back in the box!"));
   }
}
