package cz.ondraster.oilcraft.compatibility;

public abstract class CompatibilityBase {
   private static CompatibilityBase compatBC;
   private static CompatibilityBase compatIC;
   private static CompatibilityBase compatRF;

   public static void init() {
      compatBC = new CompatibilityBuildcraft();
      compatBC.addRecipes();

      compatIC = new CompatibilityIC();
      compatIC.addRecipes();
   }

   // has to be overriden, but with @Optional.Method it will be stripped and run this empty implementation instead :]
   public void addRecipes() {

   }
}
