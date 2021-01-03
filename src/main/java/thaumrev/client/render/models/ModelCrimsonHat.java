package thaumrev.client.render.models;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCrimsonHat extends ModelBiped {

    public ModelRenderer brim;
    public ModelRenderer top;

    public ModelCrimsonHat() {
        this.textureWidth = 64;
        this.textureHeight = 64;

        this.brim = new ModelRenderer(this, 0, 32);
        this.brim.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.setRotation(brim, 0.0F, 0.0F, 0.0F);
        this.brim.setTextureSize(64, 32);
        this.brim.addBox(-5.0F, -8.5F, -5.0F, 10, 2, 10, 0.0F);

        this.top = new ModelRenderer(this, 0, 44);
        this.top.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.setRotation(top, 0.0F, 0.0F, 0.0F);
        this.top.setTextureSize(64, 32);
        this.top.addBox(-4.0F, -15.5F, -4.0F, 8, 7, 8, 0.0F);

        // brim.addChild(top);
        this.bipedHead.addChild(brim);
        this.brim.addChild(top);

    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }

    public void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
