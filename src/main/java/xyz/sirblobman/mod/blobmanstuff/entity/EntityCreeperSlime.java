package xyz.sirblobman.mod.blobmanstuff.entity;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import javax.annotation.Nullable;

import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IChargeableMob;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap.MutableAttribute;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.datasync.IDataSerializer;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.Explosion.Mode;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.server.ServerWorld;

import xyz.sirblobman.mod.blobmanstuff.entity.ai.goal.SlimeCreeperAttackGoal;
import xyz.sirblobman.mod.blobmanstuff.entity.ai.goal.SlimeCreeperFaceRandomGoal;
import xyz.sirblobman.mod.blobmanstuff.entity.ai.goal.SlimeCreeperFloatGoal;
import xyz.sirblobman.mod.blobmanstuff.entity.ai.goal.SlimeCreeperHopGoal;
import xyz.sirblobman.mod.blobmanstuff.entity.ai.goal.SlimeCreeperMovementController;
import xyz.sirblobman.mod.blobmanstuff.entity.ai.goal.SlimeCreeperSwellGoal;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

@OnlyIn(value = Dist.CLIENT, _interface = IChargeableMob.class)
public final class EntityCreeperSlime extends MonsterEntity implements IChargeableMob {
    public static boolean checkCreeperSlimeSpawnRules(EntityType<EntityCreeperSlime> type, IWorld world,
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

    private static final DataParameter<Integer> DATA_SLIME_SIZE = defineId(DataSerializers.INT);
    private static final DataParameter<Integer> DATA_CREEPER_SWELL_DIR = defineId(DataSerializers.INT);
    private static final DataParameter<Boolean> DATA_CREEPER_IS_POWERED = defineId(DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> DATA_CREEPER_IS_IGNITED = defineId(DataSerializers.BOOLEAN);

    private static <T> DataParameter<T> defineId(IDataSerializer<T> serializer) {
        return EntityDataManager.defineId(EntityCreeperSlime.class, serializer);
    }

    public static MutableAttribute createAttributes() {
        MutableAttribute attributeMap = MonsterEntity.createMonsterAttributes();
        attributeMap.add(Attributes.MOVEMENT_SPEED, 0.25D);
        return attributeMap;
    }

    public float slimeTargetSquish;
    public float slimeSquish;
    public float slimeOldSquish;
    private boolean slimeWasOnGround;

    private int creeperOldSwell;
    private int creeperSwell;
    private int creeperMaxSwell;
    private int creeperExplosionRadius;

    public EntityCreeperSlime(EntityType<? extends EntityCreeperSlime> type, World world) {
        super(type, world);
        this.creeperMaxSwell = 30;
        this.creeperExplosionRadius = 3;
        this.moveControl = new SlimeCreeperMovementController(this);
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
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
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
    public void addAdditionalSaveData(@NotNull CompoundNBT tag) {
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
    public void readAdditionalSaveData(@NotNull CompoundNBT tag) {
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
    public @NotNull EntitySize getDimensions(@NotNull Pose pose) {
        float slimeSize = getSlimeSize();
        EntitySize dimensions = super.getDimensions(pose);
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
    public void onSyncedDataUpdated(@NotNull DataParameter<?> data) {
        if (DATA_SLIME_SIZE.equals(data)) {
            refreshDimensions();

            this.yRot = this.yHeadRot;
            this.yBodyRot = this.yHeadRot;
            if (isInWater() && this.random.nextInt(20) == 0) {
                doWaterSplashEffect();
            }
        }

        super.onSyncedDataUpdated(data);
    }

    @Override
    public boolean causeFallDamage(float p_225503_1_, float p_225503_2_) {
        return false;
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
    public void thunderHit(@NotNull ServerWorld world, @NotNull LightningBoltEntity lightning) {
        this.entityData.set(DATA_CREEPER_IS_POWERED, true);
    }

    @Override
    protected @NotNull ActionResultType mobInteract(@NotNull PlayerEntity player, @NotNull Hand hand) {
        ItemStack stack = player.getItemInHand(hand);
        Item item = stack.getItem();
        if (item != Items.FLINT_AND_STEEL) {
            return super.mobInteract(player, hand);
        }

        this.level.playSound(player, getX(), getY(), getZ(), SoundEvents.FLINTANDSTEEL_USE, getSoundSource(),
                1.0F, this.random.nextFloat() * 0.4F + 0.8F);
        if (this.level.isClientSide()) {
            ignite();
            stack.hurtAndBreak(1, player, newPlayer -> {
                newPlayer.broadcastBreakEvent(hand);
            });
        }

        return ActionResultType.sidedSuccess(this.level.isClientSide());
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
    public @Nullable ILivingEntityData finalizeSpawn(@NotNull IServerWorld world,
                                                     @NotNull DifficultyInstance difficulty,
                                                     @NotNull SpawnReason spawnReason,
                                                     @Nullable ILivingEntityData entityData,
                                                     @Nullable CompoundNBT nbtData) {
        int i = this.random.nextInt(3);
        if (i < 2 && this.random.nextFloat() < 0.5F * difficulty.getSpecialMultiplier()) {
            ++i;
        }

        int j = 1 << i;
        this.setSlimeSize(j, true);
        return super.finalizeSpawn(world, difficulty, spawnReason, entityData, nbtData);
    }

    @Override
    public @NotNull IPacket<?> getAddEntityPacket() {
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
            for(int j = 0; j < i * 8; ++j) {
                float f = this.random.nextFloat() * ((float)Math.PI * 2F);
                float f1 = this.random.nextFloat() * 0.5F + 0.5F;
                float f2 = MathHelper.sin(f) * (float)i * 0.5F * f1;
                float f3 = MathHelper.cos(f) * (float)i * 0.5F * f1;
                this.level.addParticle(this.getParticleType(), this.getX() + (double)f2, this.getY(), this.getZ() + (double)f3, 0.0D, 0.0D, 0.0D);
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
        return MathHelper.lerp(p_70831_1_, (float)this.creeperOldSwell, (float)this.creeperSwell) / (float)(this.creeperMaxSwell - 2);
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

    private IParticleData getParticleType() {
        return ParticleTypes.ITEM_SLIME;
    }

    private void ignite() {
        this.entityData.set(DATA_CREEPER_IS_IGNITED, true);
    }

    private void explodeCreeper() {
        if (this.level.isClientSide()) {
            return;
        }

        boolean mobGriefing = ForgeEventFactory.getMobGriefingEvent(this.level, this);
        Mode explosionMode = (mobGriefing ? Mode.DESTROY : Mode.NONE);
        int multiplier = isPowered() ? 2 : 1;

        this.dead = true;
        int explosionRadius = (this.creeperExplosionRadius * multiplier);
        this.level.explode(this, getX(), getY(), getZ(), explosionRadius, explosionMode);
        remove();
        spawnLingeringCloud();

        for (int i = 0; i < explosionRadius; i++) {
            SlimeEntity slimeEntity = EntityType.SLIME.create(this.level);
            if (slimeEntity == null) {
                continue;
            }

            slimeEntity.setPos(getX(), getY(), getZ());

            CompoundNBT tag = new CompoundNBT();
            slimeEntity.save(tag);
            tag.putInt("Size", 11);
            slimeEntity.load(tag);

            this.level.addFreshEntity(slimeEntity);
        }
    }

    private void spawnLingeringCloud() {
        Collection<EffectInstance> activeEffectCollection = getActiveEffects();
        if (activeEffectCollection.isEmpty()) {
            return;
        }

        AreaEffectCloudEntity effectCloud = new AreaEffectCloudEntity(this.level, getX(), getY(), getZ());
        effectCloud.setRadius(2.5F);
        effectCloud.setRadiusOnUse(-0.5F);
        effectCloud.setWaitTime(10);
        effectCloud.setDuration(effectCloud.getDuration() / 2);
        effectCloud.setRadiusPerTick(-effectCloud.getRadius() / effectCloud.getDuration());

        for (EffectInstance effect : activeEffectCollection) {
            EffectInstance effectCopy = new EffectInstance(effect);
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
