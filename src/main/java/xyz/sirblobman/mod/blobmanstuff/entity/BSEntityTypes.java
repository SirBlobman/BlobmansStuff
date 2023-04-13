package xyz.sirblobman.mod.blobmanstuff.entity;

import java.util.function.Supplier;

import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityType.Builder;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements.Type;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.levelgen.Heightmap.Types;

import xyz.sirblobman.mod.blobmanstuff.BlobmanStuffMod;
import xyz.sirblobman.mod.blobmanstuff.entity.renderer.BlueSlimeRenderer;
import xyz.sirblobman.mod.blobmanstuff.entity.renderer.CreeperSlimeRenderer;
import xyz.sirblobman.mod.blobmanstuff.item.BSItems;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent.RegisterRenderers;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent.Operation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class BSEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, BlobmanStuffMod.MOD_ID);

    public static RegistryObject<EntityType<ItemProjectile>> ITEM_PROJECTILE = registerType(() -> {
        Builder<ItemProjectile> builder = Builder.of(ItemProjectile::new, MobCategory.MISC);
        builder.sized(0.25F, 0.25F);
        builder.clientTrackingRange(4);
        builder.updateInterval(10);
        return builder;
    }, "item_projectile");

    public static RegistryObject<EntityType<BlueSlime>> BLUE_SLIME = registerType(() -> {
        Builder<BlueSlime> builder = Builder.of(BlueSlime::new, MobCategory.MONSTER);
        builder.clientTrackingRange(10);
        return builder;
    }, "blue_slime");

    public static RegistryObject<EntityType<CreeperSlime>> CREEPER_SLIME = registerType(() -> {
        Builder<CreeperSlime> builder = Builder.of(CreeperSlime::new, MobCategory.MONSTER);
        builder.clientTrackingRange(10);
        return builder;
    }, "creeper_slime");

    private static <T extends Entity> RegistryObject<EntityType<T>> registerType(Supplier<Builder<T>> builderSupplier, String id) {
        Supplier<EntityType<T>> supplier = () -> {
            Builder<T> builder = builderSupplier.get();
            return builder.build(id);
        };

        return ENTITY_TYPES.register(id, supplier);
    }

    public static void registerTypes(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }

    public static void registerSpawns(SpawnPlacementRegisterEvent e) {
        e.register(BLUE_SLIME.get(), Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES,
                BlueSlime::checkCustomSpawnRules, Operation.AND);

        e.register(CREEPER_SLIME.get(), Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES,
                CreeperSlime::checkCustomSpawnRules, Operation.AND);
    }

    @OnlyIn(Dist.CLIENT)
    public static void registerRendering(RegisterRenderers e) {
        e.registerEntityRenderer(ITEM_PROJECTILE.get(), ThrownItemRenderer::new);
        e.registerEntityRenderer(BLUE_SLIME.get(), BlueSlimeRenderer::new);
        e.registerEntityRenderer(CREEPER_SLIME.get(), CreeperSlimeRenderer::new);
    }

    public static void registerSpawnEggs() {
        int bgSlimeBlue = 0x0000FF;
        int fgSlimeBlue = 0x87CEEB;
        BSItems.ITEMS.register("blue_slime_spawn_egg",
                () -> createSpawnEgg(BLUE_SLIME, bgSlimeBlue, fgSlimeBlue));

        int bgCreeperGreen = 0x3B8E37;
        int fgCreeperGreen = 0x67D755;
        BSItems.ITEMS.register("creeper_slime_spawn_egg",
                () -> createSpawnEgg(CREEPER_SLIME, bgCreeperGreen, fgCreeperGreen));
    }

    private static <T extends Mob> Item createSpawnEgg(RegistryObject<EntityType<T>> entityType, int bgColor,
                                                       int highColor) {
        Properties properties = new Properties();
        return new ForgeSpawnEggItem(entityType, bgColor, highColor, properties);
    }

    public static void registerAttributes(EntityAttributeCreationEvent e) {
        AttributeSupplier.Builder blueSlimeAttributes = Monster.createMonsterAttributes();
        blueSlimeAttributes.add(Attributes.ATTACK_DAMAGE, 5.0D);
        e.put(BLUE_SLIME.get(), blueSlimeAttributes.build());

        AttributeSupplier.Builder creeperSlimeMap = CreeperSlime.createAttributes();
        e.put(CREEPER_SLIME.get(), creeperSlimeMap.build());
    }
}
