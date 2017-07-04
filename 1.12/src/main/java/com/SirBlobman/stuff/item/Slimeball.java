package com.SirBlobman.stuff.item;

import com.SirBlobman.stuff.creative.SlimyTabs;
import com.SirBlobman.stuff.utility.Util;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Slimeball extends Item {
	private final String color;
	public Slimeball(String color) {
		this.color = color;
		String name = color + "_slimeball";
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(SlimyTabs.ITEMS);
	}
	
	@Override
	public boolean hasEffect(ItemStack is) {
		String uname = is.getUnlocalizedName();
		if(uname.contains("shiny")) return true;
		else return false;
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack is) {
		String f = Util.format("item.slimeball.name");
		String t = f.trim();
		return t;
	}
	
	@Override
	public void addInformation(ItemStack is, World w, List<String> list, ITooltipFlag itf) {
		String color = getColor();
		String c = Util.format("color." + color);
		String f = Util.format("color.color", c);
		String t = f.trim();
		list.add(t);
	}
	
	public String getColor() {return color;}
}