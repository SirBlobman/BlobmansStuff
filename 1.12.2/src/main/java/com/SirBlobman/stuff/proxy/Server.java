package com.SirBlobman.stuff.proxy;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class Server extends Common {
    @Override
    public void pre(FMLPreInitializationEvent e) {
        super.pre(e);
    }
    
    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);
    }
    
    @Override
    public void post(FMLPostInitializationEvent e) {
        super.post(e);
    }
    
    @Override
    public void items(IForgeRegistry<Item> ifr) {
        super.items(ifr);
    }
    
    @Override
    public void blocks(IForgeRegistry<Block> ifr) {
        super.blocks(ifr);
    }
}