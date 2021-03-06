package com.SirBlobman.stuff.item;

import com.SirBlobman.stuff.creative.SlimyTabs;
import com.SirBlobman.stuff.entity.ProjectileBlueSlimeball;
import com.SirBlobman.stuff.entity.ProjectileGreenSlimeball;
import com.SirBlobman.stuff.entity.ProjectileSlimeball;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class SlimeWand extends Item {
	private final int level;
	public SlimeWand(int level) {
		this.level = level;
		String name = "slime_wand";
		setUnlocalizedName(name);
		setRegistryName(name + level);
		setCreativeTab(SlimyTabs.TOOLS);
		setMaxStackSize(1);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack is, World w, EntityPlayer ep, EnumHand eh) {
		ActionResult<ItemStack> SUCC = new ActionResult<ItemStack>(EnumActionResult.SUCCESS, is);
		double x = ep.posX;
		double y = ep.posY;
		double z = ep.posZ;
		SoundEvent slime = SoundEvents.BLOCK_SLIME_BREAK;
		SoundCategory sc = SoundCategory.HOSTILE;
		w.playSound(null, x, y, z, slime, sc, 1.0F, 1.0F);
		slime(ep, w, getLevel());
		StatBase sb = StatList.getObjectUseStats(this);
		ep.addStat(sb);
		return SUCC;
	}
	
	public int getLevel() {return level;}
	private void slime(EntityPlayer ep, World w, int lvl) {
		if(!w.isRemote) {
			ProjectileSlimeball ps = null;
			if(lvl == 1) ps = new ProjectileGreenSlimeball(w, ep, 5.0F);
			else if(lvl == 2) ps = new ProjectileBlueSlimeball(w, ep, 20.0F);
			else return;
			
            ps.setHeadingFromThrower(ep, ep.rotationPitch, ep.rotationYaw, 0.0F, 1.5F, 0.0F);
			w.spawnEntityInWorld(ps);
		}
	}
}