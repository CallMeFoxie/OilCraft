package cz.ondraster.oilcraft.tileentities;

import cz.ondraster.oilcraft.fluids.FluidTank;
import cz.ondraster.oilcraft.worldgen.Config;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityGeneratorGas extends TileEntityGeneratorRF {

   FluidTank tank;

   public TileEntityGeneratorGas() {
      tank = new FluidTank(Config.GeneratorRF.gasTankCapacity);
   }

   @Override
   public void writeToNBT(NBTTagCompound tag) {
      super.writeToNBT(tag);
      tank.save(tag);
   }

   @Override
   public void readFromNBT(NBTTagCompound tag) {
      super.readFromNBT(tag);
      tank.load(tag);
   }

   @Override
   public void updateEntity() {
      super.updateEntity();

      if (tank.getFluidAmount() > 0 && RFStorage.receiveEnergy(Config.GeneratorRF.generatePerMBofGas, true) == Config.GeneratorRF.generatePerMBofGas) {
         tank.drain(1, true);
         RFStorage.receiveEnergy(Config.GeneratorRF.generatePerMBofGas, true);
      }
   }
}
