package com.SirBlobman.stuff.proxy;

import com.SirBlobman.stuff.Stuff;
import com.SirBlobman.stuff.block.BBlocks;
import com.SirBlobman.stuff.entity.BEntities;
import com.SirBlobman.stuff.entity.spawning.SpiderSlime;
import com.SirBlobman.stuff.item.BItems;
import com.SirBlobman.stuff.recipe.BRecipes;
import com.SirBlobman.stuff.utility.Util;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class Common {
	public void pre(FMLPreInitializationEvent e) {
		Util.regEvents(Stuff.INSTANCE, new SpiderSlime());
		BItems.items();
		BBlocks.blocks();
		BBlocks.items();
		BEntities.entities();
	}
	
	public void init(FMLInitializationEvent e) {
		BRecipes.furnace();
	}
}