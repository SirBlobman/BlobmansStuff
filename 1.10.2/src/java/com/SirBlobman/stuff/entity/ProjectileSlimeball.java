package com.SirBlobman.stuff.entity;

import com.SirBlobman.stuff.item.BItems;
import com.SirBlobman.stuff.item.Slimeball;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IThrowableEntity;

public class ProjectileSlimeball extends EntityThrowable implements IThrowableEntity
{
	private Item slimeball;
	public ProjectileSlimeball(World w, Item ball)
	{
		super(w);
		this.slimeball = ball;
	}
	
	public ProjectileSlimeball(World w)
	{
		this(w, Items.SLIME_BALL);
	}
	
	@Override
	public void onImpact(RayTraceResult trace)
	{
		Entity e = trace.entityHit;
		if(e == null) return;
		if(e instanceof EntityLiving)
		{
			int i = 0;
			EntityLiving el = (EntityLiving) e;
			if(slimeball.equals(Items.SLIME_BALL))
			{
				i = 5;
				EntityDamageSourceIndirect SLIMEBALL = new EntityDamageSourceIndirect("stuff.slimeball", getThrower(), el);
				el.attackEntityFrom(SLIMEBALL, i);
				PotionEffect slow1 = new PotionEffect(MobEffects.SLOWNESS, 20, 0);
				el.addPotionEffect(slow1);
			}
			if(slimeball instanceof Slimeball)
			{
				if(slimeball.equals(BItems.blueSlimeball))
				{
					i = 18;
					EntityDamageSourceIndirect SLIMEBALL = new EntityDamageSourceIndirect("stuff.slimeball", getThrower(), el);
					el.attackEntityFrom(SLIMEBALL, i);
					PotionEffect slow2 = new PotionEffect(MobEffects.SLOWNESS, 20, 1);
					el.addPotionEffect(slow2);
				}
			}
		}
	}
	
	public Item getSlimeball()
	{
		return slimeball;
	}

	@Override
	public void setThrower(Entity entity)
	{
		
	}
}