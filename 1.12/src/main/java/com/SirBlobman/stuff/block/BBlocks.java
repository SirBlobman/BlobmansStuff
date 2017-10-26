package com.SirBlobman.stuff.block;

import com.SirBlobman.stuff.block.item.CustomItemBlock;
import com.SirBlobman.stuff.block.item.CustomSlimeBlockItem;
import com.SirBlobman.stuff.utility.Util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public final class BBlocks {
	public static final CustomSlimeBlock SLIME_BLOCK_BLUE = new CustomSlimeBlock("blue", 1.5D);
	public static final CustomSlimeBlock SLIME_BLOCK_YELLOW = new CustomSlimeBlock("yellow", 2.0D);
	public static final CustomSlimeBlock SLIME_BLOCK_RED = new CustomSlimeBlock("red", 3.0D);
	public static final CustomSlimeBlock SLIME_BLOCK_SHINY = new CustomSlimeBlock("shiny", 10.0D);
	public static final CustomSlimeBlock SLIME_BLOCK_BLACK = new CustomSlimeBlock("black", 100.0D);
	public static final CustomTNT BLACK_SLIME_TNT = new CustomTNT();
	
	public static void blocks(IForgeRegistry<Block> ifr) {
		Util.print("Registering Blocks...");
		ifr.registerAll(
			SLIME_BLOCK_BLUE, SLIME_BLOCK_YELLOW, SLIME_BLOCK_RED,
			SLIME_BLOCK_SHINY, SLIME_BLOCK_BLACK, BLACK_SLIME_TNT
		);
	}
	
	public static void items(IForgeRegistry<Item> ifr) {
		Util.print("Registering ItemBlocks....");
		CustomSlimeBlockItem blue = new CustomSlimeBlockItem(SLIME_BLOCK_BLUE);
		CustomSlimeBlockItem yellow = new CustomSlimeBlockItem(SLIME_BLOCK_YELLOW);
		CustomSlimeBlockItem red = new CustomSlimeBlockItem(SLIME_BLOCK_RED);
		CustomSlimeBlockItem shiny = new CustomSlimeBlockItem(SLIME_BLOCK_SHINY);
		CustomSlimeBlockItem black = new CustomSlimeBlockItem(SLIME_BLOCK_BLACK);
		CustomItemBlock black_tnt = new CustomItemBlock(BLACK_SLIME_TNT);
		ifr.registerAll(
			blue, yellow, red,
			shiny, black, black_tnt
		);
	}
}