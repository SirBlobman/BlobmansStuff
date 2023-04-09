package xyz.sirblobman.mod.blobmanstuff.entity;

import java.util.function.Supplier;

import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityType.Builder;
import net.minecraft.entity.ai.attributes.AttributeModifierMap.MutableAttribute;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraft.world.gen.Heightmap;

import xyz.sirblobman.mod.blobmanstuff.BlobmanStuffMod;
import xyz.sirblobman.mod.blobmanstuff.entity.renderer.BlueSlimeRenderer;
import xyz.sirblobman.mod.blobmanstuff.entity.renderer.CreeperSlimeRenderer;
import xyz.sirblobman.mod.blobmanstuff.item.BSItemGroups;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public final class BSEntityTypes {
    public static EntityType<EntityCustomItemProjectile> ITEM_PROJECTILE;
    public static EntityType<EntityBlueSlime> BLUE_SLIME;
    public static EntityType<EntityCreeperSlime> CREEPER_SLIME;

    public static void registerTypes(IForgeRegistry<EntityType<?>> registry) {
        Builder<EntityCustomItemProjectile> customItemProjectileBuilder = Builder.<EntityCustomItemProjectile>of(
                        EntityCustomItemProjectile::new, EntityClassification.MISC).sized(0.25F, 0.25F)
                .clientTrackingRange(4).updateInterval(10);
        ITEM_PROJECTILE = customItemProjectileBuilder.build("item_projectile");
        ITEM_PROJECTILE.setRegistryName(BlobmanStuffMod.MOD_ID, "item_projectile");

        Builder<EntityBlueSlime> blueSlimeBuilder = Builder.of(
                EntityBlueSlime::new, EntityClassification.MONSTER).sized(2.04F, 2.04F)
                .clientTrackingRange(10);
        BLUE_SLIME = blueSlimeBuilder.build("blue_slime");
        BLUE_SLIME.setRegistryName(BlobmanStuffMod.MOD_ID, "blue_slime");

        Builder<EntityCreeperSlime> creeperSlimeBuilder = Builder.of(
                EntityCreeperSlime::new, EntityClassification.MONSTER).sized(2.04F, 2.04F)
                .clientTrackingRange(10);
        CREEPER_SLIME = creeperSlimeBuilder.build("creeper_slime");
        CREEPER_SLIME.setRegistryName(BlobmanStuffMod.MOD_ID, "creeper_slime");

        registry.registerAll(ITEM_PROJECTILE, BLUE_SLIME, CREEPER_SLIME);
    }

    public static void registerSpawns() {
        EntitySpawnPlacementRegistry.register(BLUE_SLIME, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EntityBlueSlime::checkBlueSlimeSpawnRules);
        EntitySpawnPlacementRegistry.register(CREEPER_SLIME, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EntityCreeperSlime::checkCreeperSlimeSpawnRules);
    }

    @OnlyIn(Dist.CLIENT)
    public static void registerRendering(ItemRenderer itemRenderer) {
        RenderingRegistry.registerEntityRenderingHandler(ITEM_PROJECTILE,
                manager -> new SpriteRenderer<>(manager, itemRenderer));
        RenderingRegistry.registerEntityRenderingHandler(BLUE_SLIME, BlueSlimeRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(CREEPER_SLIME, CreeperSlimeRenderer::new);
    }

    public static void registerSpawnEggs(IForgeRegistry<Item> e) {
        int bgSlimeBlue = 0x0000FF;
        int fgSlimeBlue = 0x87CEEB;
        e.register(createSpawnEgg("blue_slime_spawn_egg", () -> BLUE_SLIME, bgSlimeBlue, fgSlimeBlue));

        int bgCreeperGreen = 0x3B8E37;
        int fgCreeperGreen = 0x67D755;
        e.register(createSpawnEgg("creeper_slime_spawn_egg", () -> CREEPER_SLIME, bgCreeperGreen, fgCreeperGreen));
    }

    private static Item createSpawnEgg(String id, Supplier<EntityType<?>> entityType, int bgColor, int highColor) {
        Properties properties = new Properties().tab(BSItemGroups.MAIN);
        Item item = new ForgeSpawnEggItem(entityType, bgColor, highColor, properties);
        item.setRegistryName(BlobmanStuffMod.MOD_ID, id);
        return item;
    }

    public static void registerAttributes(EntityAttributeCreationEvent e) {
        MutableAttribute blueSlimeMap = MonsterEntity.createMonsterAttributes();
        blueSlimeMap.add(Attributes.ATTACK_DAMAGE, 5.0D);
        e.put(BLUE_SLIME, blueSlimeMap.build());

        MutableAttribute creeperSlimeMap = EntityCreeperSlime.createAttributes();
        e.put(CREEPER_SLIME, creeperSlimeMap.build());
    }
}
