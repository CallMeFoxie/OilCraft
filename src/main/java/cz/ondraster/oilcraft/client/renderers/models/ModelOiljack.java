package cz.ondraster.oilcraft.client.renderers.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelOiljack extends ModelBase {
   //fields
   ModelRenderer leg2;
   ModelRenderer leg1;
   ModelRenderer leg3;
   ModelRenderer leg4;
   ModelRenderer top;
   ModelRenderer swing;
   ModelRenderer beamTop;
   ModelRenderer beamSide;
   ModelRenderer motor;
   ModelRenderer armLeft;
   ModelRenderer armRight;
   ModelRenderer armJoin;
   ModelRenderer armConnect;
   ModelRenderer wheelLeft;
   ModelRenderer wheelRight;
   ModelRenderer powerConnector;

   public ModelOiljack() {
      textureWidth = 128;
      textureHeight = 128;

      leg2 = new ModelRenderer(this, 0, 0);
      leg2.addBox(-4F, 0F, -4F, 4, 32, 4);
      leg2.setRotationPoint(0F, 0F, 0F);
      leg2.setTextureSize(128, 128);
      leg2.mirror = true;
      setRotation(leg2, -0.3490659F, 0F, 0.3490659F);
      leg1 = new ModelRenderer(this, 0, 0);
      leg1.addBox(-4F, 0F, 0F, 4, 32, 4);
      leg1.setRotationPoint(0F, 0F, 0F);
      leg1.setTextureSize(128, 128);
      leg1.mirror = true;
      setRotation(leg1, 0.3490659F, 0F, 0.3490659F);
      leg3 = new ModelRenderer(this, 0, 0);
      leg3.addBox(0F, 0F, -4F, 4, 32, 4);
      leg3.setRotationPoint(0F, 0F, 0F);
      leg3.setTextureSize(128, 128);
      leg3.mirror = true;
      setRotation(leg3, -0.3490659F, 0F, -0.3490659F);
      leg4 = new ModelRenderer(this, 0, 0);
      leg4.addBox(0F, 0F, 0F, 4, 32, 4);
      leg4.setRotationPoint(0F, 0F, 0F);
      leg4.setTextureSize(128, 128);
      leg4.mirror = true;
      setRotation(leg4, 0.3490659F, 0F, -0.3490659F);
      top = new ModelRenderer(this, 16, 32);
      top.addBox(-4F, -3F, -4F, 8, 6, 8);
      top.setRotationPoint(0F, 0F, 0F);
      top.setTextureSize(128, 128);
      top.mirror = true;
      setRotation(top, 0F, 0F, 0F);
      swing = new ModelRenderer(this, 0, 46);
      swing.addBox(-4F, -4F, -1F, 8, 4, 2);
      swing.setRotationPoint(0F, 0F, 0F);
      swing.setTextureSize(128, 128);
      swing.mirror = true;
      setRotation(swing, 0F, 0F, 0F);
      beamTop = new ModelRenderer(this, 16, 0);
      beamTop.addBox(-4F, -8F, -12F, 8, 8, 24);
      beamTop.setRotationPoint(0F, -4F, 0F);
      beamTop.setTextureSize(128, 128);
      beamTop.mirror = true;
      setRotation(beamTop, 0F, 0F, 0F);
      beamSide = new ModelRenderer(this, 0, 52);
      beamSide.addBox(-4F, -12F, 12F, 8, 16, 2);
      beamSide.setRotationPoint(0F, -4F, 0F);
      beamSide.setTextureSize(128, 128);
      beamSide.mirror = true;
      setRotation(beamSide, 0F, 0F, 0F);
      motor = new ModelRenderer(this, 40, 46);
      motor.addBox(-4F, -6F, -8F, 8, 6, 16);
      motor.setRotationPoint(0F, 20F, -26F);
      motor.setTextureSize(128, 128);
      motor.mirror = true;
      setRotation(motor, 0F, 0F, 0F);
      armLeft = new ModelRenderer(this, 20, 64);
      armLeft.addBox(-1F, -16F, -1F, 2, 16, 2);
      armLeft.setRotationPoint(-7F, 16F, -20F);
      armLeft.setTextureSize(128, 128);
      armLeft.mirror = true;
      setRotation(armLeft, -0.3839724F, 0F, 0F);
      armRight = new ModelRenderer(this, 20, 64);
      armRight.addBox(-1F, -16F, -1F, 2, 16, 2);
      armRight.setRotationPoint(7F, 16F, -20F);
      armRight.setTextureSize(128, 128);
      armRight.mirror = true;
      setRotation(armRight, -0.3839724F, 0F, 0F);
      armJoin = new ModelRenderer(this, 48, 32);
      armJoin.addBox(-8F, -16F, -1F, 16, 2, 2);
      armJoin.setRotationPoint(0F, 16F, -20F);
      armJoin.setTextureSize(128, 128);
      armJoin.mirror = true;
      setRotation(armJoin, -0.3839724F, 0F, 0F);
      armConnect = new ModelRenderer(this, 0, 36);
      armConnect.addBox(-1F, -22F, -1F, 2, 6, 2);
      armConnect.setRotationPoint(0F, 16F, -20F);
      armConnect.setTextureSize(128, 128);
      armConnect.mirror = true;
      setRotation(armConnect, -0.3839724F, 0F, 0F);
      wheelLeft = new ModelRenderer(this, 20, 46);
      wheelLeft.addBox(-1F, -4F, -4F, 2, 8, 8);
      wheelLeft.setRotationPoint(-5F, 16F, -20F);
      wheelLeft.setTextureSize(128, 128);
      wheelLeft.mirror = true;
      setRotation(wheelLeft, -0.7853982F, 0F, 0F);
      wheelRight = new ModelRenderer(this, 20, 46);
      wheelRight.addBox(-1F, -4F, -4F, 2, 8, 8);
      wheelRight.setRotationPoint(5F, 16F, -20F);
      wheelRight.setTextureSize(128, 128);
      wheelRight.mirror = true;
      setRotation(wheelRight, -0.7853982F, 0F, 0F);
      powerConnector = new ModelRenderer(this, 32, 72);
      powerConnector.addBox(-8F, -8F, -6F, 16, 16, 6);
      powerConnector.setRotationPoint(0F, 16F, -34F);
      powerConnector.setTextureSize(128, 128);
      powerConnector.mirror = true;
      setRotation(powerConnector, 0F, 0F, 0F);
   }

   public void render(float angle) {
      float renderDepth = 0.0625F;
      //angle = 0;
      float finalAngle = angle % 180;
      if (angle >= 180)
         finalAngle = 361 - angle;

      finalAngle -= 90;

      float rotationAngle = angle;

      rotationAngle += 90f;

      //angle *= 4;
      if (true) {
         armLeft.offsetY = (float) Math.sin(Math.toRadians(rotationAngle)) / 6f;
         armRight.offsetY = (float) Math.sin(Math.toRadians(rotationAngle)) / 6f;
         armJoin.offsetY = (float) Math.sin(Math.toRadians(rotationAngle)) / 6f;
         armConnect.offsetY = (float) Math.sin(Math.toRadians(rotationAngle)) / 6f;

         //System.out.println(armLeft.offsetY);

         armJoin.offsetZ = (float) -Math.cos(Math.toRadians(rotationAngle)) / 6f;
         armLeft.offsetZ = (float) -Math.cos(Math.toRadians(rotationAngle)) / 6f;
         armRight.offsetZ = (float) -Math.cos(Math.toRadians(rotationAngle)) / 6f;
         armConnect.offsetZ = (float) -Math.cos(Math.toRadians(rotationAngle)) / 6f;

         float offZ = armJoin.offsetZ;
         float offY = armJoin.offsetY;
         float rotation = (float) Math.asin(offZ);

         setRotation(armLeft, rotation - 0.3839724F - 0.05f, 0f, 0f);
         setRotation(armRight, rotation - 0.3839724F - 0.05f, 0f, 0f);
         setRotation(armJoin, rotation - 0.3839724F - 0.05f, 0f, 0f);
         setRotation(armConnect, rotation - 0.3839724F - 0.05f, 0f, 0f);

         setRotation(wheelLeft, (float) Math.toRadians(rotationAngle + 45), 0f, 0f);
         setRotation(wheelRight, (float) Math.toRadians(rotationAngle + 45), 0f, 0f);

         //prevAngle = angle;
      }

      setRotation(beamTop, (float) Math.sin(Math.toRadians(angle + 120)) / 4f, 0f, 0f);
      setRotation(beamSide, (float) Math.sin(Math.toRadians(angle + 120)) / 4f, 0f, 0f);

      leg2.render(renderDepth);
      leg1.render(renderDepth);
      leg3.render(renderDepth);
      leg4.render(renderDepth);
      top.render(renderDepth);
      swing.render(renderDepth);

      beamTop.render(renderDepth);
      beamSide.render(renderDepth);
      motor.render(renderDepth);
      armLeft.render(renderDepth);
      armRight.render(renderDepth);
      armJoin.render(renderDepth);
      armConnect.render(renderDepth);

      wheelLeft.render(renderDepth);
      wheelRight.render(renderDepth);

      powerConnector.render(renderDepth);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
      //super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
   }

}
