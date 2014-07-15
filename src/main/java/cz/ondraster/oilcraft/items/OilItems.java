package cz.ondraster.oilcraft.items;

import cz.ondraster.oilcraft.References;
import cz.ondraster.oilcraft.Registrator;
import cz.ondraster.oilcraft.fluids.Fluids;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;

public class OilItems {
   public static Item bucketCrudeOil;
   public static Item bucketHydrogen;

   public static Item debugTool;
   public static Item itemMatches;
   public static Item wrench;
   public static Item dustParaffin;

   public static void init() {
      bucketCrudeOil = new OilCraftBucket(Fluids.blockFluidCrudeOil).setUnlocalizedName(References.UnlocalizedNames.BUCKETCRUDEOIL).setTextureName(References.Textures.BUCKETCRUDEOIL);
      Registrator.registerItem(bucketCrudeOil);
      FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack(References.UnlocalizedNames.FLUIDCRUDEOIL, FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(OilItems.bucketCrudeOil), new ItemStack(Items.bucket));
      Fluids.blockFluidCrudeOil.setBucket((OilCraftBucket) bucketCrudeOil);

      /*bucketHydrogen = new OilCraftBucket(Fluids.blockFluidHydrogen).setUnlocalizedName(References.UnlocalizedNames.BUCKETHYDROVEN).setTextureName(References.Textures.BUCKETHYDROGEN);
      Registrator.registerItem(bucketHydrogen);
      FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack(References.UnlocalizedNames.FLUIDHYDROGEN, FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(OilItems.bucketHydrogen), new ItemStack(Items.bucket));
      Fluids.blockFluidHydrogen.setBucket((OilCraftBucket) bucketHydrogen);    */

      debugTool = new DebugTool();
      Registrator.registerItem(debugTool);

      itemMatches = new ItemMatchBox();
      Registrator.registerItem(itemMatches);

      wrench = new OilWrench();
      Registrator.registerItem(wrench);

      dustParaffin = new Item();
      dustParaffin.setUnlocalizedName(References.UnlocalizedNames.DUSTPARAFFIN);
      dustParaffin.setTextureName(References.Icons.DUSTPARAFFIN);
      Registrator.registerItem(dustParaffin);
   }
}
