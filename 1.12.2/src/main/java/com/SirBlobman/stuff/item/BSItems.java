package com.SirBlobman.stuff.item;

import com.SirBlobman.stuff.item.custom.ItemCustomSlimeball;
import com.SirBlobman.stuff.item.custom.ItemSlimeWand;
import com.SirBlobman.stuff.item.custom.QuickItem;

import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public final class BSItems {
    public static final ItemCustomSlimeball
        SLIMEBALL_BLUE      = new ItemCustomSlimeball("blue"),
        SLIMEBALL_YELLOW    = new ItemCustomSlimeball("yellow"),
        SLIMEBALL_RED       = new ItemCustomSlimeball("red"),
        SLIMEBALL_SHINY     = new ItemCustomSlimeball("shiny"),
        SLIMEBALL_BLACK     = new ItemCustomSlimeball("black");
    
    public static final ItemSlimeWand SLIME_WAND = new ItemSlimeWand();
    public static final QuickItem WAND_STICK = new QuickItem("wand_stick");
    
    public static void register(IForgeRegistry<Item> ifr) {
        ifr.registerAll(SLIMEBALL_BLUE, SLIMEBALL_YELLOW, SLIMEBALL_RED, SLIMEBALL_SHINY, SLIMEBALL_BLACK);
        ifr.registerAll(SLIME_WAND, WAND_STICK);
    }
}