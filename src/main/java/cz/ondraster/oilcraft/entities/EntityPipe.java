package cz.ondraster.oilcraft.entities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by Ondra on 29.6.2014.
 */
public class EntityPipe extends TileEntity {
   public boolean connectXm = false;
   public boolean connectXp = false;
   public boolean connectYm = false;
   public boolean connectYp = false;
   public boolean connectZm = false;
   public boolean connectZp = false;

   public EntityPipe() {

   }

   @Override
   public void readFromNBT(NBTTagCompound tag) {
      super.readFromNBT(tag);
      connectXm = tag.getBoolean("connectXm");
      connectXp = tag.getBoolean("connectXp");
      connectYm = tag.getBoolean("connectYm");
      connectYp = tag.getBoolean("connectYp");
      connectZm = tag.getBoolean("connectZm");
      connectZp = tag.getBoolean("connectZp");

      //Network.networkChannel.sendToDimension(new MessagePipeUpdate(this), this.worldObj.provider.dimensionId);
   }

   @Override
   public void writeToNBT(NBTTagCompound tag) {
      super.writeToNBT(tag);
      tag.setBoolean("connectXm", connectXm);
      tag.setBoolean("connectXp", connectXp);
      tag.setBoolean("connectYm", connectYm);
      tag.setBoolean("connectYp", connectYp);
      tag.setBoolean("connectZm", connectZm);
      tag.setBoolean("connectZp", connectZp);
   }

   @Override
   public void updateEntity() {
      // empty for now
   }

   public void changeState(ForgeDirection side, boolean nState) {
      //connectYp = nState;
      switch (side) {
         case DOWN:
            connectYm = nState;
            break;
         case UP:
            connectYp = nState;
            break;
         case NORTH:
            connectZm = nState;
            break;
         case SOUTH:
            connectZp = nState;
            break;
         case WEST:
            connectXm = nState;
            break;
         case EAST:
            connectXp = nState;
            break;
         case UNKNOWN:
            break;
      }
   }

   public void changeStateNegative(ForgeDirection side, boolean nState) {
      switch (side) {
         case UP:
            connectYm = nState;
            break;
         case DOWN:
            connectYp = nState;
            break;
         case SOUTH:
            connectZm = nState;
            break;
         case NORTH:
            connectZp = nState;
            break;
         case EAST:
            connectXm = nState;
            break;
         case WEST:
            connectXp = nState;
            break;
         case UNKNOWN:
            break;
      }
   }

   public boolean isConnected(ForgeDirection side) {
      switch (side) {
         case DOWN:
            return connectYm;
         case UP:
            return connectYp;
         case NORTH:
            return connectZm;
         case SOUTH:
            return connectZp;
         case WEST:
            return connectXm;
         case EAST:
            return connectXp;
         case UNKNOWN:
            break;
      }

      return false;
   }


   public int encodeSides() {
      int retval = 0;
      if (connectYm)
         retval |= 1 << 0;
      if (connectYp)
         retval |= 1 << 1;
      if (connectZm)
         retval |= 1 << 2;
      if (connectZp)
         retval |= 1 << 3;
      if (connectXm)
         retval |= 1 << 4;
      if (connectXp)
         retval |= 1 << 5;

      return retval;
   }

   public void decodeSides(int val) {
      connectYm = connectYp = connectXp = connectXm = connectZm = connectZp = false;
      if ((val & (1 << 0)) != 0)
         connectYm = true;
      if ((val & (1 << 1)) != 0)
         connectYp = true;
      if ((val & (1 << 2)) != 0)
         connectZm = true;
      if ((val & (1 << 3)) != 0)
         connectZp = true;
      if ((val & (1 << 4)) != 0)
         connectXm = true;
      if ((val & (1 << 5)) != 0)
         connectXp = true;
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
