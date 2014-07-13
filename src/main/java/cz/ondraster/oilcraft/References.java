package cz.ondraster.oilcraft;

public class References {
   public static final String MODID = "oilcraft";
   public static final String VERSION = "@VERSION@";
   public static final String MODNAME = "OilCraft";

   public class GUIs {
      public static final int guiValve = 1;
   }

   public class UnlocalizedNames {
      public static final String BLOCKOILJACK = MODID + "_oiljack";
      public static final String BLOCKPIPE = MODID + "_pipe";
      public static final String FLUIDCRUDEOIL = MODID + "_crude_oil";
      public static final String BLOCKOILJACKPIPE = MODID + "_oiljack_pipe";
      public static final String BUCKETCRUDEOIL = MODID + "_bucket_crudeoil";
      public static final String ITEMDEBUGTOOL = MODID + "_debugtool";
      public static final String BLOCKMACHINECASING = MODID + "_block_machine_casing";
      public static final String BLOCKMACHINECASINGHT = MODID + "_block_machine_casinght";
      public static final String BLOCKELECTRICFIREBOX = MODID + "_block_machine_electric_firebox";
      public static final String BLOCKWINDOW = MODID + "_block_machine_window";
      public static final String BLOCKMETER = MODID + "_block_machine_meter";
      public static final String BLOCKSOLIDFIREBOX = MODID + "_block_machine_solid_firebox";
      public static final String TABNAME = MODID + "_tabname";
      public static final String BLOCKVALVE = MODID + "_block_machine_valve";
      public static final String BLOCKVALVEHT = MODID + "_block_machine_valveht";
      public static final String FLUIDHYDROGEN = MODID + "_hydrogen";
      public static final String BUCKETHYDROVEN = MODID + "_bucket_hydrogen";
      public static final String ITEMMATCHES = MODID + "_matches";
      public static final String ITEMWRENCH = MODID + "_wrench";
      public static final String CONTROLLERHEATER = MODID + "_controller_heater";
      public static final String ICONITEM = MODID + "_icon";
      public static final String CONTROLLERMEROXTREATER = MODID + "_controller_merox_treater";
      public static final String FLUIDHEATEDOIL = MODID + "_heated_oil";
   }

   public class Textures {
      public static final String BLOCKOILJACK3D = "oiljack.png";
      public static final String BLOCKPIPE3D = "pipe_front.png";
      public static final String BLOCKPIPEMIDDLE3D = "pipe_middle.png";
      public static final String BUCKETCRUDEOIL = MODID + ":bucket_crudeoil";
      public static final String BLOCKOILJACKPIPE = MODID + ":oiljack_pipe";
      public static final String FLUIDCRUDEOILFLOWING = MODID + ":fluid_crudeoil_flowing";
      public static final String FLUIDCRUDEOILSTILL = MODID + ":fluid_crudeoil_still";
      public static final String ITEMDEBUGTOOL = MODID + ":icon_debugger";
      public static final String BLOCKMACHINECASING = MODID + ":machine_casing";
      public static final String BLOCKMACHINECASINGHT = MODID + ":machine_casinght";
      public static final String BLOCKELECTRICFIREBOX = MODID + ":machine_electric_firebox";
      public static final String BLOCKWINDOW = MODID + ":machine_window";
      public static final String BLOCKMETER = MODID + ":machine_meter";
      public static final String BLOCKSOLIDFIREBOX = MODID + ":machine_solid_firebox";
      public static final String BLOCKVALVE = MODID + ":machine_valve";
      public static final String BLOCKVALVEHT = MODID + ":machine_valveht";
      public static final String FLUIDHYDROGENFLOWING = MODID + ":fluid_hydrogen_flowing";
      public static final String FLUIDHYDROGENSTILL = MODID + ":fluid_hydrogen_still";
      public static final String BUCKETHYDROGEN = MODID + ":bucket_hydrogen";
      public static final String ITEMMATCHES = MODID + ":matches";
      public static final String CONTROLLERHEATER = MODID + ":controller_heater";
      public static final String VALVEOUTLET = MODID + ":machine_valve_out";
      public static final String VALVEOUTLETHT = MODID + ":machine_valve_out_ht";
      public static final String SOLIDTOP = MODID + ":machine_top_ht";
      public static final String CONTROLLERMEROXTREATER = MODID + ":controller_merox_treater";
   }

   public class Icons {
      public static final String TABICON = MODID + ":tab_oilcraft";
      public static final String ICONOILJACK = MODID + ":icon_oiljack";
      public static final String ICONPIPE = MODID + ":icon_pipe";
      public static final String ITEMWRENCH = MODID + ":wrench";
   }

   public class Entities {
      public static final String ENTITYOILJACK = MODID + "_oiljack_te";
      public static final String ENTITYOILJACKPIPE = MODID + "_oiljack_pipe_te";
      public static final String ENTITYPIPE = MODID + "_pipe_te";
      public static final String ENTITYVALVE = MODID + "_valve_te";
      public static final String ENTITYVALVEHT = MODID + "_valve_ht_te";
      public static final String ENTITYBASE = MODID + "_base_te";
      public static final String ENTITYHEATER = MODID + "_heater_te";
      public static final String ENTITYMEROXTREATER = MODID + "_meroxtreater_te";
      public static final String ENTITYGASPROC = MODID + "_gasproc_te";
      public static final String ENTITYHYDROTREATER = MODID + "_hydrotreater_te";
      public static final String ENTITYCROCKER = MODID + "_crocker_te";
      public static final String ENTITYCOKER = MODID + "_coker_te";
      public static final String ENTITYASPHALT = MODID + "_asphalt_te";
      public static final String ENTITYDISTILLATOR = MODID + "_distillator_te";
      public static final String FIREBOXSOLID = MODID + "_firebox_solid";
      public static final String FIREBOXELECTRIC = MODID + "_firebox_electric";
   }

   public class FactoryControllers {
      public static final int MEROXTREATER = 0;
      public static final int GASPROC = 1;
      public static final int HYDROTREATER = 2;
      public static final int CROCKER = 3;
      public static final int COKER = 4;
      public static final int ASPHALT = 5;
      public static final int DISTILLATOR = 6;
   }
}
