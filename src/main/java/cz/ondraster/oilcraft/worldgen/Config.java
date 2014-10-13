package cz.ondraster.oilcraft.worldgen;

public class Config {
   public static int powerPerEu = 100;
   public static int powerPerRF = 4;

   public static int oiljackPowerCapacity = 1000;

   public static int oiljackMaxTransferEu = 32;
   public static int oiljackMaxTransferRF = oiljackMaxTransferEu * (powerPerEu / powerPerRF);
   public static int powerPerAction = 2000;

   public class GeneratorRF {
      public static final int maxStorage = 20000;
      public static final int maxTransfer = 10000;
      public static final int maxExtract = 300;

      public static final int generatePerMBofGas = 20;
      public static final int gasTankCapacity = 4000;
   }
}
