package com.SirBlobman.stuff.items;

import com.SirBlobman.stuff.creative.tabs.SlimyTabs;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ShinySword extends ItemSword 
{
	public ShinySword(String type, ToolMaterial mat) 
	{
		super(mat);
		setUnlocalizedName(type + "shiny_sword");
		setRegistryName("tool/" + type.toLowerCase() + "shiny_sword");
		setCreativeTab(SlimyTabs.Tools);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public boolean hasEffect(ItemStack is)
	{
		return true;
	}
}