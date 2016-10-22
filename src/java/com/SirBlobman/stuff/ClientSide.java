package com.SirBlobman.stuff;

import com.SirBlobman.stuff.render.RenderBlocks;
import com.SirBlobman.stuff.render.RenderEntities;
import com.SirBlobman.stuff.render.RenderItems;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientSide extends Common 
{
	public void preInit(FMLPreInitializationEvent e)
	{
		super.preInit(e);
		RenderEntities.renderEntities();
	}
	
	public void init(FMLInitializationEvent e)
	{
		RenderItems.renderItems();
		RenderBlocks.renderBlocks();
		super.init(e);

	}
}