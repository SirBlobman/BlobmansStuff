package xyz.sirblobman.mod.blobmanstuff.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import xyz.sirblobman.mod.blobmanstuff.type.SlimeballColor;

import org.apache.commons.lang3.Validate;
import org.jetbrains.annotations.NotNull;

public final class ItemColoredSlimeball extends Item {
    private final SlimeballColor color;

    public ItemColoredSlimeball(SlimeballColor color) {
        super(new Properties().tab(BSItemGroups.MAIN).rarity(color.getRarity()));
        this.color = Validate.notNull(color, "color must not be null!");
    }

    public SlimeballColor getColor() {
        return this.color;
    }

    @Override
    public boolean isFoil(@NotNull ItemStack stack) {
        Item item = stack.getItem();
        if (item instanceof ItemColoredSlimeball) {
            ItemColoredSlimeball itemSlimeball = (ItemColoredSlimeball) item;
            SlimeballColor color = itemSlimeball.getColor();
            return color.isShiny();
        }

        return false;
    }
}
