package com.SirBlobman.stuff.creative.tabs;

import com.SirBlobman.stuff.blocks.BBlocks;
import com.SirBlobman.stuff.items.BItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public final class SlimyTabs 
{
	public static final CreativeTabs Items = new CreativeTabs("BItems")
	{
		@Override
		public Item getTabIconItem()
		{
			return BItems.blueSlimeball;
		}
	};
	
	public static final CreativeTabs Blocks = new CreativeTabs("BBlocks")
	{
		@Override
		public Item getTabIconItem()
		{
			return Item.getItemFromBlock(BBlocks.blueSlime);
		}
	};
	
	public static final CreativeTabs Armor = new CreativeTabs("BArmor")
	{
		@Override
		public Item getTabIconItem()
		{
			return BItems.blueHelmet;
		}
	};
	
	public static final CreativeTabs Tools = new CreativeTabs("BTools")
	{
		@Override
		public Item getTabIconItem()
		{
			return BItems.shinySword;
		}
	};
}
