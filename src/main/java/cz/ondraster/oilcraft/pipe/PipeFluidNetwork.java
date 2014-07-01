package cz.ondraster.oilcraft.pipe;

import cz.ondraster.oilcraft.FluidTank;
import cz.ondraster.oilcraft.Helper;
import cz.ondraster.oilcraft.entities.EntityPipe;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.List;

public class PipeFluidNetwork extends FluidTank {
   private List<PipeNode> nodes;

   public PipeFluidNetwork() {
      super(0);
      nodes = new ArrayList<PipeNode>();
   }

   public boolean addNode(PipeNode node, World world) {
      TileEntity tileEntity = world.getTileEntity(node.x, node.y, node.z);
      if (!(tileEntity instanceof EntityPipe)) {
         Helper.logWarn("Tried adding non-FluidTank into PipeFluidNetwork");
         return false;
      }

      EntityPipe fluidTank = (EntityPipe) tileEntity;

      if (storedFluid == null) {
         if (fluidTank.getTankInfo(ForgeDirection.UNKNOWN)[0].fluid != null)
            storedFluid = fluidTank.getTankInfo(ForgeDirection.UNKNOWN)[0].fluid.getFluid();

         capacity += node.capacity;
         storedAmount += node.used;
         this.nodes.add(node);
         rebalance(world);
         return true;
      } else if (fluidTank.getTankInfo(ForgeDirection.UNKNOWN)[0].fluid == null) {
         capacity += node.capacity;
         rebalance(world);
         return true;
      } else if (fluidTank.getTankInfo(ForgeDirection.UNKNOWN)[0].fluid.getFluid() == storedFluid) {
         capacity += node.capacity;
         storedAmount += node.used;
         rebalance(world);
         return true;
      }

      return false;
   }

   public void removeNode(PipeNode node, World world) {
      // rebuild the map until we get a better algorithm...
      if (nodes.size() == 1) { // we were the only one there.
         nodes.clear();
         return;
      } else {
         // first delete the one we want to get rid of
         for (int i = 0; i < nodes.size(); i++) {
            PipeNode tmp = nodes.get(i);
            if (tmp.x == node.x && tmp.y == node.y && tmp.z == node.z) {
               nodes.remove(i);
               EntityPipe f = (EntityPipe) world.getTileEntity(tmp.x, tmp.y, tmp.z);
               EntityPipe test = (EntityPipe) world.getTileEntity(nodes.get(0).x, nodes.get(0).y, nodes.get(0).z);
               if (f.getNetworkDr() == test.getNetworkDr()) // he was our DR...
               {
                  test.setDr(test.xCoord, test.yCoord, test.zCoord);
                  test.becomeDr(this);
               }
            }
         }
         // new node = nodes[0]
         PipeNode newdr = nodes.get(0);
      }
   }

   public void rebalance(World world) {
      for (PipeNode node : nodes) {
         EntityPipe pipe = (EntityPipe) world.getTileEntity(node.x, node.y, node.z);
         pipe.setFluidParams(storedFluid, (int) (((double) storedAmount / capacity) * storedAmount));
      }
   }
}
