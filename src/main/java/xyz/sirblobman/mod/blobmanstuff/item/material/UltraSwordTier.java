package xyz.sirblobman.mod.blobmanstuff.item.material;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

import xyz.sirblobman.mod.blobmanstuff.block.BSBlocks;

import org.jetbrains.annotations.NotNull;

public final class UltraSwordTier implements Tier {
    @Override
    public int getUses() {
        return (Integer.MAX_VALUE - 1);
    }

    @Override
    public float getSpeed() {
        return 800.0F;
    }

    @Override
    public float getAttackDamageBonus() {
        return 1_000.0F;
    }

    @Override
    public int getLevel() {
        return 500;
    }

    @Override
    public int getEnchantmentValue() {
        return 500;
    }

    @Override
    public @NotNull Ingredient getRepairIngredient() {
        return Ingredient.of(BSBlocks.BLACK_SLIME_BLOCK.get());
    }
}
