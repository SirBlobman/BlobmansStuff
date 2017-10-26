package com.SirBlobman.stuff.entity.custom;

import com.SirBlobman.stuff.entity.BSEntities.BSLootTables;

import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

public class EntityBlueSlime extends EntitySlime {
    public EntityBlueSlime(World world) {super(world);}
    public EntityBlueSlime createInstance() {return new EntityBlueSlime(world);}
    
    public EnumParticleTypes getParticleType() {return EnumParticleTypes.WATER_SPLASH;}
    public void fall(float f, float g) {}
    
    @Override
    public ResourceLocation getLootTable() {
        boolean small = isSmallSlime();
        if(small) return BSLootTables.BLUE_SLIME;
        else return LootTableList.EMPTY;
    }
    
    @Override
    public void jump() {
        motionY = 1.0D;
        isAirBorne = true;
    }
}