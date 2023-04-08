package xyz.sirblobman.mod.blobmanstuff;

import java.util.function.Supplier;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.entity.SpriteRenderer;

import xyz.sirblobman.mod.blobmanstuff.block.BSBlocks;
import xyz.sirblobman.mod.blobmanstuff.entity.BSEntityTypes;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
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
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        modEventBus.register(new BlobmanStuffRegistry(this));

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(FMLCommonSetupEvent e) {
        Logger logger = getLogger();
        logger.info("Blobman's Stuff Common Setup");
    }

    private void clientSetup(FMLClientSetupEvent e) {
        Logger logger = getLogger();
        logger.info("Blobman's Stuff Client Setup");

        BSBlocks.registerRendering();

        Supplier<Minecraft> minecraftSupplier = e.getMinecraftSupplier();
        Minecraft minecraft = minecraftSupplier.get();
        ItemRenderer itemRenderer = minecraft.getItemRenderer();
        BSEntityTypes.registerRendering(itemRenderer);
    }

    @SubscribeEvent
    public void serverSetup(FMLServerStartingEvent e) {
        Logger logger = getLogger();
        logger.info("Blobman's Stuff Server Setup");
    }
}