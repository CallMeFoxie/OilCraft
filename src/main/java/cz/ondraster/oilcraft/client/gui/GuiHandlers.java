package cz.ondraster.oilcraft.client.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import cz.ondraster.oilcraft.containers.ContainerValve;
import cz.ondraster.oilcraft.factory.tileentities.TileEntityValve;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandlers implements IGuiHandler {

   @Override
   public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
      TileEntity te = world.getTileEntity(x, y, z);
      if (te instanceof TileEntityValve)
         return new ContainerValve(player.inventory, (TileEntityValve) te);

      return null;
   }

   @Override
   public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
      TileEntity te = world.getTileEntity(x, y, z);
      if (te instanceof TileEntityValve)
         return new GuiValve(player.inventory, (TileEntityValve) te);

      return null;
   }

}
