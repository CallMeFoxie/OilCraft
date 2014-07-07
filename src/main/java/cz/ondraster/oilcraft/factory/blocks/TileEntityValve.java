package cz.ondraster.oilcraft.factory.blocks;

import cz.ondraster.oilcraft.Helper;
import cz.ondraster.oilcraft.TileEntityWithInventory;
import cz.ondraster.oilcraft.fluids.FluidTank;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.*;


public class TileEntityValve extends TileEntityWithInventory implements IFluidHandler {
   private FluidTank tank;
   private TankDirection direction;


   public TileEntityValve() {
      super(2);
      tank = new FluidTank(2000);
   }

   public void setDirection(TankDirection direction) {
      this.direction = direction;
   }

   @Override
   public void readFromNBT(NBTTagCompound tag) {
      super.readFromNBT(tag);
      tank.load(tag);
   }

   @Override
   public void writeToNBT(NBTTagCompound tag) {
      super.writeToNBT(tag);
      tank.save(tag);
   }

   @Override
   public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
      //if (direction == TankDirection.FILL)
      if (doFill)
         worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);

      return tank.fill(resource, doFill);

      //return 0;
   }

   @Override
   public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
      //if (direction == TankDirection.DRAIN)
      if (doDrain)
         worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
      return tank.drain(resource.amount, doDrain);

      // return null;
   }

   @Override
   public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
      //if (direction == TankDirection.DRAIN)
      if (doDrain)
         worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
      return tank.drain(maxDrain, doDrain);

      //return null;
   }

   @Override
   public boolean canFill(ForgeDirection from, Fluid fluid) {
      //if (direction == TankDirection.FILL)
      if (tank.getFluid() == null || tank.getFluid().getFluid() == null)
         return true;
      else
         return tank.getFluid().getFluid() == fluid;

      //return false;
   }

   @Override
   public boolean canDrain(ForgeDirection from, Fluid fluid) {
      //if (direction == TankDirection.DRAIN)
      return tank.getFluidAmount() > 0 && tank.getFluid().getFluid() == fluid;

      //return false;
   }

   @Override
   public FluidTankInfo[] getTankInfo(ForgeDirection from) {
      return new FluidTankInfo[]{tank.getInfo()};
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
   protected void onInventoryChanged(int slot) {
      if (worldObj.isRemote)
         return;

      if (slot == 0 && getStackInSlot(0) != null) { // input
         ItemStack itemStack = getStackInSlot(0);
         if (FluidContainerRegistry.isFilledContainer(itemStack)) {
            FluidStack content = FluidContainerRegistry.getFluidForFilledItem(itemStack);
            if (tank.getFluid() == null || (content.getFluid() == tank.getFluid().getFluid() && tank.getFluidAmount() + content.amount <= tank.getCapacity() && Helper.canMergeStacks(itemStack, getStackInSlot(1)))) {

               tank.fill(content, true);

               ItemStack output = getStackInSlot(1);
               itemStack.stackSize--;

               if (output == null) {
                  output = Helper.getEmptyContainer(itemStack);
               }

               if (itemStack.stackSize == 0)
                  itemStack = null;
               setInventorySlotContents(0, itemStack);

               if (output != null)
                  output.stackSize += 1;

               setInventorySlotContents(1, output);


            }
         }
      }

      this.markDirty();
      worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
   }

   public enum TankDirection {
      FILL,
      DRAIN
   }
}
