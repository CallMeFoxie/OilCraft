package cz.ondraster.oilcraft.items;

import cz.ondraster.oilcraft.OilCraft;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucket;

public class OilCraftBucket extends ItemBucket {
   public OilCraftBucket(Block fluid) {
      super(fluid);
      this.setCreativeTab(OilCraft.creativeTab);
      this.setContainerItem(Items.bucket);
   }
}
