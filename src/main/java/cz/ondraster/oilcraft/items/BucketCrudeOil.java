package cz.ondraster.oilcraft.items;

import cz.ondraster.oilcraft.OilCraft;
import cz.ondraster.oilcraft.References;
import cz.ondraster.oilcraft.fluids.Fluids;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucket;

public class BucketCrudeOil extends ItemBucket {
   public BucketCrudeOil() {
      super(Fluids.blockFluidCrudeOil);
      this.setCreativeTab(OilCraft.creativeTab);
      this.setUnlocalizedName(References.UnlocalizedNames.BUCKETCRUDEOIL);
      this.setTextureName(References.Textures.BUCKETCRUDEOIL);
      this.setContainerItem(Items.bucket);
   }
}
