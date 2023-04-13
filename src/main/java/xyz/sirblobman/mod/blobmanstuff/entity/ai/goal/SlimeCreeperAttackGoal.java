package xyz.sirblobman.mod.blobmanstuff.entity.ai.goal;

import java.util.EnumSet;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;

import xyz.sirblobman.mod.blobmanstuff.entity.CreeperSlime;

public final class SlimeCreeperAttackGoal extends Goal {
    private final CreeperSlime slime;
    private int growTiredTimer;

    public SlimeCreeperAttackGoal(CreeperSlime slime) {
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
            return (!(livingentity instanceof Player) || !((Player) livingentity).getAbilities().invulnerable)
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
        } else if (livingentity instanceof Player && ((Player) livingentity).getAbilities().invulnerable) {
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
        ((SlimeCreeperMovementController) this.slime.getMoveControl()).setDirection(this.slime.getYRot(),
                this.slime.isDealsDamage());
    }
}
