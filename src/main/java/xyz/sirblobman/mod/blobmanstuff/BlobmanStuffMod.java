package xyz.sirblobman.mod.blobmanstuff;

import xyz.sirblobman.mod.blobmanstuff.block.BSBlocks;
import xyz.sirblobman.mod.blobmanstuff.entity.BSEntityTypes;
import xyz.sirblobman.mod.blobmanstuff.item.BSItems;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(BlobmanStuffMod.MOD_ID)
public final class BlobmanStuffMod {
    public static final String MOD_ID = "blobmanstuff";

    public BlobmanStuffMod() {
        FMLJavaModLoadingContext modLoadingContext = FMLJavaModLoadingContext.get();
        IEventBus modEventBus = modLoadingContext.getModEventBus();

        BSBlocks.registerBlocks(modEventBus);
        BSItems.registerItems(modEventBus);
        BSEntityTypes.registerTypes(modEventBus);
    }
}
