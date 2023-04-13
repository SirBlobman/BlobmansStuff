package xyz.sirblobman.mod.blobmanstuff.entity;

import java.util.Collection;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.PowerableMob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Level.ExplosionInteraction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.WorldgenRandom;

import xyz.sirblobman.mod.blobmanstuff.entity.ai.goal.SlimeCreeperAttackGoal;
import xyz.sirblobman.mod.blobmanstuff.entity.ai.goal.SlimeCreeperFaceRandomGoal;
import xyz.sirblobman.mod.blobmanstuff.entity.ai.goal.SlimeCreeperFloatGoal;
import xyz.sirblobman.mod.blobmanstuff.entity.ai.goal.SlimeCreeperHopGoal;
import xyz.sirblobman.mod.blobmanstuff.entity.ai.goal.SlimeCreeperMovementController;
import xyz.sirblobman.mod.blobmanstuff.entity.ai.goal.SlimeCreeperSwellGoal;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

@OnlyIn(value = Dist.CLIENT, _interface = PowerableMob.class)
public final class CreeperSlime extends Monster implements PowerableMob {
    private static final EntityDataAccessor<Integer> DATA_SLIME_SIZE = defineId(EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_CREEPER_SWELL_DIR = defineId(EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> DATA_CREEPER_IS_POWERED = defineId(EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> DATA_CREEPER_IS_IGNITED = defineId(EntityDataSerializers.BOOLEAN);
    public float slimeTargetSquish;
    public float slimeSquish;
    public float slimeOldSquish;
    private boolean slimeWasOnGround;
    private int creeperOldSwell;
    private int creeperSwell;
    private int creeperMaxSwell;
    private int creeperExplosionRadius;
    public CreeperSlime(EntityType<? extends CreeperSlime> type, Level world) {
        super(type, world);
        this.creeperMaxSwell = 30;
        this.creeperExplosionRadius = 3;
        this.moveControl = new SlimeCreeperMovementController(this);
    }

    public static boolean checkCustomSpawnRules(EntityType<CreeperSlime> type, LevelAccessor world,
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

    private static <T> EntityDataAccessor<T> defineId(EntityDataSerializer<T> serializer) {
        return SynchedEntityData.defineId(CreeperSlime.class, serializer);
    }

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Monster.createMonsterAttributes();
        builder.add(Attributes.MOVEMENT_SPEED, 0.25D);
        return builder;
    }

    @Override
    protected void registerGoals() {
        // Goals
        this.goalSelector.addGoal(1, new SlimeCreeperFloatGoal(this));
        this.goalSelector.addGoal(2, new SlimeCreeperSwellGoal(this));
        this.goalSelector.addGoal(4, new SlimeCreeperAttackGoal(this));
        this.goalSelector.addGoal(4, new SlimeCreeperFaceRandomGoal(this));
        this.goalSelector.addGoal(5, new SlimeCreeperHopGoal(this));

        // Targets
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_SLIME_SIZE, 1);
        this.entityData.define(DATA_CREEPER_SWELL_DIR, -1);
        this.entityData.define(DATA_CREEPER_IS_POWERED, false);
        this.entityData.define(DATA_CREEPER_IS_IGNITED, false);
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag tag) {
        super.addAdditionalSaveData(tag);

        int size = (getSlimeSize() - 1);
        tag.putInt("Size", size);
        tag.putBoolean("wasOnGround", this.slimeWasOnGround);

        if (this.entityData.get(DATA_CREEPER_IS_POWERED)) {
            tag.putBoolean("powered", true);
        }

        tag.putShort("Fuse", (short) this.creeperMaxSwell);
        tag.putByte("ExplosionRadius", (byte) this.creeperExplosionRadius);
        tag.putBoolean("ignited", isCreeperIgnited());
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag tag) {
        super.readAdditionalSaveData(tag);

        if (tag.contains("Fuse", 99)) {
            this.creeperMaxSwell = tag.getShort("Fuse");
        }

        if (tag.contains("ExplosionRadius", 99)) {
            this.creeperExplosionRadius = tag.getByte("ExplosionRadius");
        }

        if (tag.getBoolean("ignited")) {
            ignite();
        }

        if (tag.getBoolean("powered")) {
            this.entityData.set(DATA_CREEPER_IS_POWERED, true);
        }

        int size = tag.getInt("Size");
        if (size < 0) {
            size = 0;
        }

        setSlimeSize(size + 1, false);
        this.slimeWasOnGround = tag.getBoolean("wasOnGround");
    }

    @Override
    protected boolean shouldDespawnInPeaceful() {
        return true;
    }

    @Override
    public @NotNull EntityDimensions getDimensions(@NotNull Pose pose) {
        float slimeSize = getSlimeSize();
        EntityDimensions dimensions = super.getDimensions(pose);
        return dimensions.scale(0.255F, slimeSize);
    }

    @Override
    public void refreshDimensions() {
        double x = getX();
        double y = getY();
        double z = getZ();

        super.refreshDimensions();
        setPos(x, y, z);
    }

    @Override
    public void onSyncedDataUpdated(@NotNull EntityDataAccessor<?> data) {
        if (DATA_SLIME_SIZE.equals(data)) {
            refreshDimensions();

            setYRot(this.yHeadRot);
            this.yBodyRot = this.yHeadRot;
            if (isInWater() && this.random.nextInt(20) == 0) {
                doWaterSplashEffect();
            }
        }

        super.onSyncedDataUpdated(data);
    }

    @Override
    protected SoundEvent getHurtSound(@NotNull DamageSource source) {
        return SoundEvents.SLIME_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.CREEPER_DEATH;
    }

    @Override
    public void thunderHit(@NotNull ServerLevel world, @NotNull LightningBolt lightning) {
        this.entityData.set(DATA_CREEPER_IS_POWERED, true);
    }

    @Override
    protected @NotNull InteractionResult mobInteract(Player player, @NotNull InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        Item item = stack.getItem();
        if (item != Items.FLINT_AND_STEEL) {
            return super.mobInteract(player, hand);
        }

        this.level.playSound(player, getX(), getY(), getZ(), SoundEvents.FLINTANDSTEEL_USE, getSoundSource(),
                1.0F, this.random.nextFloat() * 0.4F + 0.8F);
        if (!this.level.isClientSide()) {
            ignite();
            stack.hurtAndBreak(1, player, newPlayer -> {
                newPlayer.broadcastBreakEvent(hand);
            });
        }

        return InteractionResult.sidedSuccess(this.level.isClientSide());
    }

    @Override
    public float getSoundVolume() {
        float slimeSize = getSlimeSize();
        return 0.4F * slimeSize;
    }

    @Override
    public boolean isPowered() {
        return this.entityData.get(DATA_CREEPER_IS_POWERED);
    }

    @Override
    @SuppressWarnings({"deprecation", "OverrideOnly"}) // Overrides are fine
    public SpawnGroupData finalizeSpawn(@NotNull ServerLevelAccessor world, @NotNull DifficultyInstance difficulty,
                                        @NotNull MobSpawnType spawnReason, SpawnGroupData entityData,
                                        CompoundTag nbtData) {
        RandomSource randomSource = world.getRandom();
        int i = randomSource.nextInt(3);
        if (i < 2 && randomSource.nextFloat() < 0.5F * difficulty.getSpecialMultiplier()) {
            ++i;
        }

        int j = 1 << i;
        this.setSlimeSize(j, true);
        return super.finalizeSpawn(world, difficulty, spawnReason, entityData, nbtData);
    }

    @Override
    public @NotNull Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void tick() {
        this.slimeSquish += (this.slimeTargetSquish - this.slimeSquish) * 0.5F;
        this.slimeOldSquish = this.slimeSquish;

        if (this.isAlive()) {
            this.creeperOldSwell = this.creeperSwell;
            if (this.isCreeperIgnited()) {
                this.setSwellDir(1);
            }

            int i = this.getSwellDir();
            if (i > 0 && this.creeperSwell == 0) {
                this.playSound(SoundEvents.CREEPER_PRIMED, 1.0F, 0.5F);
            }

            this.creeperSwell += i;
            if (this.creeperSwell < 0) {
                this.creeperSwell = 0;
            }

            if (this.creeperSwell >= this.creeperMaxSwell) {
                this.creeperSwell = this.creeperMaxSwell;
                this.explodeCreeper();
            }
        }

        super.tick();
        if (!isAlive()) {
            return;
        }

        if (this.onGround && !this.slimeWasOnGround) {
            int i = this.getSlimeSize();
            for (int j = 0; j < i * 8; ++j) {
                float f = this.random.nextFloat() * ((float) Math.PI * 2F);
                float f1 = this.random.nextFloat() * 0.5F + 0.5F;
                float f2 = Mth.sin(f) * (float) i * 0.5F * f1;
                float f3 = Mth.cos(f) * (float) i * 0.5F * f1;
                this.level.addParticle(this.getParticleType(), this.getX() + (double) f2, this.getY(), this.getZ() + (double) f3, 0.0D, 0.0D, 0.0D);
            }

            this.playSound(this.getSquishSound(), this.getSoundVolume(), ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) / 0.8F);
            this.slimeTargetSquish = -0.5F;
        } else if (!this.onGround && this.slimeWasOnGround) {
            this.slimeTargetSquish = 1.0F;
        }

        this.slimeWasOnGround = this.onGround;
        this.decreaseSquish();
    }

    private void decreaseSquish() {
        this.slimeTargetSquish *= 0.6F;
    }

    private SoundEvent getSquishSound() {
        return SoundEvents.PARROT_IMITATE_CREEPER;
    }

    public boolean isCreeperIgnited() {
        return this.entityData.get(DATA_CREEPER_IS_IGNITED);
    }

    @OnlyIn(Dist.CLIENT)
    public float getSwelling(float p_70831_1_) {
        return Mth.lerp(p_70831_1_, (float) this.creeperOldSwell, (float) this.creeperSwell) / (float) (this.creeperMaxSwell - 2);
    }

    public int getSwellDir() {
        return this.entityData.get(DATA_CREEPER_SWELL_DIR);
    }

    public void setSwellDir(int p_70829_1_) {
        this.entityData.set(DATA_CREEPER_SWELL_DIR, p_70829_1_);
    }

    public boolean isSlimeTiny() {
        int slimeSize = getSlimeSize();
        return (slimeSize <= 1);
    }

    public int getSlimeSize() {
        return this.entityData.get(DATA_SLIME_SIZE);
    }

    private void setSlimeSize(int size, boolean fixHealth) {
        this.entityData.set(DATA_SLIME_SIZE, size);
        reapplyPosition();
        refreshDimensions();

        getAttribute(Attributes.MAX_HEALTH).setBaseValue(size * size);
        getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.2F + 0.1F * size);
        getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(size);

        if (fixHealth) {
            setHealth(getMaxHealth());
        }

        this.xpReward = size;
    }

    private ParticleOptions getParticleType() {
        return ParticleTypes.ITEM_SLIME;
    }

    private void ignite() {
        this.entityData.set(DATA_CREEPER_IS_IGNITED, true);
    }

    private void explodeCreeper() {
        if (this.level.isClientSide()) {
            return;
        }

        boolean powered = isPowered();
        int multiplier = (powered ? 2 : 1);
        int explosionRadius = (this.creeperExplosionRadius * multiplier);
        this.dead = true;

        this.level.explode(this, getX(), getY(), getZ(), explosionRadius, ExplosionInteraction.MOB);
        discard();
        spawnLingeringCloud();


        Component customName = getCustomName();
        int slimeSize = getSlimeSize();
        boolean noAi = isNoAi();

        for (int i = 0; i < explosionRadius; i++) {
            Slime slimeEntity = EntityType.SLIME.create(this.level);
            if (slimeEntity == null) {
                continue;
            }

            if (this.isPersistenceRequired()) {
                slimeEntity.setPersistenceRequired();
            }

            slimeEntity.setCustomName(customName);
            slimeEntity.setNoAi(noAi);
            slimeEntity.setSize(slimeSize, true);
            slimeEntity.moveTo(getX(), getY() + 1.5D, getZ());
            this.level.addFreshEntity(slimeEntity);
        }
    }

    private void spawnLingeringCloud() {
        Collection<MobEffectInstance> activeEffectCollection = getActiveEffects();
        if (activeEffectCollection.isEmpty()) {
            return;
        }

        AreaEffectCloud effectCloud = new AreaEffectCloud(this.level, getX(), getY(), getZ());
        effectCloud.setRadius(2.5F);
        effectCloud.setRadiusOnUse(-0.5F);
        effectCloud.setWaitTime(10);
        effectCloud.setDuration(effectCloud.getDuration() / 2);
        effectCloud.setRadiusPerTick(-effectCloud.getRadius() / effectCloud.getDuration());

        for (MobEffectInstance effect : activeEffectCollection) {
            MobEffectInstance effectCopy = new MobEffectInstance(effect);
            effectCloud.addEffect(effectCopy);
        }

        this.level.addFreshEntity(effectCloud);
    }

    public int getJumpDelay() {
        return this.random.nextInt(20) + 10;
    }

    public boolean doPlayJumpSound() {
        int slimeSize = this.getSlimeSize();
        return slimeSize > 0;
    }

    public SoundEvent getJumpSound() {
        return SoundEvents.SLIME_JUMP;
    }

    public float getSoundPitch() {
        float f = isSlimeTiny() ? 1.4F : 0.8F;
        return ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) * f;
    }

    public boolean isDealsDamage() {
        return !isSlimeTiny() && isEffectiveAi();
    }
}
