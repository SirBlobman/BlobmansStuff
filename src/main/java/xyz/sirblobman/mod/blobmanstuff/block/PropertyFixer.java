package xyz.sirblobman.mod.blobmanstuff.block;

import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.block.Block;

@FunctionalInterface
public interface PropertyFixer<T extends Block> {
    Properties fix(T block, Properties properties);
}
