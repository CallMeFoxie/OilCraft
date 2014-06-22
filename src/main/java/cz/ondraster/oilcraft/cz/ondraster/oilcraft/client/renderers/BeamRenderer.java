package cz.ondraster.oilcraft.cz.ondraster.oilcraft.client.renderers;

import cz.ondraster.oilcraft.TEBeam;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by Ondra on 21.6.2014.
 */
public class BeamRenderer extends TileEntitySpecialRenderer {

   /*@Override
   public void renderTileEntityAt(TileEntity var1, double var2, double var4, double var6, float var8) {
      GL11.glPushMatrix();

      GL11.glTranslated(var2, var4, var6);
      //GL11.glRotatef(var8, 0.0F, 1.0F, 0.0F);

      TextureManager tm = Minecraft.getMinecraft().getTextureManager();
      tm.bindTexture(new ResourceLocation("oilcraft", "textures/blocks/beam.png"));

      TEBeam te = (TEBeam) var1;

      double off = (te.renderOffset % 30) * 0.8d;
      if(te.renderOffset >= 30)
         off = (30 * 0.8d) - off;

      GL11.glTranslated(0.5, 0, 0);
      GL11.glRotated(off, 0, 0, 2d);
      GL11.glTranslated(-0.5, 0, 0);
      Tessellator tessellator = Tessellator.instance;
      tessellator.startDrawingQuads();
      tessellator.addVertexWithUV(0, 0, 0, 0, 0); // bottom left
      tessellator.addVertexWithUV(0, 1, 0, 0, 1); // top left
      tessellator.addVertexWithUV(1, 1, 0, 1, 1); // top right
      tessellator.addVertexWithUV(1, 0, 0, 1, 0); // bottom right



      tessellator.draw();


      GL11.glPopMatrix();
   }*/

   private final ModelBeam beam;
   private final ModelBridle bridle;

   public BeamRenderer() {
      this.beam = new ModelBeam();
      this.bridle = new ModelBridle();
   }

   @Override
   public void renderTileEntityAt(TileEntity var1, double x, double y, double z, float scale) {

      TextureManager tm = Minecraft.getMinecraft().getTextureManager();

      GL11.glPushMatrix();

      GL11.glTranslatef((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
      //GL11.glRotatef(var8, 0.0F, 1.0F, 0.0F);


      tm.bindTexture(new ResourceLocation("oilcraft", "textures/blocks/beam_texture.png"));

      TEBeam te = (TEBeam) var1;

      double off = (te.renderOffset % 30) * 0.8d;
      if (te.renderOffset >= 30)
         off = (30 * 0.8d) - off;

      off -= (15 * 0.8d);

      GL11.glTranslated(0.5, 0, 0);
      GL11.glRotated(off, 0, 0, 2d);
      GL11.glTranslated(-0.5, 0, 0);
      GL11.glScalef(0.5f, 0.5f, 0.5f);
      this.beam.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

      GL11.glPopMatrix();

      // render the bridle

      GL11.glPushMatrix();


      GL11.glTranslatef((float) x, (float) y + (float) Math.asin(Math.toRadians(off)) * -1f - 0.6f * te.bridleLength, (float) z + 0.5F);
      GL11.glScalef(0.1f, te.bridleLength * 3.0f, 0.1f);
      tm.bindTexture(new ResourceLocation("oilcraft", "textures/blocks/bridle_texture.png"));

      this.bridle.render(null, 0f, 0f, -0.0f, 0f, 0f, 0.0f);

      GL11.glPopMatrix();
   }
}
