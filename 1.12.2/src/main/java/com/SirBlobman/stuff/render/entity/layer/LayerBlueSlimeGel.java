package com.SirBlobman.stuff.render.entity.layer;

import com.SirBlobman.stuff.entity.custom.EntityBlueSlime;
import com.SirBlobman.stuff.render.entity.RenderBlueSlime;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;

public class LayerBlueSlimeGel implements LayerRenderer<EntityBlueSlime> {
    private final RenderBlueSlime renderSlime;
    private final ModelBase slimeModel = new ModelSlime(0);
    
    public LayerBlueSlimeGel(RenderBlueSlime rbs) {this.renderSlime = rbs;}
    public boolean shouldCombineTextures() {return true;}
    
    @Override
    public void doRenderLayer(EntityBlueSlime en, float swing, float amount, float ticks, float age, float yaw, float pitch, float scale) {
        if(!en.isInvisible()) {
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.enableNormalize();
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
            slimeModel.setModelAttributes(renderSlime.getMainModel());
            slimeModel.render(en, swing, amount, age, yaw, pitch, scale);
            GlStateManager.disableBlend();
            GlStateManager.disableNormalize();
        }
    }
}