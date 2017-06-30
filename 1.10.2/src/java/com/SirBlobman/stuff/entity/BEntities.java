package com.SirBlobman.stuff.entity;

import java.awt.Color;

import com.SirBlobman.stuff.Stuff;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class BEntities
{
	public static ResourceLocation BlueSlimeLoot = LootTableList.register(new ResourceLocation(Stuff.MODID, "entities/blue_slime"));
	
	public static void createEntitites()
	{
		int lightBlue = 38143;
		r(BlueSlime.class, "blueSlime", Color.blue.getRGB(), lightBlue, Stuff.blueSlimeID);
		r(CreeperSlime.class, "creeperSlime", Color.green.getRGB(), lightBlue, Stuff.creeperSlimeID);
		rNoEgg(ProjectileSlimeball.class, "Slimeball", Stuff.slimeBallID);
	}
	
	private static void r(Class<? extends Entity> c, String name, int eggColor1, int eggColor2, int id)
	{
		EntityRegistry.registerModEntity
		(
			c,
			name,
			id,
			Stuff.instance,
			64,
			5,
			true,
			eggColor1,
			eggColor2
		);
	}
	
	private static void rNoEgg(Class<? extends Entity> c, String name, int id)
	{
		EntityRegistry.registerModEntity
		(
			c,
			name,
			id,
			Stuff.instance,
			64,
			1, 
			true
		);
	}
}
