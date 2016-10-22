package com.SirBlobman.stuff.render.entity;

import com.SirBlobman.stuff.entities.ProjectileSlimeball;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class RenderSlimeball extends Render<ProjectileSlimeball>
{
	public RenderSlimeball(RenderManager rm)
	{
		super(rm);
	}
	
	public RenderSlimeball test()
	{
		return this;
	}
	
	protected ResourceLocation getEntityTexture(ProjectileSlimeball e)
	{
		Item ball = e.getSlimeball();
		return ball.getRegistryName();
	}
}