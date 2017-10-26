package com.SirBlobman.stuff.entity.spawning;

import com.SirBlobman.stuff.config.Config;
import com.SirBlobman.stuff.entity.BlueSlime;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SpiderSlime {
	@SubscribeEvent
	public void spawn(EntityJoinWorldEvent e) {
		World w = e.getWorld();
		if(!w.isRemote) {
			double chance = (Math.random() * 100.0D);
			if(chance <= Config.BLUE_SLIME_JOCKEY_CHANCE) {
				Entity en = e.getEntity();
				if(en instanceof EntitySpider) {
					EntitySpider es = (EntitySpider) en;
					BlueSlime bs = new BlueSlime(w);
					bs.setLocationAndAngles(es.posX, es.posY, es.posZ, es.rotationYaw, es.rotationPitch);
					w.spawnEntityInWorld(bs);
					bs.startRiding(es, true);
					es.updateRidden();
				}
			}
		}
	}
}