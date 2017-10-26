package com.SirBlobman.stuff.proxy;

import com.SirBlobman.stuff.Stuff;
import com.SirBlobman.stuff.entity.BEntities;
import com.SirBlobman.stuff.block.BBlocks;
import com.SirBlobman.stuff.entity.spawning.SpiderSlime;
import com.SirBlobman.stuff.item.BItems;
import com.SirBlobman.stuff.recipe.BRecipes;
import com.SirBlobman.stuff.utility.Util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class Common {
	public void pre(FMLPreInitializationEvent e) {
		Util.regEvents(Stuff.INSTANCE, new SpiderSlime());
		BEntities.entities();
	}
	
	public void init(FMLInitializationEvent e) {
		BRecipes.furnace();
	}
	
	public void blocks(Register<Block> e) {
		IForgeRegistry<Block> ifr = e.getRegistry();
		BBlocks.blocks(ifr);
	}
	
	public void items(Register<Item> e) {
		IForgeRegistry<Item> ifr = e.getRegistry();
		BItems.items(ifr);
		BBlocks.items(ifr);
	}
}