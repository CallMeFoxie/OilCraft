package cz.ondraster.oilcraft;
import net.minecraft.block.Block;


/**
 * Created by Ondra on 21.6.2014.
 */
public class OilBlocks {
   public static Block Beam;

   public static void init()
   {
      Beam = new BlockBeam();
   }
}
