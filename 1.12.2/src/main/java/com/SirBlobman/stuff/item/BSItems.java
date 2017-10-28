package com.SirBlobman.stuff.item;

import com.SirBlobman.stuff.BlobmansStuff;
import com.SirBlobman.stuff.item.custom.*;

import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.registries.IForgeRegistry;

public final class BSItems {
    public static final ArmorMaterial BLUE_SLIME_ARMOR = EnumHelper.addArmorMaterial("BLUE_SLIME", BlobmansStuff.MODID + ":blue_slime", 100, new int[] {5, 5, 5, 5}, 30, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 2.0F);
    
    public static final ItemCustomSlimeball
        SLIMEBALL_BLUE      = new ItemCustomSlimeball("blue"),
        SLIMEBALL_YELLOW    = new ItemCustomSlimeball("yellow"),
        SLIMEBALL_RED       = new ItemCustomSlimeball("red"),
        SLIMEBALL_SHINY     = new ItemCustomSlimeball("shiny"),
        SLIMEBALL_BLACK     = new ItemCustomSlimeball("black");
    
    public static final ItemSlimeWand SLIME_WAND = new ItemSlimeWand();
    public static final QuickItem WAND_STICK = new QuickItem("wand_stick");
    
    public static final ItemSirBlobmanArmor
        SIRBLOBMAN_CROWN    = new ItemSirBlobmanArmor(EntityEquipmentSlot.HEAD),
        SIRBLOBMAN_SUIT     = new ItemSirBlobmanArmor(EntityEquipmentSlot.CHEST),
        SIRBLOBMAN_PANTS    = new ItemSirBlobmanArmor(EntityEquipmentSlot.LEGS),
        SIRBLOBMAN_SHOES    = new ItemSirBlobmanArmor(EntityEquipmentSlot.FEET);
    
    public static final ItemSlimeArmor
        BLUE_SLIME_HELMET        = new ItemSlimeArmor("blue", BLUE_SLIME_ARMOR, EntityEquipmentSlot.HEAD, new ItemStack(SLIMEBALL_BLUE)),
        BLUE_SLIME_CHESTPLATE    = new ItemSlimeArmor("blue", BLUE_SLIME_ARMOR, EntityEquipmentSlot.CHEST, new ItemStack(SLIMEBALL_BLUE)),
        BLUE_SLIME_LEGGINGS      = new ItemSlimeArmor("blue", BLUE_SLIME_ARMOR, EntityEquipmentSlot.LEGS, new ItemStack(SLIMEBALL_BLUE)),
        BLUE_SLIME_BOOTS         = new ItemSlimeArmor("blue", BLUE_SLIME_ARMOR, EntityEquipmentSlot.FEET, new ItemStack(SLIMEBALL_BLUE));
    
    public static final ItemShinySword
        SHINY_SWORD = new ItemShinySword(false),
        ULTRA_SWORD = new ItemShinySword(true);
    
    public static void register(IForgeRegistry<Item> ifr) {
        ifr.registerAll(SLIMEBALL_BLUE, SLIMEBALL_YELLOW, SLIMEBALL_RED, SLIMEBALL_SHINY, SLIMEBALL_BLACK);
        ifr.registerAll(SLIME_WAND, WAND_STICK);
        ifr.registerAll(SIRBLOBMAN_CROWN, SIRBLOBMAN_SUIT, SIRBLOBMAN_PANTS, SIRBLOBMAN_SHOES);
        ifr.registerAll(BLUE_SLIME_HELMET, BLUE_SLIME_CHESTPLATE, BLUE_SLIME_LEGGINGS, BLUE_SLIME_BOOTS);
        ifr.registerAll(SHINY_SWORD, ULTRA_SWORD);
    }
}