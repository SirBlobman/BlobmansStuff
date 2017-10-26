package com.SirBlobman.stuff.block.custom;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;

public class QuickItemBlock extends ItemBlock {
    public QuickItemBlock(Block block) {
        super(block);
        ResourceLocation rl = block.getRegistryName();
        setRegistryName(rl);
        setUnlocalizedName(rl.getResourcePath());
    }
}