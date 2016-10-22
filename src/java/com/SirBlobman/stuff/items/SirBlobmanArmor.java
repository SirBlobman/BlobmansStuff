package com.SirBlobman.stuff.items;

import com.SirBlobman.stuff.creative.tabs.SlimyTabs;

import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class SirBlobmanArmor extends ItemArmor 
{
	String type;
	
	public SirBlobmanArmor(String type, ArmorMaterial mat, int i, EntityEquipmentSlot slot)
	{
		super(mat, i, slot);
		setUnlocalizedName("sirblobman_" + type);
		setRegistryName("armor/sirblobman_" + type);
		setCreativeTab(SlimyTabs.Armor);
		this.type = type;
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack is)
	{
		String format = I18n.format("armor." + type, "SirBlobman");
		return format.trim();
	}
}
