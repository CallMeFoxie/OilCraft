package cz.ondraster.oilcraft.entities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by Ondra on 29.6.2014.
 */
public class EntityPipe extends TileEntity {
   public int connections = 0;

   public EntityPipe() {

   }

   @Override
   public void readFromNBT(NBTTagCompound tag) {
      super.readFromNBT(tag);
      connections = tag.getInteger("connections");
   }

   @Override
   public void writeToNBT(NBTTagCompound tag) {
      super.writeToNBT(tag);
      tag.setInteger("connections", connections);
   }

   @Override
   public void updateEntity() {
      // empty for now
   }


   public void changeState(int side, boolean nState) {
      connections &= ~(1 << side);
      if (nState)
         connections |= (1 << side);
   }

   public boolean isConnected(int side) {
      return (connections & (1 << side)) != 0;
   }

   @Override
   public Packet getDescriptionPacket() {
      NBTTagCompound nbtTagCompound = new NBTTagCompound();
      this.writeToNBT(nbtTagCompound);
      System.out.println("getDescriptionPacket..." + nbtTagCompound.getBoolean("connectYp"));
      return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, nbtTagCompound);
   }

   @Override
   public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
      System.out.println("onDataPacket");
      readFromNBT(pkt.func_148857_g());
   }


}
