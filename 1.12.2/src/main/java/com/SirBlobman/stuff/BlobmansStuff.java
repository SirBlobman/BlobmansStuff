package com.SirBlobman.stuff;

import com.SirBlobman.stuff.proxy.Common;
import com.SirBlobman.stuff.utility.Util;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber(modid=BlobmansStuff.MODID)
@Mod(modid = BlobmansStuff.MODID, name = BlobmansStuff.NAME, version = BlobmansStuff.VERSION, acceptedMinecraftVersions = BlobmansStuff.ACCEPTED_VERSIONS)
public class BlobmansStuff {
    public static final String MODID = "blobman";
    public static final String NAME = "Blobman's Stuff";
    public static final String VERSION = "1.0.0";
    public static final String ACCEPTED_VERSIONS = "[1.12.2]";
    
    public static final String PACKAGE = "com.SirBlobman.stuff";
    public static final String PROXY = PACKAGE + ".proxy";
    public static final String CLIENT_SIDE = PROXY + ".Client";
    public static final String SERVER_SIDE = PROXY + ".Server";
    
    @Instance
    public static BlobmansStuff INSTANCE = new BlobmansStuff();
    
    @SidedProxy(clientSide=CLIENT_SIDE, serverSide=SERVER_SIDE)
    public static Common proxy;
    
    public static File FOLDER;
    
    @EventHandler
    public void pre(FMLPreInitializationEvent e) {
        FOLDER = e.getModConfigurationDirectory();
        BSConfig.load();
        Util.regEvents(this);
        proxy.pre(e);
    }
    
    @EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }
    
    @EventHandler
    public void post(FMLPostInitializationEvent e) {
        proxy.post(e);
    }
    
    @SubscribeEvent
    public void items(Register<Item> e) {
        IForgeRegistry<Item> ifr = e.getRegistry();
        proxy.items(ifr);
    }
    
    @SubscribeEvent
    public void blocks(Register<Block> e) {
        IForgeRegistry<Block> ifr = e.getRegistry();
        proxy.blocks(ifr);
    }
}