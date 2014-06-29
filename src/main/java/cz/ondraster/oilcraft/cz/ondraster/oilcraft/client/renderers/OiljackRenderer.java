package cz.ondraster.oilcraft.cz.ondraster.oilcraft.client.renderers;

import cz.ondraster.oilcraft.cz.ondraster.oilcraft.client.renderers.models.ModelOiljack;
import cz.ondraster.oilcraft.entities.EntityOiljack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by Ondra on 21.6.2014.
 */
public class OiljackRenderer extends TileEntitySpecialRenderer {

   private final ModelOiljack jack;

   public OiljackRenderer() {
      this.jack = new ModelOiljack();
   }

   @Override
   public void renderTileEntityAt(TileEntity var1, double x, double y, double z, float scale) {

      TextureManager tm = Minecraft.getMinecraft().getTextureManager();

      GL11.glPushMatrix();

      GL11.glTranslatef((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
      //GL11.glRotatef(var8, 0.0F, 1.0F, 0.0F);


      tm.bindTexture(new ResourceLocation("oilcraft", "textures/blocks/beam.png"));

      EntityOiljack te = (EntityOiljack) var1;

      float off = te.renderOffset;
      //if (te.renderOffset >= 30)
      //   off = (30 * 0.8f) - off;

      //off -= (15 * 0.8d);

      //GL11.glTranslated(0, 0, 0);
      //GL11.glRotated(off, 0, 0, 2d);
      //GL11.glTranslated(0, 0, 0);
      //GL11.glScalef(0.5f, 0.5f, 0.5f);
      GL11.glRotatef(180, 1f, 0f, 0f);
      GL11.glTranslatef(0, -1f, 0f);
      this.jack.render(off);

      GL11.glPopMatrix();
   }
}
