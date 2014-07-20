package cz.ondraster.oilcraft.factory.tileentities;

import cz.ondraster.oilcraft.Helper;
import cz.ondraster.oilcraft.fluids.FluidTank;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.*;


public class TileEntityValve extends TileEntityPartWithInventory implements IFluidHandler {
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
   public void load(NBTTagCompound tag) {
      super.load(tag);
      tank.load(tag);
   }

   @Override
   public void save(NBTTagCompound tag) {
      super.save(tag);
      tank.save(tag);
   }

   @Override
   public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
      //if (direction == TankDirection.FILL)
      if (doFill)
         worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);

      markDirty();

      return tank.fill(resource, doFill);

      //return 0;
   }

   @Override
   public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
      //if (direction == TankDirection.DRAIN)
      if (doDrain)
         worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);

      markDirty();

      return tank.drain(resource.amount, doDrain);

      // return null;
   }

   @Override
   public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
      //if (direction == TankDirection.DRAIN)
      if (doDrain)
         worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);

      markDirty();
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
      if (worldObj == null)
         return;

      if (worldObj.isRemote)
         return;

      if (slot == 0 && getStackInSlot(0) != null) { // input
         ItemStack inputSlot = getStackInSlot(0);
         ItemStack outputSlot = getStackInSlot(1);
         if (FluidContainerRegistry.isFilledContainer(inputSlot)) {
            ItemStack empty = Helper.getEmptyContainer(inputSlot);
            empty.stackSize = 1;
            boolean allow = false;

            // check for all the possible output scenarios
            if (outputSlot == null)
               allow = true;
            else if (empty == null)
               allow = true;
            else if (Helper.canMergeStacks(outputSlot, empty))
               allow = true;

            if (allow) {

               FluidStack fluidStack = FluidContainerRegistry.getFluidForFilledItem(inputSlot);
               if (tank.fill(fluidStack, false) == fluidStack.amount)
                  tank.fill(fluidStack, true);
               else
                  return;


               decrStackSize(0, 1, false);

               if (outputSlot == null)
                  setInventorySlotContents(1, empty);
               else if (empty != null) {
                  ++outputSlot.stackSize;
                  setInventorySlotContents(1, outputSlot);

               }
            }
         }
      }

      this.markDirty();
      if (worldObj != null)
         worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);


   }

   public enum TankDirection {
      FILL,
      DRAIN
   }
}
