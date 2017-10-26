package com.SirBlobman.stuff.item.custom;

import com.SirBlobman.stuff.BSTabs;

import net.minecraft.item.Item;

public class QuickItem extends Item {
    public QuickItem(String name) {
        super();
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(BSTabs.ITEMS);
    }
}