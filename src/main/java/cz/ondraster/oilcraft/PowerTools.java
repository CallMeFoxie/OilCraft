package cz.ondraster.oilcraft;

import cz.ondraster.oilcraft.worldgen.Config;

public class PowerTools {
   public static int convertEUtoRF(double EU) {
      return (int) ((Config.powerPerEu / Config.powerPerRF) * EU);
   }

   public static double convertRFtoEU(int RF) {
      return (Config.powerPerRF / (double) Config.powerPerEu) * RF;
   }
}
