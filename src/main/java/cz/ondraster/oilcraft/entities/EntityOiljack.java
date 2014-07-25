package cz.ondraster.oilcraft.entities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class EntityOiljack extends TileEntity {

   public int renderOffset = 0;
   private int lastUpdate = 0;
   private ForgeDirection orientation = ForgeDirection.NORTH;

   public EntityOiljack() {

   }

   @Override
   public void readFromNBT(NBTTagCompound tag) {
      super.readFromNBT(tag);
      orientation = ForgeDirection.getOrientation(tag.getInteger("orientation"));
   }

   @Override
   public void writeToNBT(NBTTagCompound tag) {
      super.writeToNBT(tag);
      tag.setInteger("orientation", orientation.ordinal());
   }

   @Override
   public void updateEntity() {
      if (lastUpdate >= 40) {
         lastUpdate = 0;
         if (worldObj.getTileEntity(xCoord + orientation.offsetX, yCoord, zCoord + orientation.offsetZ) instanceof EntityOiljackPipe) {
            ((EntityOiljackPipe) worldObj.getTileEntity(xCoord + orientation.offsetX, yCoord, zCoord + orientation.offsetZ)).digOil();
         }
      }

      lastUpdate++;
   }

   public boolean canWork() {
      // check for neighbour blocks. Should run client side only to help with server perfomance!
      if (worldObj.getTileEntity(xCoord + orientation.offsetX, yCoord, zCoord + orientation.offsetZ) instanceof EntityOiljackPipe)
         return true;

      return false;
   }

   public ForgeDirection getOrientation() {
      return this.orientation;
   }

   public void setOrientation(ForgeDirection direction) {
      this.orientation = direction;
   }

   @Override
   public Packet getDescriptionPacket() {
      NBTTagCompound nbtTagCompound = new NBTTagCompound();
      this.writeToNBT(nbtTagCompound);
      return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, nbtTagCompound);
   }

   @Override
   public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
      readFromNBT(pkt.func_148857_g());
   }
}
