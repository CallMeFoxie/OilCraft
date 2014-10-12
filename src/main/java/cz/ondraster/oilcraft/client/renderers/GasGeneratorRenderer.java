package cz.ondraster.oilcraft.client.renderers;

import cz.ondraster.oilcraft.References;
import cz.ondraster.oilcraft.client.renderers.models.ModelGasGenerator;
import cz.ondraster.oilcraft.tileentities.TileEntityGeneratorGas;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;

public class GasGeneratorRenderer extends TileEntitySpecialRenderer {
   private final ModelGasGenerator generator;

   public GasGeneratorRenderer() {
      this.generator = new ModelGasGenerator();
   }

   @Override
   public void renderTileEntityAt(TileEntity var1, double x, double y, double z, float scale) {

      ForgeDirection rotation = ((TileEntityGeneratorGas) var1).getOrientation();

      TextureManager tm = Minecraft.getMinecraft().getTextureManager();

      GL11.glPushMatrix();

      GL11.glTranslatef((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
      if (rotation == ForgeDirection.EAST)
         GL11.glRotatef(270, 0f, 1f, 0f);
      else if (rotation == ForgeDirection.SOUTH)
         GL11.glRotatef(180, 0f, 1f, 0f);
      else if (rotation == ForgeDirection.WEST)
         GL11.glRotatef(90, 0f, 1f, 0f);

      tm.bindTexture(new ResourceLocation(References.MODID, "textures/models/" + References.Textures.MODELGASGENERATOR));

      TileEntityGeneratorGas te = (TileEntityGeneratorGas) var1;

      if (te.getEnergyStored(ForgeDirection.UNKNOWN) > 0)
         te.rotationOffset += 45;
      if (te.rotationOffset >= 360) {
         te.rotationOffset = 0;
      }

      float off = te.rotationOffset;

      GL11.glRotatef(180, 1f, 0f, 0f);
      GL11.glTranslatef(0, -1f, 0f);
      this.generator.render(off);


      GL11.glPopMatrix();
   }
}
