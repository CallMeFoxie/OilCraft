package cz.ondraster.oilcraft.network.messages;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cz.ondraster.oilcraft.entities.EntityPipe;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by Ondra on 29.6.2014.
 */
public class MessagePipeUpdate implements IMessage, IMessageHandler<MessagePipeUpdate, IMessage> {

   int x, y, z;
   int encodedSides;
   EntityPipe pipe;

   public MessagePipeUpdate() {

   }

   public MessagePipeUpdate(EntityPipe pipe) {
      this.pipe = pipe;
   }

   @Override
   public void fromBytes(ByteBuf buf) {
      x = buf.readInt();
      y = buf.readInt();
      z = buf.readInt();
      encodedSides = buf.readInt();
   }

   @Override
   public void toBytes(ByteBuf buf) {
      buf.writeInt(pipe.xCoord);
      buf.writeInt(pipe.yCoord);
      buf.writeInt(pipe.zCoord);
      buf.writeInt(pipe.encodeSides());
   }

   @Override
   public IMessage onMessage(MessagePipeUpdate message, MessageContext ctx) {

      TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);

      if (tileEntity != null && tileEntity instanceof EntityPipe) {
         EntityPipe entityPipe = (EntityPipe) tileEntity;
         entityPipe.decodeSides(message.encodedSides);
         //System.out.println("Received new encoded sides: " + message.encodedSides);
      }

      return null;
   }
}
