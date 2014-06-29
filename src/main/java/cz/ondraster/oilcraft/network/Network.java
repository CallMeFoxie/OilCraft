package cz.ondraster.oilcraft.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import cz.ondraster.oilcraft.References;
import cz.ondraster.oilcraft.network.messages.MessagePipeUpdate;

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
      networkChannel.registerMessage(MessagePipeUpdate.class, MessagePipeUpdate.class, 0, Side.CLIENT);
   }
}
