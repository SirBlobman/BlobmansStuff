package com.SirBlobman.stuff.proxy;

import com.SirBlobman.stuff.render.BRender;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class Client extends Common {
	@Override
	public void pre(FMLPreInitializationEvent e) {
		super.pre(e);
		BRender.entities();
	}
	
	@Override
	public void init(FMLInitializationEvent e) {
		super.init(e);
	}
	
	@Override
	public void items(Register<Item> e) {
		super.items(e);
		BRender.items();
		BRender.blocks();
	}
}