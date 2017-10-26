package com.SirBlobman.stuff.entity;

import com.SirBlobman.stuff.BlobmansStuff;
import com.SirBlobman.stuff.entity.custom.EntityBlueSlime;
import com.SirBlobman.stuff.entity.custom.EntityCreeperSlime;
import com.SirBlobman.stuff.entity.custom.EntityProjectileItem;
import com.SirBlobman.stuff.utility.Util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public final class BSEntities {
    public static final class BSLootTables {
        public static final ResourceLocation BLUE_SLIME = register("blue_slime");
        
        private static ResourceLocation register(String name) {return register("entities", name);}
        private static ResourceLocation register(String path, String name) {
            String modid = BlobmansStuff.MODID;
            String rname = path + "/" + name;
            ResourceLocation rl = new ResourceLocation(modid, rname);
            return rl;
        }
    }
    
    public static final class BSEggColors {
        public static final int
            RED         = rgb(255, 0, 0),
            ORANGE      = rgb(255, 165, 0),
            YELLOW      = rgb(255, 255, 0),
            GREEN       = rgb(0, 255, 0),
            DARK_GREEN  = rgb(0, 128, 0),
            BLUE        = rgb(0, 0, 255),
            INDIGO      = rgb(111, 0, 255),
            VIOLET      = rgb(255, 0, 255),
            
            AQUA        = rgb(0, 255, 255),
            BLACK       = rgb(0, 0, 0),
            WHITE       = rgb(255, 255, 255);
        
        private static int rgb(int red, int green, int blue) {
            int r = (red << 16) & 0x00FF0000;
            int g = (green << 8) & 0x0000FF00;
            int b = (blue) & 0x000000FF;
            int color = (0xFF000000 | r | g | b);
            return color;
        }
    }
    
    public static void register() {
        //Entity Registry
        reg("projectile_item", EntityProjectileItem.class);
        reg("blue_slime", EntityBlueSlime.class, BSEggColors.BLUE, BSEggColors.AQUA);
        reg("creeper_slime", EntityCreeperSlime.class, BSEggColors.GREEN, BSEggColors.AQUA);
        
        //Spawning Registry
        regSpawn(EntityBlueSlime.class, EnumCreatureType.MONSTER, 20, 2, Util.getBiomes("plains"));
        regSpawn(EntityCreeperSlime.class, EnumCreatureType.MONSTER, 20, 2, Util.getBiomes("forest"));
    }
    
    private static int id = 0;
    private static void reg(String name, Class<? extends Entity> clazz) {
        ResourceLocation rl = new ResourceLocation(BlobmansStuff.MODID, name);
        BlobmansStuff bs = BlobmansStuff.INSTANCE;
        EntityRegistry.registerModEntity(rl, clazz, name, id, bs, 64, 1, true);
        id = (id + 1);
    }
    
    private static void reg(String name, Class<? extends Entity> clazz, int egg1, int egg2) {
        reg(name, clazz);
        regEgg(clazz, egg1, egg2);
    }
    
    private static void regEgg(Class<? extends Entity> clazz, int egg1, int egg2) {
        EntityEntry ee = EntityRegistry.getEntry(clazz);
        ResourceLocation rl = ee.getRegistryName();
        EntityRegistry.registerEgg(rl, egg1, egg2);
    }
    
    private static void regSpawn(Class<? extends EntityLiving> clazz, EnumCreatureType type, int weight, int maxGroup, Biome... biomes) {
        EntityRegistry.addSpawn(clazz, weight, 1, maxGroup, type, biomes);
    }
}