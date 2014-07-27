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
   int ticksSinceLastUpdate = 0;
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
      if (isExport())
         return 0;
      if (doFill)
         worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);

      markDirty();

      return tank.fill(resource, doFill);
   }

   public int machineFill(FluidStack resource, boolean doFill) {
      if (doFill)
         worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);

      markDirty();

      return tank.fill(resource, doFill);
   }

   @Override
   public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
      if (!isExport())
         return null;
      if (doDrain)
         worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);

      markDirty();

      return tank.drain(resource.amount, doDrain);
   }

   public FluidStack machineDrain(FluidStack resource, boolean doDrain) {
      if (doDrain)
         worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);

      markDirty();

      return tank.drain(resource.amount, doDrain);
   }

   public FluidStack machineDrain(int maxDrain, boolean doDrain) {
      if (doDrain)
         worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);

      markDirty();
      return tank.drain(maxDrain, doDrain);
   }

   @Override
   public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
      if (!isExport())
         return null;
      if (doDrain)
         worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);

      markDirty();
      return tank.drain(maxDrain, doDrain);
   }

   public boolean machineCanFill(Fluid fluid) {
      if (tank.getFluid() == null || tank.getFluid().getFluid() == null)
         return true;
      else
         return tank.getFluid().getFluid() == fluid;
   }

   @Override
   public boolean canFill(ForgeDirection from, Fluid fluid) {
      if (isExport())
         return false;

      if (tank.getFluid() == null || tank.getFluid().getFluid() == null)
         return true;
      else
         return tank.getFluid().getFluid() == fluid;
   }

   public boolean machineCanDrain(Fluid fluid) {
      return tank.getFluidAmount() > 0 && tank.getFluid().getFluid() == fluid;
   }

   @Override
   public boolean canDrain(ForgeDirection from, Fluid fluid) {
      if (!isExport())
         return false;

      return tank.getFluidAmount() > 0 && tank.getFluid().getFluid() == fluid;
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

   protected void trySlot() {
      if (worldObj == null)
         return;

      if (worldObj.isRemote)
         return;

      if (getStackInSlot(0) != null) { // input
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


               decrStackSize(0, 1);

               if (outputSlot == null)
                  setInventorySlotContents(1, empty);
               else if (empty != null) {
                  ++outputSlot.stackSize;
                  setInventorySlotContents(1, outputSlot);

               }
            }
         } else if (FluidContainerRegistry.isEmptyContainer(inputSlot)) {
            outputSlot = FluidContainerRegistry.fillFluidContainer(this.tank.getFluid(), inputSlot);
            if (outputSlot != null && Helper.canMergeStacks(outputSlot, getStackInSlot(1))) {
               decrStackSize(0, 1);
               setInventorySlotContents(1, Helper.mergeStacks(getStackInSlot(1), outputSlot));
               this.tank.drain(FluidContainerRegistry.getFluidForFilledItem(outputSlot).amount, true);
            }
         }
      }

      this.markDirty();
      if (worldObj != null)
         worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
   }

   @Override
   public void updateEntity() {
      super.updateEntity();

      if (ticksSinceLastUpdate >= 40) {
         trySlot();
         ticksSinceLastUpdate = 0;
      }

      ticksSinceLastUpdate++;
   }

   public boolean isExport() {
      int meta = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
      return (meta & 0x4) != 0;
   }

   public enum TankDirection {
      FILL,
      DRAIN
   }
}
