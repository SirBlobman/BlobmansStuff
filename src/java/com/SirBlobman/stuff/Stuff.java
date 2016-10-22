package com.SirBlobman.stuff;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod
(
	modid = Stuff.MODID, 
	name = Stuff.NAME, 
	version = Stuff.VERSION, 
	acceptedMinecraftVersions = Stuff.VERSIONS
)
public class Stuff 
{
	public static final String MODID = "blobmansstuff";
	public static final String NAME = "Blobman's Stuff";
	public static final String VERSION = "0.0.9";
	public static final String VERSIONS = "[1.9.4, 1.10.2]";
	public static final String CLIENT = "com.SirBlobman.stuff.ClientSide";
	public static final String SERVER = "com.SirBlobman.stuff.ServerSide";
	
	@Instance
	public static Stuff instance = new Stuff();
	
	@SidedProxy(clientSide=CLIENT, serverSide=SERVER)
	public static Common proxy;
	
	public static Configuration config;
	
	public static int blueSlimeID;
	public static int creeperSlimeID;
	public static int slimeBallID;
	
	public static int creeperSlimeSpawnAmount;
	public static int creeperSlimeBlueSize;
	
	public static double slimeJockeyChance;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e) 
	{
		config = new Configuration(e.getSuggestedConfigurationFile(), true);
		config.load();
		//Config Categories
		config.addCustomCategoryComment("Entity", "This section allows changing of things related to Entities.\nIf you have an ID conflict, change it here");
		config.addCustomCategoryComment("Entity.Creeper Slime", "This section is for the Creeper Slime Entity.");
		//Config Options
		blueSlimeID = config.getInt("ID", "Entity.Blue Slime", 1, 1, Integer.MAX_VALUE, "");
		creeperSlimeID = config.getInt("ID", "Entity.Creeper Slime", 2, 1, Integer.MAX_VALUE, "");
		slimeBallID = config.getInt("ID", "Entity.Slime Ball", 3, 1, Integer.MAX_VALUE, "");
		creeperSlimeSpawnAmount = config.getInt("Blue Slime Spawn Amount", "Entity.Creeper Slime", 5, 1, 50, "How many Blue Slimes spawn when the Creeper Slime explodes");
		creeperSlimeBlueSize = config.getInt("Blue Slime Size", "Entity.Creeper Slime", 5, 1, 250, "How big the Blue Slimes are");
		slimeJockeyChance = config.getFloat("Slime Jockey Chance", "Entity", 10.0F, 1.0F, 100.0F, "What is the chance that a spider will spawn on top of a blue slime?") / 100.0D;
		
		config.save();
		proxy.preInit(e);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent e) {proxy.init(e);}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {}
}
