package com.SirBlobman.stuff.items;

import java.util.List;

import com.SirBlobman.stuff.creative.tabs.SlimyTabs;
import com.SirBlobman.stuff.entities.ProjectileSlimeball;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class SlimeWand extends Item
{
	private int level;
	public SlimeWand(int level)
	{
		this.level = level;
		maxStackSize = 1;
		setCreativeTab(SlimyTabs.Items);
		String name = "slime_wand";
		setUnlocalizedName(name);
		setRegistryName(name + level);
	}
	
	public int getLevel() {return level;}
	
	@Override
	public void addInformation(ItemStack is, EntityPlayer ep, List<String> lore, boolean b)
	{
		int level = getLevel();
		boolean b1 = level == 1;
		Item slime = b1 ? Items.SLIME_BALL : BItems.blueSlimeball;
		
		lore.add("Level: \u00a7e" + level);
		lore.add("Slimeball: \u00a7f" + slime.getItemStackDisplayName(is));
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack is, World w, EntityPlayer ep, EnumHand eh)
	{
		ActionResult<ItemStack> success = new ActionResult<ItemStack>(EnumActionResult.SUCCESS, is);
		ActionResult<ItemStack> failure = new ActionResult<ItemStack>(EnumActionResult.FAIL, is);
		
		boolean b1 = ep.capabilities.isCreativeMode;
		if(!b1)
		{
			InventoryPlayer ip = ep.inventory;
			int snowballs = ip.clearMatchingItems(Items.SNOWBALL, -1, 1, null);
			if(snowballs == 0) return failure;
		}
		
		double x = ep.posX;
		double y = ep.posY;
		double z = ep.posZ;
		SoundEvent slime = SoundEvents.BLOCK_SLIME_BREAK;
		SoundCategory neutral = SoundCategory.NEUTRAL;
		w.playSound(null, x, y, z, slime, neutral, 1.0F, 1.0F);
		slime(ep, w, getLevel());
		StatBase sb = StatList.getObjectUseStats(this);
		ep.addStat(sb);
		return success;
	}
	
	private void slime(EntityPlayer ep, World w, int level)
	{
		if(!w.isRemote)
		{
			boolean b1 = level == 1;
			Item slime = b1 ? Items.SLIME_BALL : BItems.blueSlimeball;
			ProjectileSlimeball ps = new ProjectileSlimeball(w, slime);
			ps.setHeadingFromThrower(ep, ep.rotationPitch, ep.rotationYaw, 0.0F, 1.5F, 0.0F);
			w.spawnEntityInWorld(ps);
		}
	}
}