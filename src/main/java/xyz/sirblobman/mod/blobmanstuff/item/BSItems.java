package xyz.sirblobman.mod.blobmanstuff.item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import net.minecraft.inventory.EquipmentSlotType;
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
    @ObjectHolder("ultra_sword") public static Item ULTRA_SWORD;

    @ObjectHolder("slime_wand") public static Item SLIME_WAND;
    @ObjectHolder("blue_slime_wand") public static Item BLUE_SLIME_WAND;
    @ObjectHolder("wand_stick") public static Item WAND_STICK;

    public static void register(IForgeRegistry<Item> registry) {
        SlimeballColor[] slimeballColors = SlimeballColor.values();
        for (SlimeballColor slimeballColor : slimeballColors) {
            String nameLowercase = slimeballColor.name().toLowerCase(Locale.US);
            String id = (nameLowercase + "_slime_ball");
            createItem(new ItemColoredSlimeball(slimeballColor), id);
        }

        createItem(new ItemShinySword(), "shiny_sword");
        createItem(new ItemUltraSword(), "ultra_sword");

        createItem(new ItemBlobmanArmor(EquipmentSlotType.HEAD), "blobman_crown");
        createItem(new ItemBlobmanArmor(EquipmentSlotType.CHEST), "blobman_suit");
        createItem(new ItemBlobmanArmor(EquipmentSlotType.LEGS), "blobman_pants");
        createItem(new ItemBlobmanArmor(EquipmentSlotType.FEET), "blobman_shoes");

        createItem(new ItemBlueSlimeArmor(EquipmentSlotType.HEAD), "blue_slime_helmet");
        createItem(new ItemBlueSlimeArmor(EquipmentSlotType.CHEST), "blue_slime_chestplate");
        createItem(new ItemBlueSlimeArmor(EquipmentSlotType.LEGS), "blue_slime_leggings");
        createItem(new ItemBlueSlimeArmor(EquipmentSlotType.FEET), "blue_slime_boots");

        createItem(new ItemSlimeWand(0), "slime_wand");
        createItem(new ItemSlimeWand(1), "blue_slime_wand");
        createItem(new ItemSimple(), "wand_stick");

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
