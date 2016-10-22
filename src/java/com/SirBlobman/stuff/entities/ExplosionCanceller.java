package com.SirBlobman.stuff.entities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.Explosion;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ExplosionCanceller 
{
	@SubscribeEvent
	public void onExplode(ExplosionEvent.Start e)
	{
		Explosion ex = e.getExplosion();
		EntityLivingBase el = ex.getExplosivePlacedBy();
		if(el instanceof CustomCreeper)
		{
			e.setCanceled(true);
		}
	}
}