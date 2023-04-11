package xyz.sirblobman.mod.blobmanstuff.item.material;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

import xyz.sirblobman.mod.blobmanstuff.item.BSItems;

import org.jetbrains.annotations.NotNull;

public final class ShinySwordTier implements Tier {
    @Override
    public int getUses() {
        return 10_000;
    }

    @Override
    public float getSpeed() {
        return 80.0F;
    }

    @Override
    public float getAttackDamageBonus() {
        return 100.0F;
    }

    @Override
    public int getLevel() {
        return 50;
    }

    @Override
    public int getEnchantmentValue() {
        return 50;
    }

    @Override
    public @NotNull Ingredient getRepairIngredient() {
        return Ingredient.of(BSItems.SHINY_SLIME_BALL.get());
    }
}
