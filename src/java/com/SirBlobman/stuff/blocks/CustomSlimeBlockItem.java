package com.SirBlobman.stuff.blocks;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class CustomSlimeBlockItem extends ItemBlock
{
	final CustomSlimeBlock SLIME_BLOCK;
	
	public CustomSlimeBlockItem(CustomSlimeBlock csb)
	{
		super(csb);
		SLIME_BLOCK = csb;
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack is)
	{
		String format = I18n.format("tile.custom_slime_block.name", I18n.format("color." + SLIME_BLOCK.getColor()));
		return format.trim();
	}
}