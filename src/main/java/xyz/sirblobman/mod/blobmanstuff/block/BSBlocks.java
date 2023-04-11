package xyz.sirblobman.mod.blobmanstuff.block;

import java.util.function.Supplier;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;

import xyz.sirblobman.mod.blobmanstuff.BlobmanStuffMod;
import xyz.sirblobman.mod.blobmanstuff.item.BSItems;
import xyz.sirblobman.mod.blobmanstuff.type.SlimeballColor;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

public final class BSBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, BlobmanStuffMod.MOD_ID);

    private static final PropertyFixer<BlockColoredSlime> COLORED_SLIME_FIXER = (block, properties) -> {
        SlimeballColor color = block.getColor();
        Rarity rarity = color.getRarity();
        return properties.rarity(rarity);
    };

    public static final RegistryObject<BlockColoredSlime> BLUE_SLIME_BLOCK = registerBlock("blue_slime_block",
            () -> new BlockColoredSlime(SlimeballColor.BLUE, 1.5D), COLORED_SLIME_FIXER);
    public static final RegistryObject<BlockColoredSlime> YELLOW_SLIME_BLOCK = registerBlock("yellow_slime_block",
            () -> new BlockColoredSlime(SlimeballColor.YELLOW, 2.0D), COLORED_SLIME_FIXER);
    public static final RegistryObject<BlockColoredSlime> RED_SLIME_BLOCK = registerBlock("red_slime_block",
            () -> new BlockColoredSlime(SlimeballColor.RED, 3.0D), COLORED_SLIME_FIXER);
    public static final RegistryObject<BlockColoredSlime> SHINY_SLIME_BLOCK = registerBlock("shiny_slime_block",
            () -> new BlockColoredSlime(SlimeballColor.SHINY, 5.0D), COLORED_SLIME_FIXER);
    public static final RegistryObject<BlockColoredSlime> BLACK_SLIME_BLOCK = registerBlock("black_slime_block",
            () -> new BlockColoredSlime(SlimeballColor.BLACK, 10.0D), COLORED_SLIME_FIXER);

    private static <T extends Block> RegistryObject<T> registerBlock(String id, Supplier<T> supplier,
                                                                     @Nullable PropertyFixer<T> fixer) {
        RegistryObject<T> object = BLOCKS.register(id, supplier);
        registerBlockItem(id, object, fixer);
        return object;
    }

    private static <T extends Block> void registerBlockItem(String id, RegistryObject<T> object,
                                                            @Nullable PropertyFixer<T> fixer) {
        Supplier<Item> supplier = () -> {
            T block = object.get();

            Properties properties = new Properties();
            if (fixer != null) {
                properties = fixer.fix(block, properties);
            }

            return new BlockItem(block, properties);
        };

        BSItems.ITEMS.register(id, supplier);
    }

    public static void registerBlocks(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
