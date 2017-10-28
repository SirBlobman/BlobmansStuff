package com.SirBlobman.stuff.item.custom;

import com.SirBlobman.stuff.BSTabs;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;

public class ItemShinySword extends ItemSword {
    private static final ToolMaterial SHINY = EnumHelper.addToolMaterial("SHINY", 50, 1000000, 80.0F, 96.0F, 100);
    private static final ToolMaterial ULTRA_SHINY = EnumHelper.addToolMaterial("ULTRA_SHINY", 50, Integer.MAX_VALUE, Float.MAX_VALUE, Float.POSITIVE_INFINITY, Integer.MAX_VALUE);
    
    public ItemShinySword(boolean ultra) {
        super(ultra ? ULTRA_SHINY : SHINY);
        String name = (ultra ? "ultra_shiny_sword" : "shiny_sword");
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(BSTabs.ITEMS);
    }
    
    public boolean hasEffect(ItemStack is) {return true;}
}