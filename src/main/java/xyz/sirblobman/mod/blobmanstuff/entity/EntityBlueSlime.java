package xyz.sirblobman.mod.blobmanstuff.entity;

import java.util.Objects;
import java.util.Optional;
import java.util.Random;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.loot.LootTables;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biomes;

import org.jetbrains.annotations.NotNull;

public final class EntityBlueSlime extends SlimeEntity {
    public static boolean checkBlueSlimeSpawnRules(EntityType<EntityBlueSlime> type, IWorld world,
                                                   SpawnReason spawnReason, BlockPos position, Random random) {
        if (world.getDifficulty() != Difficulty.PEACEFUL) {
            if (Objects.equals(world.getBiomeName(position), Optional.of(Biomes.SWAMP))
                    && position.getY() > 50 && position.getY() < 70 && random.nextFloat() < 0.5F
                    && random.nextFloat() < world.getMoonBrightness()
                    && world.getMaxLocalRawBrightness(position) <= random.nextInt(8)) {
                return checkMobSpawnRules(type, world, spawnReason, position, random);
            }

            if (!(world instanceof ISeedReader)) {
                return false;
            }

            ChunkPos chunkpos = new ChunkPos(position);
            boolean flag = SharedSeedRandom.seedSlimeChunk(chunkpos.x, chunkpos.z, ((ISeedReader)world).getSeed(),
                    987234911L).nextInt(10) == 0;
            if (random.nextInt(10) == 0 && flag && position.getY() < 40) {
                return checkMobSpawnRules(type, world, spawnReason, position, random);
            }
        }

        return false;
    }

    public EntityBlueSlime(EntityType<? extends SlimeEntity> type, World world) {
        super(type, world);
        getJumpPower();
    }

    @Override
    protected @NotNull ResourceLocation getDefaultLootTable() {
        if (isTiny()) {
            return LootTables.EMPTY;
        }

        EntityType<? extends SlimeEntity> slimeType = getType();
        return slimeType.getDefaultLootTable();
    }

    @Override
    protected IParticleData getParticleType() {
        return ParticleTypes.DRIPPING_WATER;
    }

    @Override
    public boolean causeFallDamage(float p_225503_1_, float p_225503_2_) {
        return false;
    }

    @Override
    protected float getJumpPower() {
        return getBlockJumpFactor();
    }
}
