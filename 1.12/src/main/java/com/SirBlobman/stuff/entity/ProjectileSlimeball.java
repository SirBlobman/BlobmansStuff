package com.SirBlobman.stuff.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public abstract class ProjectileSlimeball extends EntitySnowball {
	private final float damage;
	public ProjectileSlimeball(World w) {super(w); this.damage = 0.0F;}
	public ProjectileSlimeball(World w, EntityLivingBase eb, float damage) {super(w, eb); this.damage = damage;}
	
	@Override
	public void onImpact(RayTraceResult ray) {
		Entity e = ray.entityHit;
		if(e != null) {
			if(e instanceof EntityLivingBase) {
				EntityLivingBase el = (EntityLivingBase) e;
				if(!world.isRemote) {
					PotionEffect pe = new PotionEffect(MobEffects.SLOWNESS, 400, 0);
					float f = damage;
					EntityDamageSourceIndirect dam = new EntityDamageSourceIndirect("stuff.slimeball", getThrower(), el);
					el.attackEntityFrom(dam, f);
					el.addPotionEffect(pe);
				}
			}
		}
	}
}