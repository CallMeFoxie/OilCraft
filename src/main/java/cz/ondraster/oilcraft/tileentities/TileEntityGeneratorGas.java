package cz.ondraster.oilcraft.tileentities;

import cz.ondraster.oilcraft.fluids.FluidTank;
import cz.ondraster.oilcraft.worldgen.Config;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityGeneratorGas extends TileEntityGeneratorRF {

   public int rotationOffset = 0; // clientside only stuff, for rendering of the animation
   FluidTank tank;
   private ForgeDirection orientation;

   public TileEntityGeneratorGas() {
      tank = new FluidTank(Config.GeneratorRF.gasTankCapacity);
   }

   @Override
   public void writeToNBT(NBTTagCompound tag) {
      super.writeToNBT(tag);
      tank.save(tag);
      tag.setInteger("orientation", orientation.ordinal());
   }

   @Override
   public void readFromNBT(NBTTagCompound tag) {
      super.readFromNBT(tag);
      tank.load(tag);
      orientation = ForgeDirection.getOrientation(tag.getInteger("orientation"));
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

   @Override
   public void updateEntity() {
      super.updateEntity();

      if (tank.getFluidAmount() > 0 && RFStorage.receiveEnergy(Config.GeneratorRF.generatePerMBofGas, true) == Config.GeneratorRF.generatePerMBofGas) {
         tank.drain(1, true);
         RFStorage.receiveEnergy(Config.GeneratorRF.generatePerMBofGas, true);
      }
   }

   public ForgeDirection getOrientation() {
      return orientation;
   }

   public void setOrientation(ForgeDirection orientation) {
      this.orientation = orientation;
   }
}
