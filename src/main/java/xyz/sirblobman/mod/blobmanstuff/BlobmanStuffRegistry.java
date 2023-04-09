package xyz.sirblobman.mod.blobmanstuff;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;

import xyz.sirblobman.mod.blobmanstuff.block.BSBlocks;
import xyz.sirblobman.mod.blobmanstuff.entity.BSEntityTypes;
import xyz.sirblobman.mod.blobmanstuff.item.BSItems;

import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber(modid = BlobmanStuffMod.MOD_ID, bus = Bus.MOD)
public final class BlobmanStuffRegistry {
    @SubscribeEvent
    public static void onRegisterBlocks(Register<Block> e) {
        IForgeRegistry<Block> registry = e.getRegistry();
        BSBlocks.registerBlocks(registry);
    }

    @SubscribeEvent
    public static void onRegisterItems(Register<Item> e) {
        IForgeRegistry<Item> registry = e.getRegistry();
        BSItems.register(registry);
        BSBlocks.registerItems(registry);
        BSEntityTypes.registerSpawnEggs(registry);
    }

    @SubscribeEvent
    public static void onRegisterEntityTypes(Register<EntityType<?>> e) {
        IForgeRegistry<EntityType<?>> registry = e.getRegistry();
        BSEntityTypes.registerTypes(registry);
        BSEntityTypes.registerSpawns();
    }

    @SubscribeEvent
    public static void onRegisterEntityAttributes(EntityAttributeCreationEvent e) {
        BSEntityTypes.registerAttributes(e);
    }
}
