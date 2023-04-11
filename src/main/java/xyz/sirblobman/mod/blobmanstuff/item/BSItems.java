package xyz.sirblobman.mod.blobmanstuff.item;

import java.util.function.Supplier;

import net.minecraft.world.item.ArmorItem.Type;
import net.minecraft.world.item.Item;

import xyz.sirblobman.mod.blobmanstuff.BlobmanStuffMod;
import xyz.sirblobman.mod.blobmanstuff.type.SlimeballColor;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class BSItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, BlobmanStuffMod.MOD_ID);

    // Slime Balls
    public static final RegistryObject<Item> BLUE_SLIME_BALL = registerItem("blue_slime_ball",
            () -> new ItemColoredSlimeball(SlimeballColor.BLUE));
    public static final RegistryObject<Item> YELLOW_SLIME_BALL = registerItem("yellow_slime_ball",
            () -> new ItemColoredSlimeball(SlimeballColor.YELLOW));
    public static final RegistryObject<Item> RED_SLIME_BALL = registerItem("red_slime_ball",
            () -> new ItemColoredSlimeball(SlimeballColor.RED));
    public static final RegistryObject<Item> SHINY_SLIME_BALL = registerItem("shiny_slime_ball",
            () -> new ItemColoredSlimeball(SlimeballColor.SHINY));
    public static final RegistryObject<Item> BLACK_SLIME_BALL = registerItem("black_slime_ball",
            () -> new ItemColoredSlimeball(SlimeballColor.BLACK));

    // Blobman Armor
    public static final RegistryObject<Item> BLOBMAN_CROWN = registerItem("blobman_crown",
            () -> new ItemBlobmanArmor(Type.HELMET));
    public static final RegistryObject<Item> BLOBMAN_SUIT = registerItem("blobman_suit",
            () -> new ItemBlobmanArmor(Type.CHESTPLATE));
    public static final RegistryObject<Item> BLOBMAN_PANTS = registerItem("blobman_pants",
            () -> new ItemBlobmanArmor(Type.LEGGINGS));
    public static final RegistryObject<Item> BLOBMAN_SHOES = registerItem("blobman_shoes",
            () -> new ItemBlobmanArmor(Type.BOOTS));

    // Blue Slime Armor
    public static final RegistryObject<Item> BLUE_SLIME_HELMET = registerItem("blue_slime_helmet",
            () -> new ItemBlueSlimeArmor(Type.HELMET));
    public static final RegistryObject<Item> BLUE_SLIME_CHESTPLATE = registerItem("blue_slime_chestplate",
            () -> new ItemBlueSlimeArmor(Type.CHESTPLATE));
    public static final RegistryObject<Item> BLUE_SLIME_LEGGINGS = registerItem("blue_slime_leggings",
            () -> new ItemBlueSlimeArmor(Type.LEGGINGS));
    public static final RegistryObject<Item> BLUE_SLIME_BOOTS = registerItem("blue_slime_boots",
            () -> new ItemBlueSlimeArmor(Type.BOOTS));

    // Overpowered Swords
    public static final RegistryObject<Item> SHINY_SWORD = registerItem("shiny_sword", ItemShinySword::new);
    public static final RegistryObject<Item> ULTRA_SWORD = registerItem("ultra_sword", ItemUltraSword::new);

    // Wand Items
    public static final RegistryObject<Item> WAND_STICK = registerItem("wand_stick", ItemSimple::new);
    public static final RegistryObject<Item> SLIME_WAND = registerItem("slime_wand",
            () -> new ItemSlimeWand(0));
    public static final RegistryObject<Item> BLUE_SLIME_WAND = registerItem("blue_slime_wand",
            () -> new ItemSlimeWand(1));

    private static <T extends Item> RegistryObject<T> registerItem(String id, Supplier<T> supplier) {
        return ITEMS.register(id, supplier);
    }

    public static void registerItems(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
