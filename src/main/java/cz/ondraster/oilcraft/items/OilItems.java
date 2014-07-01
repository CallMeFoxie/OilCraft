package cz.ondraster.oilcraft.items;

import cz.ondraster.oilcraft.References;
import cz.ondraster.oilcraft.Registrator;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;

public class OilItems {
   public static Item bucketCrudeOil;

   public static void init() {
      bucketCrudeOil = new BucketCrudeOil();
      Registrator.registerItem(bucketCrudeOil);
      FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack(References.UnlocalizedNames.FLUIDCRUDEOIL, FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(OilItems.bucketCrudeOil), new ItemStack(Items.bucket));

   }
}
