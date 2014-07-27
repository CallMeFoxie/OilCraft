package cz.ondraster.oilcraft.factory;

public interface IMachineRequiresHeat {
   public boolean increaseTemperature(int step, boolean doIt);

   public void decreaseTemperature(int step);

   public int getTemperature();
}
