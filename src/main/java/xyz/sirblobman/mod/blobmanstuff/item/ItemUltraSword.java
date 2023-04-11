package xyz.sirblobman.mod.blobmanstuff.item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

import xyz.sirblobman.mod.blobmanstuff.item.material.UltraSwordTier;

import org.jetbrains.annotations.NotNull;

public final class ItemUltraSword extends SwordItem {
    private static final Tier ULTRA_SWORD_TIER = new UltraSwordTier();

    public ItemUltraSword() {
        super(ULTRA_SWORD_TIER, 3, 20.0F, new Properties().rarity(Rarity.EPIC));
    }

    @Override
    public boolean isFoil(@NotNull ItemStack stack) {
        return true;
    }
}
