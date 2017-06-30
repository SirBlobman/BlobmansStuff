package com.SirBlobman.stuff.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

public class BlueSlime extends EntitySlime
{
	public BlueSlime(World w)
	{
		super(w);
	}
	
	public static void registerFixesSlime(DataFixer df)
	{
		EntityLiving.registerFixesMob(df, "Blue Slime");
	}
	
	protected EnumParticleTypes getParticleType()
	{
		return EnumParticleTypes.WATER_SPLASH;
	}
	
	protected BlueSlime createInstance()
	{
		return new BlueSlime(this.worldObj);
	}
	
	protected ResourceLocation getLootTable()
	{
		return this.isSmallSlime() ? BEntities.BlueSlimeLoot : LootTableList.EMPTY;
	}
	
	@Override
	public void jump()
	{
		this.motionY = 0.41999998688697815 * 2.0;
        this.isAirBorne = true;
	}
	
	/*
	 * Prevents slimes from falling to their death!
	 */
	@Override
	public void fall(float f, float g) {}
}