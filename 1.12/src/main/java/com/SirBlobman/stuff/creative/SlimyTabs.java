package com.SirBlobman.stuff.creative;

import com.SirBlobman.stuff.block.BBlocks;
import com.SirBlobman.stuff.item.BItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public final class SlimyTabs {
	public static final CreativeTabs ITEMS = new CreativeTabs("BItems") {
		@Override
		public ItemStack getTabIconItem() {
			ItemStack is = new ItemStack(BItems.SLIMEBALL_BLUE);
			return is;
		}
	};
	
	public static final CreativeTabs BLOCKS = new CreativeTabs("BBlocks") {
		@Override
		public ItemStack getTabIconItem() {
			ItemStack is = new ItemStack(BBlocks.SLIME_BLOCK_BLUE);
			return is;
		}
	};
	
	public static final CreativeTabs ARMOR = new CreativeTabs("BArmor") {
		@Override
		public ItemStack getTabIconItem() {
			ItemStack is = new ItemStack(BItems.BLUE_SLIME_HELMET);
			return is;
		}
	};
	
	public static final CreativeTabs TOOLS = new CreativeTabs("BTools") {
		@Override
		public ItemStack getTabIconItem() {
			ItemStack is = new ItemStack(BItems.SHINY_SWORD);
			return is;
		}
	};
}