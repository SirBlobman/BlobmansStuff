package xyz.sirblobman.mod.blobmanstuff.block;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraft.util.ResourceLocation;

import xyz.sirblobman.mod.blobmanstuff.BlobmanStuffMod;
import xyz.sirblobman.mod.blobmanstuff.item.BSItemGroups;
import xyz.sirblobman.mod.blobmanstuff.type.SlimeballColor;

import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(BlobmanStuffMod.MOD_ID)
public final class BSBlocks {
    private static final List<Block> ALL_BLOCKS;
    private static final List<BlockItem> ALL_ITEMS;

    static {
        ALL_BLOCKS = new ArrayList<>();
        ALL_ITEMS = new ArrayList<>();
    }

    public static List<Block> getAllBlocks() {
        return Collections.unmodifiableList(ALL_BLOCKS);
    }

    public static List<BlockItem> getAllBlockItems() {
        return Collections.unmodifiableList(ALL_ITEMS);
    }

    @ObjectHolder("blue_slime_block") public static Block BLUE_SLIME_BLOCK;
    @ObjectHolder("yellow_slime_block") public static Block YELLOW_SLIME_BLOCK;
    @ObjectHolder("red_slime_block") public static Block RED_SLIME_BLOCK;
    @ObjectHolder("shiny_slime_block") public static Block SHINY_SLIME_BLOCK;
    @ObjectHolder("black_slime_block") public static Block BLACK_SLIME_BLOCK;

    public static void registerBlocks(IForgeRegistry<Block> registry) {
        createBlock(new BlockColoredSlime(SlimeballColor.BLUE, 1.5D), "blue_slime_block");
        createBlock(new BlockColoredSlime(SlimeballColor.YELLOW, 2.0D), "yellow_slime_block");
        createBlock(new BlockColoredSlime(SlimeballColor.RED, 3.0D), "red_slime_block");
        createBlock(new BlockColoredSlime(SlimeballColor.SHINY, 5.0D), "shiny_slime_block");
        createBlock(new BlockColoredSlime(SlimeballColor.BLACK, 10.0D), "black_slime_block");

        List<Block> allBlocks = getAllBlocks();
        for (Block block : allBlocks) {
            registry.register(block);
        }
    }

    public static void registerItems(IForgeRegistry<Item> registry) {
        List<BlockItem> allItems = getAllBlockItems();
        for (BlockItem item : allItems) {
            registry.register(item);
        }
    }

    private static void createBlock(Block block, String id) {
        ResourceLocation registryName = new ResourceLocation(BlobmanStuffMod.MOD_ID, id);
        block.setRegistryName(registryName);

        Properties properties = new Properties();
        properties.tab(BSItemGroups.MAIN);

        BlockItem blockItem = new BlockItem(block, properties);
        blockItem.setRegistryName(id);

        ALL_ITEMS.add(blockItem);
        ALL_BLOCKS.add(block);
    }

    public static void registerRendering() {
        RenderType translucent = RenderType.translucent();
        RenderTypeLookup.setRenderLayer(BSBlocks.BLACK_SLIME_BLOCK, translucent);
        RenderTypeLookup.setRenderLayer(BSBlocks.BLUE_SLIME_BLOCK, translucent);
        RenderTypeLookup.setRenderLayer(BSBlocks.RED_SLIME_BLOCK, translucent);
        RenderTypeLookup.setRenderLayer(BSBlocks.SHINY_SLIME_BLOCK, translucent);
        RenderTypeLookup.setRenderLayer(BSBlocks.YELLOW_SLIME_BLOCK, translucent);
    }
}
