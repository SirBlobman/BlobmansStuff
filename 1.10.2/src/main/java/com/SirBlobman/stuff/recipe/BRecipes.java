package com.SirBlobman.stuff.recipe;

import com.SirBlobman.stuff.block.BBlocks;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BRecipes {
	public static void furnace() {
		GameRegistry.addSmelting(BBlocks.SLIME_BLOCK_BLUE, new ItemStack(Blocks.SLIME_BLOCK), 100.0F);
	}
}