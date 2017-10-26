package com.SirBlobman.stuff.block;

import com.SirBlobman.stuff.block.custom.BlockCustomSlime;
import com.SirBlobman.stuff.block.custom.QuickItemBlock;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.registries.IForgeRegistry;

public final class BSBlocks {
    public static final BlockCustomSlime
        SLIME_BLOCK_BLUE    = new BlockCustomSlime("blue", 1.5D),
        SLIME_BLOCK_YELLOW  = new BlockCustomSlime("yellow", 2.0D),
        SLIME_BLOCK_RED     = new BlockCustomSlime("red", 3.0D),
        SLIME_BLOCK_SHINY   = new BlockCustomSlime("shiny", 10.0D),
        SLIME_BLOCK_BLACK   = new BlockCustomSlime("black", 100.0D);
    
    public static final ItemBlock
        ITEM_SLIME_BLOCK_BLUE   = new QuickItemBlock(SLIME_BLOCK_BLUE),
        ITEM_SLIME_BLOCK_YELLOW = new QuickItemBlock(SLIME_BLOCK_YELLOW),
        ITEM_SLIME_BLOCK_RED    = new QuickItemBlock(SLIME_BLOCK_RED),
        ITEM_SLIME_BLOCK_SHINY  = new QuickItemBlock(SLIME_BLOCK_SHINY),
        ITEM_SLIME_BLOCK_BLACK  = new QuickItemBlock(SLIME_BLOCK_BLACK);
    
    public static void blocks(IForgeRegistry<Block> ifr) {
        ifr.registerAll(SLIME_BLOCK_BLUE, SLIME_BLOCK_YELLOW, SLIME_BLOCK_RED, SLIME_BLOCK_SHINY, SLIME_BLOCK_BLACK);
    }
    
    public static void items(IForgeRegistry<Item> ifr) {
        ifr.registerAll(ITEM_SLIME_BLOCK_BLUE, ITEM_SLIME_BLOCK_YELLOW, ITEM_SLIME_BLOCK_RED, ITEM_SLIME_BLOCK_SHINY, ITEM_SLIME_BLOCK_BLACK);
    }
}