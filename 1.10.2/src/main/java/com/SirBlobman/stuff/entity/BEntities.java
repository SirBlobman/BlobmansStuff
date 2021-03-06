package com.SirBlobman.stuff.entity;

import com.SirBlobman.stuff.Stuff;
import com.SirBlobman.stuff.config.Config;
import com.SirBlobman.stuff.utility.Util;

import java.awt.Color;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public final class BEntities {
	public static final ResourceLocation BLUE_SLIME_LOOT_LOCATION = new ResourceLocation(Stuff.MODID, "entities/blue_slime");
	public static final ResourceLocation BLUE_SLIME_LOOT = LootTableList.register(BLUE_SLIME_LOOT_LOCATION);
	public static final int LIGHT_BLUE = 38143;
	public static final int BLUE = Color.blue.getRGB();
	public static final int GREEN = Color.green.getRGB();
	
	public static void entities() {
		Util.print("Registering Entities...");
		regLiving(BlueSlime.class, "blue_slime", BLUE, LIGHT_BLUE, Config.ID_BLUE_SLIME, EnumCreatureType.MONSTER, Util.getBiomes("plains"));
		regLiving(CreeperSlime.class, "creeper_slime", GREEN, LIGHT_BLUE, Config.ID_CREEPER_SLIME, EnumCreatureType.MONSTER, Util.getBiomes("forest"));
		regNoEgg(ProjectileBlueSlimeball.class, "blue_slimeball", Config.ID_BLUE_SLIME_BALL);
		regNoEgg(ProjectileGreenSlimeball.class, "blue_slimeball", Config.ID_GREEN_SLIME_BALL);
	}
	
	private static void reg(Class<? extends Entity> clazz, String name, int c1, int c2, int id) {
		Stuff stuff = Stuff.INSTANCE;
		int range = 64;
		int freq = 5;
		EntityRegistry.registerModEntity(clazz, name, id, stuff, range, freq, true, c1, c2);
	}
	
	private static void regLiving(Class<? extends EntityLiving> clazz, String name, int c1, int c2, int id, EnumCreatureType type, Biome... bs) {
		reg(clazz, name, c1, c2, id);
		EntityRegistry.addSpawn(clazz, 20, 1, 2, type, bs);
	}
	
	private static void regNoEgg(Class<? extends Entity> clazz, String name, int id) {
		Stuff stuff = Stuff.INSTANCE;
		int range = 64;
		int freq = 1;
		EntityRegistry.registerModEntity(clazz, name, id, stuff, range, freq, true);
	}
}