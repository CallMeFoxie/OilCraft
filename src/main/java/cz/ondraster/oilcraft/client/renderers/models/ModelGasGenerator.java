package cz.ondraster.oilcraft.client.renderers.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelGasGenerator extends ModelBase {

   ModelRenderer base;
   ModelRenderer tank;
   ModelRenderer boxLeft;
   ModelRenderer boxRight;
   ModelRenderer boxBack;
   ModelRenderer boxFront;
   ModelRenderer boxTop;
   ModelRenderer shaft;
   ModelRenderer shaft2;
   ModelRenderer bargraph;

   public ModelGasGenerator() {
      textureWidth = 96;
      textureHeight = 64;

      base = new ModelRenderer(this, 0, 0);
      base.addBox(-8F, -2F, -8F, 16, 2, 16);
      base.setRotationPoint(0F, 24F, 0F);
      base.setTextureSize(64, 32);
      base.mirror = true;
      setRotation(base, 0F, 0F, 0F);
      tank = new ModelRenderer(this, 52, 20);
      tank.addBox(-2F, -12F, -2F, 4, 12, 4);
      tank.setRotationPoint(-6F, 22F, -6F);
      tank.setTextureSize(64, 32);
      tank.mirror = true;
      setRotation(tank, 0F, 0F, 0F);
      boxLeft = new ModelRenderer(this, 0, 36);
      boxLeft.addBox(-1F, -8F, -1F, 2, 8, 14);
      boxLeft.setRotationPoint(1F, 21.5F, -7F);
      boxLeft.setTextureSize(64, 32);
      boxLeft.mirror = true;
      setRotation(boxLeft, 0F, 0F, -0.5235988F);
      boxRight = new ModelRenderer(this, 0, 36);
      boxRight.addBox(-1F, -8F, -1F, 2, 8, 14);
      boxRight.setRotationPoint(2.5F, 21.5F, -7F);
      boxRight.setTextureSize(64, 32);
      boxRight.mirror = true;
      setRotation(boxRight, 0F, 0F, 0.5235988F);
      boxBack = new ModelRenderer(this, 32, 36);
      boxBack.addBox(-4F, -4F, -0.5F, 8, 7, 1);
      boxBack.setRotationPoint(1.5F, 19F, -6F);
      boxBack.setTextureSize(64, 32);
      boxBack.mirror = true;
      setRotation(boxBack, 0F, 0F, 0F);
      boxFront = new ModelRenderer(this, 32, 36);
      boxFront.addBox(-4F, -4F, -1F, 8, 7, 1);
      boxFront.setRotationPoint(1.5F, 19F, 6F);
      boxFront.setTextureSize(64, 32);
      boxFront.mirror = true;
      setRotation(boxFront, 0F, 0F, 0F);
      boxTop = new ModelRenderer(this, 0, 20);
      boxTop.addBox(-6F, 0F, -7F, 11, 1, 14);
      boxTop.setRotationPoint(2.3F, 14F, -1F);
      boxTop.setTextureSize(64, 32);
      boxTop.mirror = true;
      setRotation(boxTop, 0F, 0F, 0F);
      shaft = new ModelRenderer(this, 32, 56);
      shaft.addBox(-0.5F, -1F, -1F, 1, 2, 1);
      shaft.setRotationPoint(1.75F, 18F, -6.5F);
      shaft.setTextureSize(64, 32);
      shaft.mirror = true;
      setRotation(shaft, 0F, 0F, 0F);
      shaft2 = new ModelRenderer(this, 32, 48);
      shaft2.addBox(-1F, -1F, 0F, 2, 2, 2);
      shaft2.setRotationPoint(1.75F, 18F, 6F);
      shaft2.setTextureSize(64, 32);
      shaft2.mirror = true;
      setRotation(shaft2, 0F, 0F, 0F);
      bargraph = new ModelRenderer(this, 60, 36);
      bargraph.addBox(-1F, -3F, -4F, 1, 3, 12);
      bargraph.setRotationPoint(-6.5F, 22F, 0F);
      bargraph.setTextureSize(64, 32);
      bargraph.mirror = true;
      setRotation(bargraph, 0F, 0F, 0.5235988F);
   }

   public void render(float angle) {
      float f5 = 0.0625f;


      base.render(f5);
      tank.render(f5);
      boxLeft.render(f5);
      boxRight.render(f5);
      boxBack.render(f5);
      boxFront.render(f5);
      boxTop.render(f5);
      shaft.rotateAngleX = 0;
      shaft.rotateAngleZ = (float) Math.toRadians(angle);
      shaft.render(f5);
      shaft2.rotateAngleX = 0;
      shaft2.rotateAngleZ = (float) Math.toRadians(angle);
      shaft2.render(f5);
      bargraph.render(f5);

   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
   }

}
