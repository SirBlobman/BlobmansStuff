package com.SirBlobman.stuff.block.item;

import com.SirBlobman.stuff.block.CustomSlimeBlock;
import com.SirBlobman.stuff.utility.Util;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class CustomSlimeBlockItem extends CustomItemBlock {
	private CustomSlimeBlock block;
	public CustomSlimeBlockItem(CustomSlimeBlock b) {
		super(b);
		this.block = b;
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
	public void addInformation(ItemStack is, World w, List<String> list, ITooltipFlag itf) {
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