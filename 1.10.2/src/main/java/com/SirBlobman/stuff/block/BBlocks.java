package com.SirBlobman.stuff.block;

import com.SirBlobman.stuff.item.BItems;
import com.SirBlobman.stuff.utility.Util;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class BBlocks {
	public static final CustomSlimeBlock SLIME_BLOCK_BLUE = new CustomSlimeBlock("blue", 1.5D);
	public static final CustomSlimeBlock SLIME_BLOCK_YELLOW = new CustomSlimeBlock("yellow", 2.0D);
	public static final CustomSlimeBlock SLIME_BLOCK_RED = new CustomSlimeBlock("red", 3.0D);
	public static final CustomSlimeBlock SLIME_BLOCK_SHINY = new CustomSlimeBlock("shiny", 10.0D);
	public static final CustomSlimeBlock SLIME_BLOCK_BLACK = new CustomSlimeBlock("black", 100.0D);
	
	public static void blocks() {
		Util.print("Registering Blocks...");
		regAll(
			SLIME_BLOCK_BLUE, SLIME_BLOCK_YELLOW, SLIME_BLOCK_RED,
			SLIME_BLOCK_SHINY, SLIME_BLOCK_BLACK
		);
	}
	
	public static void items() {
		Util.print("Registering ItemBlocks....");
		CustomSlimeBlockItem blue = new CustomSlimeBlockItem(SLIME_BLOCK_BLUE);
		CustomSlimeBlockItem yellow = new CustomSlimeBlockItem(SLIME_BLOCK_YELLOW);
		CustomSlimeBlockItem red = new CustomSlimeBlockItem(SLIME_BLOCK_RED);
		CustomSlimeBlockItem shiny = new CustomSlimeBlockItem(SLIME_BLOCK_SHINY);
		CustomSlimeBlockItem black = new CustomSlimeBlockItem(SLIME_BLOCK_BLACK);
		BItems.regAll(
			blue, yellow, red,
			shiny, black
		);
	}
	
	public static void regAll(Block... bb) {
		for(Block b : bb) {
			GameRegistry.register(b);
		}
	}
}