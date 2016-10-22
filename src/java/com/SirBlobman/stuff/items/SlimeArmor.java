package com.SirBlobman.stuff.items;

import com.SirBlobman.stuff.SUtil;
import com.SirBlobman.stuff.creative.tabs.SlimyTabs;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class SlimeArmor extends ItemArmor 
{
	String color,type;
	ItemStack repair;
	
	public SlimeArmor(String color, String type, ArmorMaterial mat, int i, EntityEquipmentSlot slot, Item repair) 
	{
		super(mat, i, slot);
		this.color = color;
		this.type = type;
		setRegistryName("armor/" + color + "_slime_" + type);
		setUnlocalizedName(color + "_slime_" + type);
		setCreativeTab(SlimyTabs.Armor);
		canRepair = true;
		this.repair = new ItemStack(repair);
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack is)
	{
		String format = I18n.format("armor." + type, I18n.format("color." + color) + I18n.format("type.slime"));
		return format.trim();
	}
	
	@Override
	public boolean getIsRepairable(ItemStack is, ItemStack is2)
	{
		return repair == is2 ? true : super.getIsRepairable(is, is2);
	}
	
	@Override
	public void onArmorTick(World w, EntityPlayer ep, ItemStack is)
	{
		if(SUtil.isWearingSlimeArmor(ep))
		{
			effectPlayer(ep);
		}
	}
	
	private void effectPlayer(EntityPlayer ep)
	{
		PotionEffect jump = new PotionEffect(MobEffects.JUMP_BOOST, 1, 1);
		ep.addPotionEffect(jump);
	}
}