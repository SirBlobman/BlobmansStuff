package com.SirBlobman.stuff.render.entity.layer;

import com.SirBlobman.stuff.entity.custom.EntityCreeperSlime;
import com.SirBlobman.stuff.render.entity.RenderCreeperSlime;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;

public class LayerCreeperSlimeCharge implements LayerRenderer<EntityCreeperSlime> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("minecraft", "textures/entity/creeper/creeper_armor.png");
    private final RenderCreeperSlime renderSlime;
    private final ModelCreeper creeperModel = new ModelCreeper(2.0F);
    
    public LayerCreeperSlimeCharge(RenderCreeperSlime rcs) {this.renderSlime = rcs;}
    public boolean shouldCombineTextures() {return false;}
    
    @Override
    public void doRenderLayer(EntityCreeperSlime en, float swing, float amount, float ticks, float age, float yaw, float pitch, float scale) {
        if(en.getPowered()) {
            boolean flag = en.isInvisible();
            GlStateManager.depthMask(!flag);
            renderSlime.bindTexture(TEXTURE);
            GlStateManager.matrixMode(5890);
            GlStateManager.loadIdentity();
            float f = (en.ticksExisted + ticks);
            GlStateManager.translate(f * 0.01F, f * 0.01F, 0.0F);
            GlStateManager.matrixMode(5888);
            GlStateManager.enableBlend();
            GlStateManager.color(0.5F, 0.5F, 0.5F, 1.0F);
            GlStateManager.disableLighting();
            GlStateManager.blendFunc(SourceFactor.ONE, DestFactor.ONE);
            creeperModel.setModelAttributes(renderSlime.getMainModel());
            Minecraft.getMinecraft().entityRenderer.setupFogColor(true);
            creeperModel.render(en, swing, amount, age, yaw, pitch, scale);
            Minecraft.getMinecraft().entityRenderer.setupFogColor(false);
            GlStateManager.matrixMode(5890);
            GlStateManager.loadIdentity();
            GlStateManager.matrixMode(5888);
            GlStateManager.enableLighting();
            GlStateManager.disableBlend();
            GlStateManager.depthMask(flag);
        }
    }
}