package cz.ondraster.oilcraft.tileentities;

import cz.ondraster.oilcraft.Helper;
import cz.ondraster.oilcraft.worldgen.Config;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;


public class TileEntityOiljack extends TileEntity {

   public int renderOffset = 0;
   private int lastUpdate = 0;
   private ForgeDirection orientation = ForgeDirection.NORTH;

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
      if (worldObj.isRemote)
         return;

      if (lastUpdate >= 40 && canWork() && hasPower()) {
         lastUpdate = 0;
         if (worldObj.getTileEntity(xCoord + orientation.offsetX, yCoord, zCoord + orientation.offsetZ) instanceof TileEntityOiljackPipe) {
            ((TileEntityOiljackPipe) worldObj.getTileEntity(xCoord + orientation.offsetX, yCoord, zCoord + orientation.offsetZ)).digOil();
            TileEntityGeneratorPower generator = (TileEntityGeneratorPower) getGenerator();
            generator.extractEnergy(Config.powerPerAction);
         }
      }

      lastUpdate++;
   }

   public boolean hasPower() {
      TileEntity generator = getGenerator();
      if (!(generator instanceof TileEntityGeneratorPower)) {
         Helper.logWarn("TileEntity Generator NOT found where it should have been! This error should NOT happen!");
         return false;
      }

      int power = ((TileEntityGeneratorPower) generator).getStoredEnergy();
      if (power < Config.powerPerAction)
         return false;

      return true;
   }

   public boolean canWork() {
      boolean canWork = true;
      // check for neighbour blocks. Should run client side only to help with server perfomance!
      if (!(worldObj.getTileEntity(xCoord + orientation.offsetX, yCoord, zCoord + orientation.offsetZ) instanceof TileEntityOiljackPipe))
         canWork = false;

      return canWork;
   }

   private TileEntity getGenerator() {
      if (getOrientation().offsetX != 0)
         return worldObj.getTileEntity(xCoord + getOrientation().getOpposite().offsetX * 2, yCoord + 0, zCoord);
      else
         return worldObj.getTileEntity(xCoord, yCoord, zCoord + getOrientation().getOpposite().offsetZ * 2);
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
