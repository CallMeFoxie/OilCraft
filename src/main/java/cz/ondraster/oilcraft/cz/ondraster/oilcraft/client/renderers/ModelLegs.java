package cz.ondraster.oilcraft.cz.ondraster.oilcraft.client.renderers;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Created by Ondra on 23.6.2014.
 */
public class ModelLegs extends ModelBase {
   ModelRenderer bridle;

   public ModelLegs() {
      bridle = new ModelRenderer(this, 0, 0);
      bridle.addBox(-4F, -4F, -4F, 8, 8, 8);
      bridle.setRotationPoint(0F, 0F, 0F);
      bridle.mirror = true;
      setRotation(bridle, 0F, 0F, 0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      bridle.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
      super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
   }
}
