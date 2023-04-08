package xyz.sirblobman.mod.blobmanstuff.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import xyz.sirblobman.mod.blobmanstuff.type.SlimeballColor;

import org.apache.commons.lang3.Validate;

public final class ItemSlimeball extends Item {
    private final SlimeballColor color;

    public ItemSlimeball(SlimeballColor color) {
        super(new Properties().tab(BlobmanStuffCreativeTabs.MAIN_TAB));
        this.color = Validate.notNull(color, "color must not be null!");
    }

    public SlimeballColor getColor() {
        return this.color;
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        Item item = stack.getItem();
        if (item instanceof ItemSlimeball) {
            ItemSlimeball itemSlimeball = (ItemSlimeball) item;
            SlimeballColor color = itemSlimeball.getColor();
            return color.isShiny();
        }

        return false;
    }
}
