package xyz.sirblobman.mod.blobmanstuff.block;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraft.util.ResourceLocation;

import xyz.sirblobman.mod.blobmanstuff.BlobmanStuffMod;
import xyz.sirblobman.mod.blobmanstuff.item.BlobmanStuffCreativeTabs;

import net.minecraftforge.registries.IForgeRegistry;

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

    public static void registerBlocks(IForgeRegistry<Block> registry) {
        // TODO

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
        properties.tab(BlobmanStuffCreativeTabs.MAIN_TAB);

        BlockItem blockItem = new BlockItem(block, properties);
        blockItem.setRegistryName(id);

        ALL_ITEMS.add(blockItem);
        ALL_BLOCKS.add(block);
    }
}
