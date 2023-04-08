package xyz.sirblobman.mod.blobmanstuff.item.material;

import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

import xyz.sirblobman.mod.blobmanstuff.block.BSBlocks;

public final class UltraSwordTier implements IItemTier {
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
    public Ingredient getRepairIngredient() {
        ItemStack stack = new ItemStack(BSBlocks.BLACK_SLIME_BLOCK, 1);
        return Ingredient.of(stack);
    }
}
