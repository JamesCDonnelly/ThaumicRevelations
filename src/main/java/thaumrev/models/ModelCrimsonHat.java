package thaumrev.models;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCrimsonHat extends ModelBiped {

    public ModelRenderer brim;
    public ModelRenderer top;

    public ModelCrimsonHat() {
        textureWidth = 64;
        textureHeight = 64;

        top = new ModelRenderer(this, 0, 44);
        top.setRotationPoint(0.0F, 0.0F, 0.0F);
        top.setTextureSize(64, 32);
        top.addBox(-4.0F, -15.5F, -4.0F, 8, 7, 8, 0.0F);

        brim = new ModelRenderer(this, 0, 32);
        brim.setRotationPoint(0.0F, 0.0F, 0.0F);
        brim.setTextureSize(64, 32);
        brim.addBox(-5.0F, -8.5F, -5.0F, 10, 2, 10, 0.0F);

        brim.addChild(top);
        this.bipedHead.addChild(brim);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }

    private void setRotation(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
