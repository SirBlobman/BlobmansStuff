package com.SirBlobman.stuff.proxy;

import com.SirBlobman.stuff.render.BSRendering;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class Client extends Common {
    @Override
    public void pre(FMLPreInitializationEvent e) {
        super.pre(e);
        BSRendering.entities();
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
        BSRendering.items();
        BSRendering.blocks();
    }
    
    @Override
    public void blocks(IForgeRegistry<Block> ifr) {
        super.blocks(ifr);
    }
}