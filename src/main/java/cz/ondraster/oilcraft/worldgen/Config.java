package cz.ondraster.oilcraft.worldgen;

public class Config {
   public static int powerPerEu = 100;
   public static int powerPerRF = 4;

   public static int oiljackPowerCapacity = 1000;

   public static int oiljackMaxTransferEu = 32;
   public static int oiljackMaxTransferRF = oiljackMaxTransferEu * (powerPerEu / powerPerRF);
   public static int powerPerAction = 200;
}
