package xyz.sirblobman.mod.blobmanstuff.entity.ai.goal;

import java.util.EnumSet;

import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;

import xyz.sirblobman.mod.blobmanstuff.entity.CreeperSlime;

public final class SlimeCreeperFaceRandomGoal extends Goal {
    private final CreeperSlime slime;
    private float chosenDegrees;
    private int nextRandomizeTime;

    public SlimeCreeperFaceRandomGoal(CreeperSlime slime) {
        this.slime = slime;
        setFlags(EnumSet.of(Flag.LOOK));
    }

    public boolean canUse() {
        LivingEntity target = this.slime.getTarget();
        boolean validTarget = (target == null);

        boolean grounded = this.slime.isOnGround();
        boolean water = this.slime.isInWater();
        boolean lava = this.slime.isInWater();
        boolean levitation = this.slime.hasEffect(MobEffects.LEVITATION);
        boolean validLocation = (grounded || water || lava || levitation);

        MoveControl moveControl = this.slime.getMoveControl();
        boolean validControl = (moveControl instanceof SlimeCreeperMovementController);
        return (validTarget && validLocation && validControl);
    }

    public void tick() {
        if (--this.nextRandomizeTime <= 0) {
            RandomSource random = this.slime.getRandom();
            this.nextRandomizeTime = 40 + random.nextInt(60);
            this.chosenDegrees = random.nextInt(360);
        }

        MoveControl moveControl = this.slime.getMoveControl();
        if (moveControl instanceof SlimeCreeperMovementController controller) {
            controller.setDirection(this.chosenDegrees, false);
        }
    }
}
