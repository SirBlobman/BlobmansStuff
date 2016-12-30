package com.SirBlobman.stuff;

import com.SirBlobman.stuff.block.BBlocks;
import com.SirBlobman.stuff.entity.BEntities;
import com.SirBlobman.stuff.entity.CustomSpawner;
import com.SirBlobman.stuff.entity.ExplosionCanceller;
import com.SirBlobman.stuff.item.BItems;
import com.SirBlobman.stuff.recipe.BRecipes;
import com.SirBlobman.stuff.world.BlobDimensions;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class Common 
{
	public void preInit(FMLPreInitializationEvent e)
	{
		BItems.createItems();
		BBlocks.createBlocks();
		BEntities.createEntitites();
		BlobDimensions.init();
		
		SUtil.regEvents(new ExplosionCanceller());
		SUtil.regEvents(new CustomSpawner());
	}
	
	public void init(FMLInitializationEvent e)
	{
		BRecipes.createRecipes();
	}
}