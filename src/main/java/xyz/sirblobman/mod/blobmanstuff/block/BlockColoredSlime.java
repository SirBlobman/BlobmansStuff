package xyz.sirblobman.mod.blobmanstuff.block;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlimeBlock;
import net.minecraft.world.phys.Vec3;

import xyz.sirblobman.mod.blobmanstuff.type.SlimeballColor;

import org.apache.commons.lang3.Validate;
import org.jetbrains.annotations.NotNull;

public final class BlockColoredSlime extends SlimeBlock {
    private final SlimeballColor color;
    private final double bounceHeight;

    public BlockColoredSlime(SlimeballColor color, double bounceHeight) {
        super(Properties.copy(Blocks.SLIME_BLOCK));
        this.color = Validate.notNull(color, "color must not be null!");
        this.bounceHeight = bounceHeight;
    }

    public SlimeballColor getColor() {
        return this.color;
    }

    public double getBounceHeight() {
        return this.bounceHeight;
    }

    @Override
    public void updateEntityAfterFallOn(@NotNull BlockGetter reader, Entity entity) {
        if (entity.isSuppressingBounce()) {
            super.updateEntityAfterFallOn(reader, entity);
            return;
        }

        bounceUp(entity);
    }

    private void bounceUp(Entity entity) {
        Vec3 vector3d = entity.getDeltaMovement();
        if (vector3d.y < 0.0D) {
            double multiplier = entity instanceof LivingEntity ? 1.0D : 0.8D;
            double bounceHeight = (multiplier * getBounceHeight());
            entity.setDeltaMovement(vector3d.x, -vector3d.y * bounceHeight, vector3d.z);
        }
    }
}
