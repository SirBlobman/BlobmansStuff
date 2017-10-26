package com.SirBlobman.stuff.block.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;

public class CustomItemBlock extends ItemBlock {
	public CustomItemBlock(Block b) {
		super(b);
		String name = b.getUnlocalizedName().substring(5);
		ResourceLocation rl = b.getRegistryName();
		setUnlocalizedName(name);
		setRegistryName(rl);
	}
}