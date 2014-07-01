package cz.ondraster.oilcraft;

import cpw.mods.fml.common.FMLLog;

/**
 * Created by Ondra on 1.7.2014.
 */
public class Helper {
   public static void logWarn(String message) {
      FMLLog.warning("[" + References.MODNAME + "] " + message);
   }
}
