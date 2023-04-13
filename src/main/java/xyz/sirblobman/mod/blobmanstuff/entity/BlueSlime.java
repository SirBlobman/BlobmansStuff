package xyz.sirblobman.mod.blobmanstuff.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;

import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

public final class BlueSlime extends Slime {
    public BlueSlime(EntityType<? extends Slime> type, Level world) {
        super(type, world);
        getJumpPower();
    }

    public static boolean checkCustomSpawnRules(EntityType<BlueSlime> type, LevelAccessor world,
                                                MobSpawnType spawnReason, BlockPos position, RandomSource random) {
        Difficulty difficulty = world.getDifficulty();
        if (difficulty == Difficulty.PEACEFUL) {
            return false;
        }

        Holder<Biome> biome = world.getBiome(position);
        boolean checkBiome = biome.is(BiomeTags.ALLOWS_SURFACE_SLIME_SPAWNS);
        boolean checkMinY = position.getY() > 50;
        boolean checkMaxY = position.getY() < 70;
        boolean checkRandom = random.nextFloat() < 0.5F;
        boolean checkMoonBrightness = random.nextFloat() < world.getMoonBrightness();
        boolean checkRawBrightness = world.getMaxLocalRawBrightness(position) <= random.nextInt(8);
        if (checkBiome && checkMinY && checkMaxY && checkRandom && checkMoonBrightness && checkRawBrightness) {
            return checkMobSpawnRules(type, world, spawnReason, position, random);
        }

        if (!(world instanceof WorldGenLevel genLevel)) {
            return false;
        }

        ChunkPos chunkPos = new ChunkPos(position);
        long worldSeed = genLevel.getSeed();
        long power = 987_234_911L;

        RandomSource randomSource = WorldgenRandom.seedSlimeChunk(chunkPos.x, chunkPos.z, worldSeed, power);
        boolean flag = randomSource.nextInt() == 0;
        if (random.nextInt(10) == 0 && flag && position.getY() < 40) {
            return checkMobSpawnRules(type, world, spawnReason, position, random);
        }

        return false;
    }

    @Override
    public @NotNull Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected @NotNull ResourceLocation getDefaultLootTable() {
        if (isTiny()) {
            return BuiltInLootTables.EMPTY;
        }

        EntityType<? extends Slime> slimeType = getType();
        return slimeType.getDefaultLootTable();
    }

    @Override
    protected @NotNull ParticleOptions getParticleType() {
        return ParticleTypes.DRIPPING_WATER;
    }

    @Override
    protected float getJumpPower() {
        return getBlockJumpFactor();
    }
}
