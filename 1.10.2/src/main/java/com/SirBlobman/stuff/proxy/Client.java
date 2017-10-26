package com.SirBlobman.stuff.proxy;

import com.SirBlobman.stuff.render.BRender;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class Client extends Common {
	@Override
	public void pre(FMLPreInitializationEvent e) {
		super.pre(e);
		BRender.items();
		BRender.blocks();
		BRender.entities();
	}
	
	@Override
	public void init(FMLInitializationEvent e) {
		super.init(e);
	}
}