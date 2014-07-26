package cz.ondraster.oilcraft.client.renderers.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelPipeDown extends ModelBase {
   //fields
   ModelRenderer Shape1;

   public ModelPipeDown() {
      textureWidth = 40;
      textureHeight = 32;

      Shape1 = new ModelRenderer(this, 0, 0);
      Shape1.addBox(-2F, 0F, -2F, 4, 16, 4);
      Shape1.setRotationPoint(0F, 8F, 0F);
      Shape1.setTextureSize(40, 32);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0F);
   }

   public void render() {
      Shape1.render(0.0625f);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }
}