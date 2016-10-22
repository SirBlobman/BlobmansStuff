package com.SirBlobman.stuff.items;

import com.SirBlobman.stuff.creative.tabs.SlimyTabs;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Slimeball extends Item
{
	private final String color;
	
	public Slimeball(String color)
	{
		setUnlocalizedName(color + "_slimeball");
		setRegistryName(color + "_slimeball");
		setCreativeTab(SlimyTabs.Items);
		this.color = color;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public boolean hasEffect(ItemStack is)
	{
		if(is.getUnlocalizedName().contains("shiny"))
		{
			return true;
		}
		
		return false;
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack is)
	{
		String format = I18n.format("item.custom_slimeball.name", I18n.format("color." + color));
		return format.trim();
	}
	
	public String getColor()
	{
		return color;
	}
}