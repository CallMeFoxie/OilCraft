package cz.ondraster.oilcraft.compatibility;

import cpw.mods.fml.common.Optional;

public class CompatibilityIC extends CompatibilityBase {
   @Override
   @Optional.Method(modid = "IC2API")
   public void addRecipes() {
      // TODO - recipe FireboxEU
   }
}
