package xyz.sirblobman.mod.blobmanstuff.item.material;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem.Type;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import xyz.sirblobman.mod.blobmanstuff.BlobmanStuffMod;

import org.jetbrains.annotations.NotNull;

public final class BlobmanArmorMaterial implements ArmorMaterial {
    @Override
    public int getDurabilityForType(@NotNull Type slot) {
        return 1024;
    }

    @Override
    public int getDefenseForType(@NotNull Type slot) {
        return 5;
    }

    @Override
    public int getEnchantmentValue() {
        return 500;
    }

    @Override
    public @NotNull SoundEvent getEquipSound() {
        return SoundEvents.ARMOR_EQUIP_GOLD;
    }

    @Override
    public @NotNull Ingredient getRepairIngredient() {
        return Ingredient.EMPTY;
    }

    @Override
    public @NotNull String getName() {
        return BlobmanStuffMod.MOD_ID + ":blobman_armor";
    }

    @Override
    public float getToughness() {
        return 0.0F;
    }

    @Override
    public float getKnockbackResistance() {
        return 0.0F;
    }
}
