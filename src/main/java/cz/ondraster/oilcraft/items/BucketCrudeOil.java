package cz.ondraster.oilcraft.items;

import cz.ondraster.oilcraft.References;
import cz.ondraster.oilcraft.fluids.Fluids;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucket;

public class BucketCrudeOil extends ItemBucket {
   public BucketCrudeOil() {
      super(Fluids.blockFluidCrudeOil);
      this.setCreativeTab(CreativeTabs.tabMaterials);
      this.setUnlocalizedName(References.UnlocalizedNames.BUCKETCRUDEOIL);
      this.setTextureName(References.Textures.BUCKETCRUDEOIL);
      this.setContainerItem(Items.bucket);
   }
}
