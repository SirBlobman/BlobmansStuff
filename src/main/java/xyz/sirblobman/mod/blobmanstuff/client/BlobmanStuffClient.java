package xyz.sirblobman.mod.blobmanstuff.client;

import java.util.function.Supplier;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;

import xyz.sirblobman.mod.blobmanstuff.BlobmanStuffMod;
import xyz.sirblobman.mod.blobmanstuff.block.BSBlocks;
import xyz.sirblobman.mod.blobmanstuff.entity.BSEntityTypes;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.apache.logging.log4j.Logger;

@EventBusSubscriber(value = Dist.CLIENT, modid = BlobmanStuffMod.MOD_ID, bus = Bus.MOD)
public final class BlobmanStuffClient {
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent e) {
        Logger logger = BlobmanStuffMod.getLogger();
        logger.info("Blobman's Stuff Client Setup");
        BSBlocks.registerRendering();

        Supplier<Minecraft> minecraftSupplier = e.getMinecraftSupplier();
        Minecraft minecraft = minecraftSupplier.get();
        ItemRenderer itemRenderer = minecraft.getItemRenderer();
        BSEntityTypes.registerRendering(itemRenderer);
    }
}
