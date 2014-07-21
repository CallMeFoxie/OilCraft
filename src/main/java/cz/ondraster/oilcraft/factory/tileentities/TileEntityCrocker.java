package cz.ondraster.oilcraft.factory.tileentities;

public class TileEntityCrocker extends TileEntityController {
   @Override
   public void checkMultiblock() {

   }

   @Override
   public void resetMultiblock() {

   }

   @Override
   public boolean canWork() {
      return false;
   }

   @Override
   public void beforeWork() {

   }

   @Override
   public void afterWork(boolean success) {

   }

   @Override
   protected TileEntityValve[] findInputValves() {
      return new TileEntityValve[0];
   }

   @Override
   protected TileEntityValve[] findOutputValves() {
      return new TileEntityValve[0];
   }

   @Override
   protected TileEntityHatch[] findOutputHatches() {
      return new TileEntityHatch[0];
   }
}
