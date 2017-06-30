package com.SirBlobman.stuff.render.entity;

import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSlime;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCustomSlime extends RenderSlime
{
	public RenderCustomSlime(RenderManager rm)
	{
		super(rm, new ModelSlime(1), 0.05F);
	}
	
	private static ResourceLocation TEXTURE;
	
	public RenderCustomSlime setTexture(ResourceLocation texture)
	{
		TEXTURE = texture;
		return this;
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntitySlime ent)
	{
		return TEXTURE;
	}
}