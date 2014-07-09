package cz.ondraster.oilcraft.client.gui;

import cz.ondraster.oilcraft.References;
import cz.ondraster.oilcraft.containers.ContainerValve;
import cz.ondraster.oilcraft.factory.tileentities.TileEntityValve;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;
import org.lwjgl.opengl.GL11;

public class GuiValve extends GuiContainer {
   TileEntityValve valve;

   public GuiValve(InventoryPlayer inv, TileEntityValve valve) {
      super(new ContainerValve(inv, valve));
      this.valve = valve;
   }

   @Override
   protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
      ResourceLocation rsrc = new ResourceLocation(References.MODID, "textures/gui/valve.png");

      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      this.mc.getTextureManager().bindTexture(rsrc);
      int k = (this.width - this.xSize) / 2;
      int l = (this.height - this.ySize) / 2;
      this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

      // FontRenderer, x, y, colour
      FluidStack fluidStack = valve.getTankInfo(ForgeDirection.UNKNOWN)[0].fluid;
      if (fluidStack == null)
         this.drawCenteredString(fontRendererObj, "Empty", this.width / 2, (this.height - this.ySize) / 2 + 35, 0xFFFFFF);
      else {
         this.drawCenteredString(fontRendererObj, "Fluid: " + fluidStack.getFluid().getLocalizedName(fluidStack), this.width / 2 + 10, (this.height - this.ySize) / 2 + 27, 0xFFFFFF);
         this.drawCenteredString(fontRendererObj, "Amount: " + fluidStack.amount + "mB", this.width / 2 + 10, (this.height - this.ySize) / 2 + 43, 0xFFFFFF);
      }
   }
}
