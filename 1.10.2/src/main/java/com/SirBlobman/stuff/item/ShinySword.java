package com.SirBlobman.stuff.item;

import com.SirBlobman.stuff.creative.SlimyTabs;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class ShinySword extends ItemSword {
	public ShinySword(String type, ToolMaterial mat) {
		super(mat);
		String name = (type == null) ? "shiny_sword" : (type + "_shiny_sword");
		String reg = "tool/" + name;
		setUnlocalizedName(name);
		setRegistryName(reg);
		setCreativeTab(SlimyTabs.TOOLS);
	}
	
	@Override
	public boolean hasEffect(ItemStack is) {return true;}
}