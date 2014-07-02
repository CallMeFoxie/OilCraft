package cz.ondraster.oilcraft;

import cz.ondraster.oilcraft.items.ItemIcon;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabOilCraft extends CreativeTabs {
   Item iconItem;

   public CreativeTabOilCraft() {
      super(References.UnlocalizedNames.TABNAME);
      iconItem = new ItemIcon(References.Icons.TABICON);
      Registrator.registerItem(iconItem);
   }

   @Override
   public Item getTabIconItem() {
      return iconItem;
   }
}
