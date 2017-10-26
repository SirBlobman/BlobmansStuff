package com.SirBlobman.stuff.item.custom;

import net.minecraft.item.ItemStack;

public class ItemCustomSlimeball extends QuickItem {
    private final String color;
    public ItemCustomSlimeball(String color) {
        super(color + "_slime_ball");
        this.color = color;
    }
    
    public String getColor() {return color.toLowerCase();}
    
    @Override
    public boolean hasEffect(ItemStack is) {
        String color = getColor();
        if(color.equals("shiny")) return true;
        else return super.hasEffect(is);
    }
}