package com.SirBlobman.stuff.proxy;

import com.SirBlobman.stuff.block.BSBlocks;
import com.SirBlobman.stuff.entity.BSEntities;
import com.SirBlobman.stuff.item.BSItems;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class Common {
    public void pre(FMLPreInitializationEvent e) {
        BSEntities.register();
    }
    
    public void init(FMLInitializationEvent e) {
        
    }
    
    public void post(FMLPostInitializationEvent e) {
        
    }
    
    public void items(IForgeRegistry<Item> ifr) {
        BSItems.register(ifr);
        BSBlocks.items(ifr);
    }
    
    public void blocks(IForgeRegistry<Block> ifr) {
        BSBlocks.blocks(ifr);
    }
}