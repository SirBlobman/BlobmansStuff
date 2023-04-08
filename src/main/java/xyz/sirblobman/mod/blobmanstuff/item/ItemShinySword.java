package xyz.sirblobman.mod.blobmanstuff.item;

import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;

import xyz.sirblobman.mod.blobmanstuff.item.material.ShinySwordTier;

import org.jetbrains.annotations.NotNull;

public final class ItemShinySword extends SwordItem {
    private static final IItemTier SHINY_SWORD_TIER = new ShinySwordTier();

    public ItemShinySword() {
        super(SHINY_SWORD_TIER, 3, 0.0F, new Properties().tab(BSItemGroups.MAIN));
    }

    @Override
    public boolean isFoil(@NotNull ItemStack stack) {
        return true;
    }
}
