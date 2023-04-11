package xyz.sirblobman.mod.blobmanstuff.entity;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

public final class ItemProjectile extends ThrowableItemProjectile {
    private final float damage;

    public ItemProjectile(float damage, Level world, LivingEntity owner) {
        super(BSEntityTypes.ITEM_PROJECTILE.get(), owner, world);
        this.damage = damage;
    }

    public ItemProjectile(EntityType<ItemProjectile> type, Level world) {
        super(type, world);
        this.damage = 1.0F;
    }

    private float getDamage() {
        return this.damage;
    }

    @Override
    public @NotNull Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected @NotNull Item getDefaultItem() {
        return Items.SLIME_BALL;
    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult result) {
        super.onHitEntity(result);

        Entity hitEntity = result.getEntity();
        Entity owner = getOwner();
        float damage = getDamage();

        Level level = getLevel();
        DamageSources damageSources = level.damageSources();
        DamageSource damageSource = damageSources.thrown(this, owner);
        hitEntity.hurt(damageSource, damage);
    }
}
