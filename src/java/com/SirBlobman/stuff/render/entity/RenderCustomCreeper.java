package com.SirBlobman.stuff.render.entity;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderCreeper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.util.ResourceLocation;

public class RenderCustomCreeper extends RenderCreeper
{
	public RenderCustomCreeper(RenderManager rm)
	{
		super(rm);
	}
	
	@Override
	protected void preRenderCallback(EntityCreeper entitylivingbaseIn, float partialTickTime)
	{
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.enableNormalize();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
		super.preRenderCallback(entitylivingbaseIn, partialTickTime);
		GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
		GL11.glPopAttrib();
	}
	
	private ResourceLocation TEXTURE;
	
	public RenderCustomCreeper setTexture(ResourceLocation texture)
	{
		TEXTURE = texture;
		return this;
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityCreeper ent)
	{
		return TEXTURE;
	}
}