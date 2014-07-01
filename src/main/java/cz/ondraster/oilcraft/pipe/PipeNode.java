package cz.ondraster.oilcraft.pipe;

public class PipeNode {
   public int x;
   public int y;
   public int z;
   public int capacity;
   public int used;

   public PipeNode(int x, int y, int z, int capacity, int used) {
      this.x = x;
      this.y = y;
      this.z = z;
      this.capacity = capacity;
      this.used = used;
   }
}