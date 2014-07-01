package cz.ondraster.oilcraft;

public class OrientationSimple {
   public static int Down = 0;
   public static int Up = 1;
   public static int North = 2;
   public static int South = 3;
   public static int West = 4;
   public static int East = 5;
   public static int Directions = 6;

   public static int getOpposite(int side) {
      if (side % 2 == 1)
         return side - 1;
      else
         return side + 1;
   }

   public static int getX(int side) {
      if (side == West)
         return -1;
      if (side == East)
         return +1;

      return 0;
   }

   public static int getY(int side) {
      if (side == Up)
         return +1;
      if (side == Down)
         return -1;

      return 0;
   }

   public static int getZ(int side) {
      if (side == South)
         return +1;
      if (side == North)
         return -1;

      return 0;
   }
}
