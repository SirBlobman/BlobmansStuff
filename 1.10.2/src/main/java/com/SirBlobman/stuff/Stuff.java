package com.SirBlobman.stuff;

import com.SirBlobman.stuff.config.Config;
import com.SirBlobman.stuff.proxy.Common;

import java.io.File;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(
	modid = Stuff.MODID, name = Stuff.NAME, version = Stuff.VERSION,
	acceptedMinecraftVersions = Stuff.VERSIONS
)
public class Stuff {
	public static final String MODID = "blobman";
	public static final String NAME = "Blobman's Stuff";
	public static final String VERSION = "0.0.12";
	public static final String VERSIONS = "[1.10.2]";
	public static final String PACKAGE = "com.SirBlobman.stuff.";
	public static final String PROXY = PACKAGE + "proxy.";
	public static final String CLIENT = PROXY + "Client";
	public static final String SERVER = PROXY + "Server";
	
	@Instance(MODID)
	public static Stuff INSTANCE = new Stuff();
	public static File FOLDER;
	
	@SidedProxy(clientSide=CLIENT,serverSide=SERVER)
	public static Common proxy;
	
	@EventHandler
	public void pre(FMLPreInitializationEvent e) {
		FOLDER = e.getModConfigurationDirectory();
		Config.load();
		proxy.pre(e);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent e) {proxy.init(e);}
}