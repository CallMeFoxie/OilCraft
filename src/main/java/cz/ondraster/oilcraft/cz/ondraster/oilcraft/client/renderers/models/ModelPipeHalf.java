package cz.ondraster.oilcraft.cz.ondraster.oilcraft.client.renderers.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

/**
 * Created by Ondra on 29.6.2014.
 */
public class ModelPipeHalf extends ModelBase {
   //fields
   ModelRenderer middle;
   ModelRenderer edge;

   public ModelPipeHalf() {
      textureWidth = 64;
      textureHeight = 32;

      middle = new ModelRenderer(this, 0, 0);
      middle.addBox(-4F, -4F, -8F, 8, 8, 4);
      middle.setRotationPoint(0F, 0F, 0F);
      middle.setTextureSize(64, 32);
      middle.mirror = true;
      setRotation(middle, 0F, 0F, 0F);
      edge = new ModelRenderer(this, 0, 0);
      edge.addBox(-6F, -6F, -8F, 12, 12, 2);
      edge.setRotationPoint(0F, 0F, 0F);
      edge.setTextureSize(64, 32);
      edge.mirror = true;
      setRotation(edge, 0F, 0F, 0F);
   }

   public void render() {
      float depth = 0.0625f;
      middle.render(depth);
      edge.render(depth);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {

   }

}