package xyz.sirblobman.mod.blobmanstuff.type;

import net.minecraft.world.item.Rarity;

import org.apache.commons.lang3.Validate;

public enum SlimeballColor {
    BLUE(Rarity.COMMON),
    YELLOW(Rarity.UNCOMMON),
    RED(Rarity.UNCOMMON),
    SHINY(true, Rarity.RARE),
    BLACK(Rarity.EPIC);

    private final boolean shiny;
    private final Rarity rarity;

    SlimeballColor(Rarity rarity) {
        this(false, rarity);
    }

    SlimeballColor(boolean shiny, Rarity rarity) {
        this.shiny = shiny;
        this.rarity = Validate.notNull(rarity, "rarity must not be null!");
    }

    public boolean isShiny() {
        return this.shiny;
    }

    public Rarity getRarity() {
        return this.rarity;
    }
}
