package xyz.sirblobman.mod.blobmanstuff.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import org.jetbrains.annotations.NotNull;

public class BlobmanStuffCreativeTabs {
    public static ItemGroup MAIN_TAB = new ItemGroup("blobmanstuff.main") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(BSItems.BLUE_SLIME_BALL, 1);
        }
    };
}
