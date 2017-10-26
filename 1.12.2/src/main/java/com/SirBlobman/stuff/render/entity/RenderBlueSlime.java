package com.SirBlobman.stuff.render.entity;

import com.SirBlobman.stuff.BlobmansStuff;
import com.SirBlobman.stuff.entity.custom.EntityBlueSlime;
import com.SirBlobman.stuff.render.entity.layer.LayerBlueSlimeGel;

import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderBlueSlime extends RenderLiving<EntityBlueSlime> {
    public RenderBlueSlime(RenderManager rm) {
        super(rm, new ModelSlime(16), 0.25F);
        addLayer(new LayerBlueSlimeGel(this));
    }
    
    @Override
    public void doRender(EntityBlueSlime en, double x, double y, double z, float yaw, float ticks) {
        shadowSize = 0.25F * en.getSlimeSize();
        super.doRender(en, x, y, z, yaw, ticks);
    }
    
    @Override
    public void preRenderCallback(EntityBlueSlime en, float ticks) {
        GlStateManager.scale(0.999F, 0.999F, 0.999F);
        float f1 = en.getSlimeSize();
        float f2 = (en.prevSquishFactor + (en.squishFactor - en.prevSquishFactor) * ticks) / (f1 * 0.5F + 1.0F);
        float f3 = (1.0F / (f2 + 1.0F));
        GlStateManager.scale(f3 * f1, 1.0F / f3 * f1, f3 * f1);
    }
    
    @Override
    public ResourceLocation getEntityTexture(EntityBlueSlime en) {
        ResourceLocation rl = new ResourceLocation(BlobmansStuff.MODID, "textures/entity/slime/blue.png");
        return rl;
    }
}