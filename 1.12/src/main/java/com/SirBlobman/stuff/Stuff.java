package com.SirBlobman.stuff;

import com.SirBlobman.stuff.block.BBlocks;
import com.SirBlobman.stuff.config.Config;
import com.SirBlobman.stuff.entity.BEntities;
import com.SirBlobman.stuff.entity.spawning.SpiderSlime;
import com.SirBlobman.stuff.item.BItems;
import com.SirBlobman.stuff.render.BRender;
import com.SirBlobman.stuff.utility.Util;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@Mod(
	modid = Stuff.MODID, name = Stuff.NAME, version = Stuff.VERSION,
	acceptedMinecraftVersions = Stuff.VERSIONS
)
public class Stuff {
	public static final String MODID = "blobman";
	public static final String NAME = "Blobman's Stuff";
	public static final String VERSION = "0.0.10";
	public static final String VERSIONS = "[1.12]";
	public static final String PACKAGE = "com.SirBlobman.stuff.";
	public static final String PROXY = PACKAGE + "proxy.";
	public static final String CLIENT = PROXY + "Client";
	public static final String SERVER = PROXY + "Server";
	
	@Instance(MODID)
	public static Stuff INSTANCE = new Stuff();
	public static File FOLDER;
	
	@EventHandler
	public void pre(FMLPreInitializationEvent e) {
		FOLDER = e.getModConfigurationDirectory();
		Config.load();
		Util.regEvents(this);
		Util.regEvents(new SpiderSlime());
		BEntities.entities();
	}
	
	@SubscribeEvent
	public void blocks(Register<Block> e) {
		IForgeRegistry<Block> ifr = e.getRegistry();
		BBlocks.blocks(ifr);
	}
	
	@SubscribeEvent
	public void items(Register<Item> e) {
		IForgeRegistry<Item> ifr = e.getRegistry();
		BItems.items(ifr);
		BBlocks.items(ifr);
		BRender.render();
	}
}