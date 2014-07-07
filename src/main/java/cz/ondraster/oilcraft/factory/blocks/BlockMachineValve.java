package cz.ondraster.oilcraft.factory.blocks;

import cz.ondraster.oilcraft.OilCraft;
import cz.ondraster.oilcraft.References;
import cz.ondraster.oilcraft.Registrator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockMachineValve extends MultiblockPartEntity {
   public BlockMachineValve() {
      super(RotationsAllowed.SIDEONLY);
      Registrator.registerTileEntity(TileEntityValve.class, References.Entities.ENTITYVALVE);
   }

   protected BlockMachineValve(boolean noRegister) {
      super(RotationsAllowed.SIDEONLY);
   }

   @Override
   public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
      return new TileEntityValve();
   }

   @Override
   public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
                                   float hitY, float hitZ) {
      if (!player.isSneaking()) {
         player.openGui(OilCraft.instance, References.GUIs.guiValve, world, x, y, z);
         return true;
      }

      return false;
   }
}
