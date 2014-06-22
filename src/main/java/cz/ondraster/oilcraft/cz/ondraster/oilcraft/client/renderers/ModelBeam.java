package cz.ondraster.oilcraft.cz.ondraster.oilcraft.client.renderers;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Created by Ondra on 22.6.2014.
 */
public class ModelBeam extends ModelBase {
   ModelRenderer beambase;

   public ModelBeam() {
      beambase = new ModelRenderer(this, 0, 0);
      beambase.addBox(-8F, -8F, -8F, 32, 16, 16);
      beambase.addBox(-16F, -16F, -8F, 8, 32, 16);
      beambase.setRotationPoint(0F, 0F, 0F);
      beambase.mirror = true;
      setRotation(beambase, 0F, 0F, 0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      beambase.render(f5);
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
