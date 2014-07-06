package cz.ondraster.oilcraft.items;

import cz.ondraster.oilcraft.OilCraft;
import cz.ondraster.oilcraft.References;
import net.minecraft.item.Item;

public class OilWrench extends Item {
   public OilWrench() {
      setUnlocalizedName(References.UnlocalizedNames.ITEMWRENCH);
      setTextureName(References.Icons.ITEMWRENCH);
      setCreativeTab(OilCraft.creativeTab);
   }
}
