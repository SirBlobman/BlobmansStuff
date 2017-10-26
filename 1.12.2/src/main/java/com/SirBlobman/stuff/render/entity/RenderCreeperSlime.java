package com.SirBlobman.stuff.render.entity;

import com.SirBlobman.stuff.BlobmansStuff;
import com.SirBlobman.stuff.entity.custom.EntityCreeperSlime;
import com.SirBlobman.stuff.render.entity.layer.LayerCreeperSlimeCharge;

import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderCreeperSlime extends RenderLiving<EntityCreeperSlime> {
    public RenderCreeperSlime(RenderManager rm) {
        super(rm, new ModelCreeper(), 0.5F);
        addLayer(new LayerCreeperSlimeCharge(this));
    }
    
    @Override
    public void preRenderCallback(EntityCreeperSlime en, float ticks) {
        float f = en.getCreeperFlashIntensity(ticks);
        float f1 = 1.0F + MathHelper.sin(f * 100.0F) * f * 0.01F;
        f = MathHelper.clamp(f, 0.0F, 1.0F);
        f = f * f;
        f = f * f;
        float f2 = (1.0F + f * 0.4F) * f1;
        float f3 = (1.0F + f * 0.1F) / f1;
        GlStateManager.scale(f2, f3, f2);
    }
    
    @Override
    public int getColorMultiplier(EntityCreeperSlime en, float brightness, float ticks) {
        float f = en.getCreeperFlashIntensity(ticks);
        if(((int) (f * 10.0F) % 2) == 0) return 0;
        else {
            int i = (int) (f * 0.2F * 255.0F);
            i = MathHelper.clamp(i, 0, 255);
            return (i << 24 | 822083583);
        }
    }
    
    @Override
    public ResourceLocation getEntityTexture(EntityCreeperSlime en) {
        ResourceLocation rl = new ResourceLocation(BlobmansStuff.MODID, "textures/entity/slime/creeper.png");
        return rl;
    }
}