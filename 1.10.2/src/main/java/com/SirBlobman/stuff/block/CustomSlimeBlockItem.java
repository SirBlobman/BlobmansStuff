package com.SirBlobman.stuff.block;

import com.SirBlobman.stuff.utility.Util;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class CustomSlimeBlockItem extends ItemBlock {
	private CustomSlimeBlock block;
	public CustomSlimeBlockItem(CustomSlimeBlock b) {
		super(b);
		this.block = b;
		String name = b.getUnlocalizedName().substring(5);
		ResourceLocation rl = b.getRegistryName();
		setUnlocalizedName(name);
		setRegistryName(rl);
	}
	
	@Override
	public boolean hasEffect(ItemStack is) {
		String color = getColor();
		if(color.equals("shiny")) return true;
		else return false;
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack is) {
		String f = Util.format("tile.slime.name");
		String t = f.trim();
		return t;
	}
	
	@Override
	public void addInformation(ItemStack is, EntityPlayer ep, List<String> list, boolean adv) {
		String color = getColor();
		String c = Util.format("color." + color);
		String f = Util.format("color.color", c);
		String t = f.trim();
		list.add(t);
	}
	
	@Override
	public CustomSlimeBlock getBlock() {return block;}
	
	public String getColor() {
		CustomSlimeBlock csb = getBlock();
		String color = csb.getColor();
		return color;
	}
}