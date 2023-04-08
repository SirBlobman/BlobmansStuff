package xyz.sirblobman.mod.blobmanstuff.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;

import net.minecraftforge.fml.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

public final class EntityCustomItemProjectile extends ProjectileItemEntity {
    private final float damage;

    public EntityCustomItemProjectile(float damage, World world, LivingEntity owner) {
        super(BSEntityTypes.ITEM_PROJECTILE, owner, world);
        this.damage = damage;
    }

    public EntityCustomItemProjectile(EntityType<EntityCustomItemProjectile> type, World world) {
        super(type, world);
        this.damage = 1.0F;
    }

    private float getDamage() {
        return this.damage;
    }

    @Override
    public @NotNull IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected @NotNull Item getDefaultItem() {
        return Items.SLIME_BALL;
    }

    @Override
    protected void onHitEntity(@NotNull EntityRayTraceResult result) {
        super.onHitEntity(result);

        Entity entity = result.getEntity();
        float damage = getDamage();

        Entity owner = getOwner();
        DamageSource damageSource = DamageSource.thrown(this, owner);
        entity.hurt(damageSource, damage);
    }
}
