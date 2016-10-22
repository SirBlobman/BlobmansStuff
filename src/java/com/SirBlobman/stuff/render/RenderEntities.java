package com.SirBlobman.stuff.render;

import com.SirBlobman.stuff.Stuff;
import com.SirBlobman.stuff.entities.BlueSlime;
import com.SirBlobman.stuff.entities.CreeperSlime;
import com.SirBlobman.stuff.entities.ProjectileSlimeball;
import com.SirBlobman.stuff.render.entity.RenderCustomCreeper;
import com.SirBlobman.stuff.render.entity.RenderCustomSlime;
import com.SirBlobman.stuff.render.entity.RenderSlimeball;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderEntities
{
	public static void renderEntities()
	{
		regS(BlueSlime.class, new ResourceLocation(Stuff.MODID, "textures/entity/slimes/blue_slime.png"));
		regC(CreeperSlime.class, new ResourceLocation(Stuff.MODID, "textures/entity/slimes/creeper_slime.png"));
		regP(ProjectileSlimeball.class);
	}
	
	private static void regP(Class<? extends ProjectileSlimeball> ball)
	{
		RenderingRegistry.registerEntityRenderingHandler(ball, new IRenderFactory<ProjectileSlimeball>()
		{
			@Override
			public Render<? super ProjectileSlimeball> createRenderFor(RenderManager manager)
			{
				return new RenderSlimeball(manager);
			}
		});
	}
	
	private static void regS(Class<? extends EntitySlime> slime, ResourceLocation texture)
	{
		RenderingRegistry.registerEntityRenderingHandler(slime, new IRenderFactory<EntitySlime>()
		{
			@Override
			public Render<? super EntitySlime> createRenderFor(RenderManager rm)
			{
				return new RenderCustomSlime(rm).setTexture(texture);
			}
		});
	}
	
	private static void regC(Class<? extends EntityCreeper> creeper, ResourceLocation texture)
	{
		RenderingRegistry.registerEntityRenderingHandler(creeper, new IRenderFactory<EntityCreeper>()
		{
			@Override
			public Render<? super EntityCreeper> createRenderFor(RenderManager rm)
			{
				return new RenderCustomCreeper(rm).setTexture(texture);
			}
		});
	}
}