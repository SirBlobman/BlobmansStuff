package com.SirBlobman.stuff.entity;

import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.init.SoundEvents;
import net.minecraft.world.World;

@SuppressWarnings("unused")
public class CustomCreeper extends EntityCreeper
{
	private int lastActiveTime;
	private int timeSinceIgnited;
	private int fuseTime = 30;
	private int explosionRadius = 3;
	private int droppedSkulls;
	
	public CustomCreeper(World w)
	{
		super(w);
	}
	
	public void fall(float f1, float f2) {}
	
	@Override
	public void onUpdate()
	{
		if(isEntityAlive())
		{
			lastActiveTime = timeSinceIgnited;
			if(hasIgnited()) setCreeperState(1);
			
			int i = getCreeperState();
			if(i > 0 && timeSinceIgnited == 0) playSound(SoundEvents.ENTITY_CREEPER_PRIMED, 1.0F, 0.5F);
			
			timeSinceIgnited += i;
			if(timeSinceIgnited < 0) timeSinceIgnited = 0;
			
			if(timeSinceIgnited >= fuseTime)
			{
				timeSinceIgnited = fuseTime;
				explode();
			}
		}
		super.onUpdate();
	}
	
	public void explode() {};
}