package com.SirBlobman.stuff.entity;

import com.SirBlobman.stuff.Stuff;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CustomSpawner 
{
	@SubscribeEvent
	public void onSpiderspawn(EntityJoinWorldEvent e)
	{
		World w = e.getWorld();
		if(!w.isRemote)
		{
			double chance = Math.random();
			if(chance <= Stuff.slimeJockeyChance)
			{
				Entity ent = e.getEntity();
				if(ent.getClass().equals(EntitySpider.class))
				{
					EntitySpider es = (EntitySpider) ent;
					BlueSlime bs = new BlueSlime(w);
					bs.setLocationAndAngles(es.posX, es.posY, es.posZ, es.rotationYaw, es.rotationPitch);
					w.spawnEntityInWorld(bs);
					es.startRiding(bs, true);
					bs.updateRidden();
				}
			}
		}
	}
}