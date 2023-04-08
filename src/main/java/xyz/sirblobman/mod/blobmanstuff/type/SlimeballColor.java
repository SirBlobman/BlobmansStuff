package xyz.sirblobman.mod.blobmanstuff.type;

public enum SlimeballColor {
    BLUE,
    YELLOW,
    RED,
    SHINY(true),
    BLACK;

    private final boolean shiny;

    SlimeballColor() {
        this(false);
    }

    SlimeballColor(boolean shiny) {
        this.shiny = shiny;
    }

    public boolean isShiny() {
        return this.shiny;
    }
}
