package xyz.sirblobman.mod.blobmanstuff.client;

import xyz.sirblobman.mod.blobmanstuff.BlobmanStuffMod;
import xyz.sirblobman.mod.blobmanstuff.entity.BSEntityTypes;
import xyz.sirblobman.mod.blobmanstuff.entity.renderer.layer.BSLayerDefinitions;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent.RegisterLayerDefinitions;
import net.minecraftforge.client.event.EntityRenderersEvent.RegisterRenderers;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(value = Dist.CLIENT, modid = BlobmanStuffMod.MOD_ID, bus = Bus.MOD)
public final class BSClientModBus {
    @SubscribeEvent
    public static void registerRenderers(RegisterRenderers e) {
        BSEntityTypes.registerRendering(e);
    }

    @SubscribeEvent
    public static void registerRenderingLayers(RegisterLayerDefinitions e) {
        BSLayerDefinitions.registerDefinitions(e);
    }
}
