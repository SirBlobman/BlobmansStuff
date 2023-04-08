package xyz.sirblobman.mod.blobmanstuff.item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import net.minecraft.item.Item;

import xyz.sirblobman.mod.blobmanstuff.BlobmanStuffMod;
import xyz.sirblobman.mod.blobmanstuff.type.SlimeballColor;

import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(BlobmanStuffMod.MOD_ID)
public final class BSItems {
    private static final List<Item> ALL_ITEMS;

    static {
        ALL_ITEMS = new ArrayList<>();
    }

    public static List<Item> getAllItems() {
        return Collections.unmodifiableList(ALL_ITEMS);
    }

    @ObjectHolder("blue_slime_ball") public static Item BLUE_SLIME_BALL;
    @ObjectHolder("blue_slime_ball") public static Item YELLOW_SLIME_BALL;
    @ObjectHolder("blue_slime_ball") public static Item RED_SLIME_BALL;
    @ObjectHolder("blue_slime_ball") public static Item SHINY_SLIME_BALL;
    @ObjectHolder("blue_slime_ball") public static Item BLACK_SLIME_BALL;

    @ObjectHolder("blobman_crown") public static Item BLOBMAN_CROWN;
    @ObjectHolder("blobman_suit") public static Item BLOBMAN_SUIT;
    @ObjectHolder("blobman_pants") public static Item BLOBMAN_PANTS;
    @ObjectHolder("blobman_shoes") public static Item BLOBMAN_SHOES;

    @ObjectHolder("blue_slime_helmet") public static Item BLUE_SLIME_HELMET;
    @ObjectHolder("blue_slime_chestplate") public static Item BLUE_SLIME_CHESTPLATE;
    @ObjectHolder("blue_slime_leggings") public static Item BLUE_SLIME_LEGGINGS;
    @ObjectHolder("blue_slime_boots") public static Item BLUE_SLIME_BOOTS;

    @ObjectHolder("shiny_sword") public static Item SHINY_SWORD;
    @ObjectHolder("ultra_shiny_sword") public static Item ULTRA_SHINY_SWORD;

    @ObjectHolder("slime_wand") public static Item SLIME_WAND;
    @ObjectHolder("wand_stick") public static Item WAND_STICK;

    public static void register(IForgeRegistry<Item> registry) {
        SlimeballColor[] slimeballColors = SlimeballColor.values();
        for (SlimeballColor slimeballColor : slimeballColors) {
            String nameLowercase = slimeballColor.name().toLowerCase(Locale.US);
            String id = (nameLowercase + "_slime_ball");
            createItem(new ItemSlimeball(slimeballColor), id);
        }

        List<Item> allItems = getAllItems();
        for (Item item : allItems) {
            registry.register(item);
        }
    }

    private static void createItem(Item item, String id) {
        item.setRegistryName(BlobmanStuffMod.MOD_ID, id);
        ALL_ITEMS.add(item);
    }
}
