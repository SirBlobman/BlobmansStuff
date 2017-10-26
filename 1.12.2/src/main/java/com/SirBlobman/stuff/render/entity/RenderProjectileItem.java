package com.SirBlobman.stuff.render.entity;

import com.SirBlobman.stuff.entity.custom.EntityProjectileItem;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class RenderProjectileItem extends Render<EntityProjectileItem> {
    private final RenderItem renderItem;
    public RenderProjectileItem(RenderManager rm, RenderItem ri) {
        super(rm);
        this.renderItem = ri;
    }
    
    @Override
    public void doRender(EntityProjectileItem en, double x, double y, double z, float yaw, float ticks) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);
        GlStateManager.enableRescaleNormal();
        GlStateManager.rotate(-renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate((renderManager.options.thirdPersonView == 2 ? -1 : 1) * renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
        bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        if(renderOutlines) {
            GlStateManager.enableColorMaterial();
            GlStateManager.enableOutlineMode(getTeamColor(en));
        }
        
        renderItem.renderItem(getStackToRender(en), TransformType.GROUND);
        if(renderOutlines) {
            GlStateManager.disableOutlineMode();
            GlStateManager.disableColorMaterial();
        }
        
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        super.doRender(en, x, y, z, yaw, ticks);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityProjectileItem epi) {
        ResourceLocation rl = TextureMap.LOCATION_BLOCKS_TEXTURE;
        return rl;
    }
    
    public ItemStack getStackToRender(EntityProjectileItem en) {
        ItemStack is = en.getItem();
        return is;
    }
}