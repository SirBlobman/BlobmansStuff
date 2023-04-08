package xyz.sirblobman.mod.blobmanstuff.item.material;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

import xyz.sirblobman.mod.blobmanstuff.BlobmanStuffMod;

import org.jetbrains.annotations.NotNull;

public final class BlueSlimeArmorMaterial implements IArmorMaterial {
    @Override
    public int getDurabilityForSlot(@NotNull EquipmentSlotType slot) {
        return 100;
    }

    @Override
    public int getDefenseForSlot(@NotNull EquipmentSlotType slot) {
        return 5;
    }

    @Override
    public int getEnchantmentValue() {
        return 500;
    }

    @Override
    public @NotNull SoundEvent getEquipSound() {
        return SoundEvents.ARMOR_EQUIP_LEATHER;
    }

    @Override
    public @NotNull Ingredient getRepairIngredient() {
        return Ingredient.EMPTY;
    }

    @Override
    public @NotNull String getName() {
        return BlobmanStuffMod.MOD_ID + ":blue_slime";
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
