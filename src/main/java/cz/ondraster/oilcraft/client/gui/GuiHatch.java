package cz.ondraster.oilcraft.client.gui;

import cz.ondraster.oilcraft.References;
import cz.ondraster.oilcraft.containers.ContainerHatch;
import cz.ondraster.oilcraft.factory.tileentities.TileEntityHatch;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiHatch extends GuiContainer {
   public GuiHatch(InventoryPlayer inventory, TileEntityHatch te) {
      super(new ContainerHatch(inventory, te));
   }

   @Override
   protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
      ResourceLocation rsrc = new ResourceLocation(References.MODID, "textures/gui/hatch.png");

      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      this.mc.getTextureManager().bindTexture(rsrc);
      int k = (this.width - this.xSize) / 2;
      int l = (this.height - this.ySize) / 2;
      this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
   }
}
