package com.SirBlobman.stuff.render.entity;

import com.SirBlobman.stuff.Stuff;
import com.SirBlobman.stuff.entity.BlueSlime;

import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderBlueSlime extends RenderLiving<BlueSlime> {
	private static final ResourceLocation SLIME_TEXTURES = new ResourceLocation(Stuff.MODID, "textures/entity/slime/blue_slime.png");

	public RenderBlueSlime(RenderManager rm) {
		super(rm, new ModelSlime(16), 0.25F);
		addLayer(new LayerBlueSlimeGel(this));
	}

	public void doRender(BlueSlime entity, double x, double y, double z, float entityYaw, float partialTicks) {
		this.shadowSize = 0.25F * (float) entity.getSlimeSize();
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}
	
	protected void preRenderCallback(BlueSlime entitylivingbaseIn, float partialTickTime) {
		GlStateManager.scale(0.999F, 0.999F, 0.999F);
		float f1 = (float) entitylivingbaseIn.getSlimeSize();
		float f2 = (entitylivingbaseIn.prevSquishFactor + (entitylivingbaseIn.squishFactor - entitylivingbaseIn.prevSquishFactor) * partialTickTime) / (f1 * 0.5F + 1.0F);
		float f3 = 1.0F / (f2 + 1.0F);
		GlStateManager.scale(f3 * f1, 1.0F / f3 * f1, f3 * f1);
	}
	
	protected ResourceLocation getEntityTexture(BlueSlime entity) {return SLIME_TEXTURES;}
}