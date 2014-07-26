package cz.ondraster.oilcraft.client.renderers;

import cz.ondraster.oilcraft.References;
import cz.ondraster.oilcraft.client.renderers.models.ModelOiljackPipe;
import cz.ondraster.oilcraft.client.renderers.models.ModelPipeDown;
import cz.ondraster.oilcraft.client.renderers.models.ModelPipeHalf;
import cz.ondraster.oilcraft.entities.EntityOiljackPipe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class OiljackPipeRenderer extends TileEntitySpecialRenderer {

   private final ModelOiljackPipe pipe;
   private final ModelPipeHalf pipeEdge;
   private final ModelPipeDown actualPipe;

   public OiljackPipeRenderer() {
      this.pipe = new ModelOiljackPipe();
      this.pipeEdge = new ModelPipeHalf();
      this.actualPipe = new ModelPipeDown();
   }

   @Override
   public void renderTileEntityAt(TileEntity var1, double x, double y, double z, float scale) {

      TextureManager tm = Minecraft.getMinecraft().getTextureManager();

      GL11.glPushMatrix();

      GL11.glTranslatef((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);

      tm.bindTexture(new ResourceLocation(References.MODID, "textures/blocks/" + References.Textures.BLOCKOILJACKPIPE));

      GL11.glRotatef(180, 1f, 0f, 0f);
      GL11.glTranslatef(0, -1f, 0f);
      this.pipe.render();

      GL11.glPopMatrix();

      GL11.glPushMatrix();

      GL11.glTranslatef((float) x + 0.5F, (float) y - 0.5F, (float) z + 0.5F);

      tm.bindTexture(new ResourceLocation(References.MODID, "textures/blocks/" + References.Textures.BLOCKPIPE3D));

      GL11.glRotatef(180, 1f, 0f, 0f);
      GL11.glTranslatef(0, -1f, 0f);
      this.pipeEdge.render();

      GL11.glPopMatrix();

      GL11.glPushMatrix();


      GL11.glScalef(1, ((EntityOiljackPipe) var1).getDepth(), 1);
      GL11.glTranslatef((float) x + 0.5F, (float) y - 1F, (float) z + 0.5F);

      tm.bindTexture(new ResourceLocation(References.MODID, "textures/blocks/" + References.Textures.BLOCKACTUALPIPE));
      this.pipeEdge.render();

      GL11.glPopMatrix();
   }
}
