package cz.ondraster.oilcraft.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cz.ondraster.oilcraft.References;

/**
 * Created by Ondra on 29.6.2014.
 */
public class Network {
   public static Network instance;
   public static SimpleNetworkWrapper networkChannel;

   public Network() {
      instance = this;
      networkChannel = NetworkRegistry.INSTANCE.newSimpleChannel(References.MODID);
      init();
   }

   public void init() {

   }
}
