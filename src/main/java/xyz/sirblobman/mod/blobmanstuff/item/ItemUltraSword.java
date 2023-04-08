package xyz.sirblobman.mod.blobmanstuff.item;

import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;

import xyz.sirblobman.mod.blobmanstuff.item.material.UltraSwordTier;

import org.jetbrains.annotations.NotNull;

public final class ItemUltraSword extends SwordItem {
    private static final IItemTier ULTRA_SWORD_TIER = new UltraSwordTier();

    public ItemUltraSword() {
        super(ULTRA_SWORD_TIER, 3, 20.0F, new Properties().tab(BSItemGroups.MAIN));
    }

    @Override
    public boolean isFoil(@NotNull ItemStack stack) {
        return true;
    }
}
