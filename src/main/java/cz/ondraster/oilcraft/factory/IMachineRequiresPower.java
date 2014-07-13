package cz.ondraster.oilcraft.factory;

public interface IMachineRequiresPower {
   public boolean addPower(int fuelValue);

   public int getPower();
}
