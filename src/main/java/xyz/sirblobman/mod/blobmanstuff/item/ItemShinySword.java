package xyz.sirblobman.mod.blobmanstuff.item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

import xyz.sirblobman.mod.blobmanstuff.item.material.ShinySwordTier;

import org.jetbrains.annotations.NotNull;

public final class ItemShinySword extends SwordItem {
    private static final Tier SHINY_SWORD_TIER = new ShinySwordTier();

    public ItemShinySword() {
        super(SHINY_SWORD_TIER, 3, 0.0F, new Properties().rarity(Rarity.EPIC));
    }

    @Override
    public boolean isFoil(@NotNull ItemStack stack) {
        return true;
    }
}
