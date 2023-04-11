package xyz.sirblobman.mod.blobmanstuff;

import xyz.sirblobman.mod.blobmanstuff.entity.BSEntityTypes;
import xyz.sirblobman.mod.blobmanstuff.item.creative.BSCreativeTabs;

import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = BlobmanStuffMod.MOD_ID, bus = Bus.MOD)
public final class BlobmanStuffRegistry {
    @SubscribeEvent
    public static void onRegisterCreativeTabs(CreativeModeTabEvent.Register e) {
        BSCreativeTabs.register(e);
    }

    @SubscribeEvent
    public static void onRegisterEntityAttributes(EntityAttributeCreationEvent e) {
        BSEntityTypes.registerAttributes(e);
    }

    @SubscribeEvent
    public static void onRegisterEntitySpawns(SpawnPlacementRegisterEvent e) {
        BSEntityTypes.registerSpawns(e);
    }
}
