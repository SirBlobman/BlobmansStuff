package xyz.sirblobman.mod.blobmanstuff.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import org.jetbrains.annotations.NotNull;

public final class BSItemGroups {
    public static ItemGroup MAIN = new ItemGroup("blobmanstuff.main") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(BSItems.BLUE_SLIME_BALL, 1);
        }
    };
}
