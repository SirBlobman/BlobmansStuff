package com.SirBlobman.stuff.creative;

import com.SirBlobman.stuff.block.BBlocks;
import com.SirBlobman.stuff.item.BItems;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public final class SlimyTabs {
	public static final CreativeTabs ITEMS = new CreativeTabs("BItems") {
		@Override
		public Item getTabIconItem() {
			Item i = BItems.SLIMEBALL_BLUE;
			return i;
		}
	};
	
	public static final CreativeTabs BLOCKS = new CreativeTabs("BBlocks") {
		@Override
		public Item getTabIconItem() {
			Block b = BBlocks.SLIME_BLOCK_BLUE;
			Item i = Item.getItemFromBlock(b);
			return i;
		}
	};
	
	public static final CreativeTabs ARMOR = new CreativeTabs("BArmor") {
		@Override
		public Item getTabIconItem() {
			Item i = BItems.BLUE_SLIME_HELMET;
			return i;
		}
	};
	
	public static final CreativeTabs TOOLS = new CreativeTabs("BTools") {
		@Override
		public Item getTabIconItem() {
			Item i = BItems.SHINY_SWORD;
			return i;
		}
	};
}