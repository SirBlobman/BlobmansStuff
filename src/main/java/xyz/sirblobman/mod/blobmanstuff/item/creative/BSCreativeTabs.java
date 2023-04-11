package xyz.sirblobman.mod.blobmanstuff.item.creative;

import java.util.Collection;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import xyz.sirblobman.mod.blobmanstuff.BlobmanStuffMod;
import xyz.sirblobman.mod.blobmanstuff.item.BSItems;

import net.minecraftforge.event.CreativeModeTabEvent.Register;
import net.minecraftforge.registries.RegistryObject;

public final class BSCreativeTabs {
    public static CreativeModeTab MAIN;

    public static void register(Register e) {
        ResourceLocation main = new ResourceLocation(BlobmanStuffMod.MOD_ID, "main");
        MAIN = e.registerCreativeModeTab(main, builder -> {
            builder.title(Component.translatable("item_group.blobmanstuff.main"));
            builder.icon(() -> new ItemStack(BSItems.BLUE_SLIME_BALL.get(), 1));
            builder.displayItems((flags, output) -> {
                Collection<RegistryObject<Item>> entries = BSItems.ITEMS.getEntries();
                for (RegistryObject<Item> entry : entries) {
                    Item item = entry.get();
                    output.accept(item);
                }
            });
        });
    }
}
