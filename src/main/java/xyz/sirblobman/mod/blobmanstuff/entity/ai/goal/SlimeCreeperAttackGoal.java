package xyz.sirblobman.mod.blobmanstuff.entity.ai.goal;

import java.util.EnumSet;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;

import xyz.sirblobman.mod.blobmanstuff.entity.EntityCreeperSlime;

public final class SlimeCreeperAttackGoal extends Goal {
    private final EntityCreeperSlime slime;
    private int growTiredTimer;

    public SlimeCreeperAttackGoal(EntityCreeperSlime slime) {
        this.slime = slime;
        this.setFlags(EnumSet.of(Flag.LOOK));
    }

    public boolean canUse() {
        LivingEntity livingentity = this.slime.getTarget();
        if (livingentity == null) {
            return false;
        } else if (!livingentity.isAlive()) {
            return false;
        } else {
            return (!(livingentity instanceof PlayerEntity) || !((PlayerEntity) livingentity).abilities.invulnerable)
                    && this.slime.getMoveControl() instanceof SlimeCreeperMovementController;
        }
    }

    public void start() {
        this.growTiredTimer = 300;
        super.start();
    }

    public boolean canContinueToUse() {
        LivingEntity livingentity = this.slime.getTarget();
        if (livingentity == null) {
            return false;
        } else if (!livingentity.isAlive()) {
            return false;
        } else if (livingentity instanceof PlayerEntity && ((PlayerEntity)livingentity).abilities.invulnerable) {
            return false;
        } else {
            return --this.growTiredTimer > 0;
        }
    }

    public void tick() {
        LivingEntity target = this.slime.getTarget();
        if (target == null) {
            return;
        }

        this.slime.lookAt(target, 10.0F, 10.0F);
        ((SlimeCreeperMovementController)this.slime.getMoveControl()).setDirection(this.slime.yRot,
                this.slime.isDealsDamage());
    }
}
