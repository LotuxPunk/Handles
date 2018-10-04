package com.vandendaelen.handles.object.block.model;


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTardisInterface extends ModelBase
{
    //fields
    ModelRenderer shape1;
    ModelRenderer shape2;
    ModelRenderer shape3;
    ModelRenderer shape4;
    ModelRenderer shape5;
    ModelRenderer shape6;
    ModelRenderer shape7;
    ModelRenderer shape8;
    ModelRenderer shape9;
    ModelRenderer shape10;
    ModelRenderer shape11;
    ModelRenderer shape12;
    ModelRenderer shape13;
    ModelRenderer shape14;
    ModelRenderer shape15;

    public ModelTardisInterface(){
        textureWidth = 128;
        textureHeight = 128;

        shape1 = new ModelRenderer(this, 0, 24);
        shape1.addBox(-7F, 21F, -2F, 14, 1, 4);
        shape1.setRotationPoint(0F, 0F, 0F);
        shape1.setTextureSize(128, 128);
        shape1.mirror = true;
        setRotation(shape1, 0F, 0F, 0F);
        shape2 = new ModelRenderer(this, 0, 29);
        shape2.addBox(-7F, 21.5F, -2.5F, 14, 3, 5);
        shape2.setRotationPoint(0F, 0F, 0F);
        shape2.setTextureSize(128, 128);
        shape2.mirror = true;
        setRotation(shape2, 0F, 0F, 0F);
        shape3 = new ModelRenderer(this, 0, 20);
        shape3.addBox(-7F, 20.5F, -1.5F, 14, 1, 3);
        shape3.setRotationPoint(0F, 0F, 0F);
        shape3.setTextureSize(128, 128);
        shape3.mirror = true;
        setRotation(shape3, 0F, 0F, 0F);
        shape4 = new ModelRenderer(this, 0, 5);
        shape4.addBox(7F, 20.5F, -2.5F, 1, 1, 5);
        shape4.setRotationPoint(0F, 0F, 0F);
        shape4.setTextureSize(128, 128);
        shape4.mirror = true;
        setRotation(shape4, 0F, 0F, 0F);
        shape5 = new ModelRenderer(this, 0, 0);
        shape5.addBox(7F, 20F, -2F, 1, 1, 4);
        shape5.setRotationPoint(0F, 0F, 0F);
        shape5.setTextureSize(128, 128);
        shape5.mirror = true;
        setRotation(shape5, 0F, 0F, 0F);
        shape6 = new ModelRenderer(this, 0, 57);
        shape6.addBox(-1F, 20F, -3F, 2, 1, 6);
        shape6.setRotationPoint(0F, 0F, 0F);
        shape6.setTextureSize(128, 128);
        shape6.mirror = true;
        setRotation(shape6, 0F, 0F, 0F);
        shape7 = new ModelRenderer(this, 0, 11);
        shape7.addBox(7F, 21F, -3F, 1, 3, 6);
        shape7.setRotationPoint(0F, 0F, 0F);
        shape7.setTextureSize(128, 128);
        shape7.mirror = true;
        setRotation(shape7, 0F, 0F, 0F);
        shape8 = new ModelRenderer(this, 0, 71);
        shape8.addBox(-1F, 12F, -1F, 2, 2, 2);
        shape8.setRotationPoint(0F, 0F, 0F);
        shape8.setTextureSize(128, 128);
        shape8.mirror = true;
        setRotation(shape8, 0F, 0.7853982F, 0F);
        shape9 = new ModelRenderer(this, 0, 42);
        shape9.addBox(-3F, 20.5F, -2.5F, 6, 1, 5);
        shape9.setRotationPoint(0F, 0F, 0F);
        shape9.setTextureSize(128, 128);
        shape9.mirror = true;
        setRotation(shape9, 0F, 0F, 0F);
        shape10 = new ModelRenderer(this, 0, 48);
        shape10.addBox(-3F, 21F, -3F, 6, 3, 6);
        shape10.setRotationPoint(0F, 0F, 0F);
        shape10.setTextureSize(128, 128);
        shape10.mirror = true;
        setRotation(shape10, 0F, 0F, 0F);
        shape11 = new ModelRenderer(this, 0, 0);
        shape11.addBox(-8F, 20F, -2F, 1, 1, 4);
        shape11.setRotationPoint(0F, 0F, 0F);
        shape11.setTextureSize(128, 128);
        shape11.mirror = true;
        setRotation(shape11, 0F, 0F, 0F);
        shape12 = new ModelRenderer(this, 0, 5);
        shape12.addBox(-8F, 20.5F, -2.5F, 1, 1, 5);
        shape12.setRotationPoint(0F, 0F, 0F);
        shape12.setTextureSize(128, 128);
        shape12.mirror = true;
        setRotation(shape12, 0F, 0F, 0F);
        shape13 = new ModelRenderer(this, 0, 11);
        shape13.addBox(-8F, 21F, -3F, 1, 3, 6);
        shape13.setRotationPoint(0F, 0F, 0F);
        shape13.setTextureSize(128, 128);
        shape13.mirror = true;
        setRotation(shape13, 0F, 0F, 0F);
        shape14 = new ModelRenderer(this, 0, 37);
        shape14.addBox(-3F, 20F, -2F, 6, 1, 4);
        shape14.setRotationPoint(0F, 0F, 0F);
        shape14.setTextureSize(128, 128);
        shape14.mirror = true;
        setRotation(shape14, 0F, 0F, 0F);
        shape15 = new ModelRenderer(this, 0, 64);
        shape15.addBox(-0.5F, 14F, -0.5F, 1, 6, 1);
        shape15.setRotationPoint(0F, 0F, 0F);
        shape15.setTextureSize(128, 128);
        shape15.mirror = true;
        setRotation(shape15, 0F, 0.7853982F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        shape1.render(f5);
        shape2.render(f5);
        shape3.render(f5);
        shape4.render(f5);
        shape5.render(f5);
        shape6.render(f5);
        shape7.render(f5);
        shape8.render(f5);
        shape9.render(f5);
        shape10.render(f5);
        shape11.render(f5);
        shape12.render(f5);
        shape13.render(f5);
        shape14.render(f5);
        shape15.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }

}
