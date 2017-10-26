package com.SirBlobman.stuff;

import com.SirBlobman.stuff.block.BSBlocks;
import com.SirBlobman.stuff.item.BSItems;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public final class BSTabs {
    public static final CreativeTabs
    ITEMS = new CreativeTabs("blobman.items") {
        @Override
        public ItemStack getTabIconItem() {
            Item item = BSItems.SLIMEBALL_BLUE;
            ItemStack is = new ItemStack(item, 1);
            return is;
        }
    },
    
    BLOCKS = new CreativeTabs("blobman.blocks") {
        @Override
        public ItemStack getTabIconItem() {
            Block block = BSBlocks.SLIME_BLOCK_BLUE;
            ItemStack is = new ItemStack(block, 1);
            return is;
        }
    };
}