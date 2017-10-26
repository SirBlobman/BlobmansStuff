package com.SirBlobman.stuff.item;

import com.SirBlobman.stuff.creative.SlimyTabs;
import com.SirBlobman.stuff.utility.Util;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class SirBlobmanArmor extends ItemArmor {
	private final String type;
	public SirBlobmanArmor(String type, int i, EntityEquipmentSlot slot) {
		super(BItems.SIRBLOBMAN, i, slot);
		this.type = type;
		String name = "sirblobman_" + type;
		String reg = "armor/" + name;
		setUnlocalizedName(name);
		setRegistryName(reg);
		setCreativeTab(SlimyTabs.ARMOR);
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack is) {
		String f = Util.format("armor." + type, "SirBlobman");
		String t = f.trim();
		return t;
	}
}