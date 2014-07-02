package cz.ondraster.oilcraft.client.renderers.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelPipeMiddle extends ModelBase {
   //fields
   ModelRenderer Shape1;

   public ModelPipeMiddle() {
      textureWidth = 32;
      textureHeight = 32;

      Shape1 = new ModelRenderer(this, 0, 0);
      Shape1.addBox(-4F, -4F, -4F, 8, 8, 8);
      Shape1.setRotationPoint(0F, 0F, 0F);
      Shape1.setTextureSize(64, 32);
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

   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {

   }

}
