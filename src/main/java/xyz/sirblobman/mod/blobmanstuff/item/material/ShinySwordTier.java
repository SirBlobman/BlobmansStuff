package xyz.sirblobman.mod.blobmanstuff.item.material;

import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

import xyz.sirblobman.mod.blobmanstuff.item.BSItems;

public final class ShinySwordTier implements IItemTier {
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
    public Ingredient getRepairIngredient() {
        ItemStack stack = new ItemStack(BSItems.SHINY_SLIME_BALL, 1);
        return Ingredient.of(stack);
    }
}
