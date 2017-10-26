package com.SirBlobman.stuff.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

public class BlueSlime extends EntitySlime {
	public BlueSlime(World w) {super(w);}
	
	protected EnumParticleTypes getParticleType() {return EnumParticleTypes.WATER_SPLASH;}
	protected BlueSlime createInstance() {return new BlueSlime(worldObj);}
	protected ResourceLocation getLootTable() {
		boolean small = isSmallSlime();
		ResourceLocation rl = small ? BEntities.BLUE_SLIME_LOOT : LootTableList.EMPTY;
		return rl;
	}
	
	@Override
	public void jump() {
		motionY = (0.41999998688697815D * 2.0D);
		isAirBorne = true;
	}
	
	public static void registerFixesSlime(DataFixer fixer)
    {
        EntityLiving.registerFixesMob(fixer, "BlueSlime");
    }
	
	@Override
	public void fall(float f, float g) {}
}