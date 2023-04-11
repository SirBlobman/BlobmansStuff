package xyz.sirblobman.mod.blobmanstuff;

import xyz.sirblobman.mod.blobmanstuff.block.BSBlocks;
import xyz.sirblobman.mod.blobmanstuff.entity.BSEntityTypes;
import xyz.sirblobman.mod.blobmanstuff.item.BSItems;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(BlobmanStuffMod.MOD_ID)
public final class BlobmanStuffMod {
    public static final String MOD_ID = "blobmanstuff";
    private static final Logger LOGGER;

    static {
        LOGGER = LogManager.getLogger();
    }

    public static Logger getLogger() {
        return LOGGER;
    }

    public BlobmanStuffMod() {
        FMLJavaModLoadingContext modLoadingContext = FMLJavaModLoadingContext.get();
        IEventBus modEventBus = modLoadingContext.getModEventBus();

        BSBlocks.registerBlocks(modEventBus);
        BSItems.registerItems(modEventBus);
        BSEntityTypes.registerSpawnEggs();
        BSEntityTypes.registerTypes(modEventBus);
    }
}
