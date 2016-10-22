package com.SirBlobman.stuff;

import com.SirBlobman.stuff.blocks.BBlocks;
import com.SirBlobman.stuff.entities.BEntities;
import com.SirBlobman.stuff.entities.CustomSpawner;
import com.SirBlobman.stuff.entities.ExplosionCanceller;
import com.SirBlobman.stuff.items.BItems;
import com.SirBlobman.stuff.recipes.BRecipes;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class Common 
{
	public void preInit(FMLPreInitializationEvent e)
	{
		BItems.createItems();
		BBlocks.createBlocks();
		BEntities.createEntitites();
		
		SUtil.regEvents(new ExplosionCanceller());
		SUtil.regEvents(new CustomSpawner());
	}
	
	public void init(FMLInitializationEvent e)
	{
		BRecipes.createRecipes();
	}
}