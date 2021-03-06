package cz.ondraster.oilcraft.client.renderers;

import cz.ondraster.oilcraft.References;
import cz.ondraster.oilcraft.client.renderers.models.ModelOiljack;
import cz.ondraster.oilcraft.tileentities.TileEntityOiljack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;

public class OiljackRenderer extends TileEntitySpecialRenderer {

   private final ModelOiljack jack;

   public OiljackRenderer() {
      this.jack = new ModelOiljack();
   }

   @Override
   public void renderTileEntityAt(TileEntity var1, double x, double y, double z, float scale) {

      ForgeDirection rotation = ((TileEntityOiljack) var1).getOrientation();

      TextureManager tm = Minecraft.getMinecraft().getTextureManager();

      GL11.glPushMatrix();

      GL11.glTranslatef((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
      if (rotation == ForgeDirection.EAST)
         GL11.glRotatef(270, 0f, 1f, 0f);
      else if (rotation == ForgeDirection.SOUTH)
         GL11.glRotatef(180, 0f, 1f, 0f);
      else if (rotation == ForgeDirection.WEST)
         GL11.glRotatef(90, 0f, 1f, 0f);

      tm.bindTexture(new ResourceLocation(References.MODID, "textures/blocks/" + References.Textures.BLOCKOILJACK3D));

      TileEntityOiljack te = (TileEntityOiljack) var1;

      if (te.canWork())
         te.renderOffset += 4;
      if (te.renderOffset >= 360) {
         te.renderOffset = 0;
      }

      float off = te.renderOffset;

      GL11.glRotatef(180, 1f, 0f, 0f);
      GL11.glTranslatef(0, -1f, 0f);
      this.jack.render(off);


      GL11.glPopMatrix();
   }
}
