package cz.ondraster.oilcraft.client.renderers;

import cz.ondraster.oilcraft.References;
import cz.ondraster.oilcraft.client.renderers.models.ModelPipeHalf;
import cz.ondraster.oilcraft.client.renderers.models.ModelPipeMiddle;
import cz.ondraster.oilcraft.tileentities.TileEntityPipe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;

public class PipeRenderer extends TileEntitySpecialRenderer {

   public ModelPipeHalf pipeHalf;
   public ModelPipeMiddle pipeMiddle;

   public PipeRenderer() {
      this.pipeHalf = new ModelPipeHalf();
      this.pipeMiddle = new ModelPipeMiddle();
   }

   @Override
   public void renderTileEntityAt(TileEntity var1, double x, double y, double z, float scale) {
      TextureManager tm = Minecraft.getMinecraft().getTextureManager();

      GL11.glPushMatrix();

      GL11.glTranslatef((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);

      tm.bindTexture(new ResourceLocation(References.MODID, "textures/blocks/" + References.Textures.BLOCKPIPE3D));

      TileEntityPipe pipe = (TileEntityPipe) var1;

      if (pipe.isConnected(ForgeDirection.NORTH)) {
         //GL11.glRotatef();
         GL11.glPushMatrix();
         this.pipeHalf.render();
         GL11.glPopMatrix();
      }
      if (pipe.isConnected(ForgeDirection.SOUTH)) {
         GL11.glPushMatrix();
         GL11.glRotatef(180, 1, 0, 0);
         this.pipeHalf.render();
         GL11.glPopMatrix();
      }
      if (pipe.isConnected(ForgeDirection.EAST)) {
         GL11.glPushMatrix();
         GL11.glRotatef(-90, 0, 1, 0);
         this.pipeHalf.render();
         GL11.glPopMatrix();
      }
      if (pipe.isConnected(ForgeDirection.WEST)) {
         GL11.glPushMatrix();
         GL11.glRotatef(90, 0, 1, 0);
         this.pipeHalf.render();
         GL11.glPopMatrix();
      }
      if (pipe.isConnected(ForgeDirection.UP)) {
         GL11.glPushMatrix();
         GL11.glRotatef(90, 1, 0, 0);
         this.pipeHalf.render();
         GL11.glPopMatrix();
      }
      if (pipe.isConnected(ForgeDirection.DOWN)) {
         GL11.glPushMatrix();
         GL11.glRotatef(-90, 1, 0, 0);
         this.pipeHalf.render();
         GL11.glPopMatrix();
      }


      tm.bindTexture(new ResourceLocation(References.MODID, "textures/blocks/" + References.Textures.BLOCKPIPEMIDDLE3D));
      GL11.glRotatef(180, 1f, 0f, 0f);
      this.pipeMiddle.render();

      GL11.glPopMatrix();
   }
}
