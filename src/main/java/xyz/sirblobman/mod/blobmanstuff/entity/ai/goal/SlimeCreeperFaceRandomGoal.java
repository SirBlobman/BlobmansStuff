package xyz.sirblobman.mod.blobmanstuff.entity.ai.goal;

import java.util.EnumSet;
import java.util.Random;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.potion.Effects;

import xyz.sirblobman.mod.blobmanstuff.entity.EntityCreeperSlime;

public final class SlimeCreeperFaceRandomGoal extends Goal {
    private final EntityCreeperSlime slime;
    private float chosenDegrees;
    private int nextRandomizeTime;

    public SlimeCreeperFaceRandomGoal(EntityCreeperSlime slime) {
        this.slime = slime;
        setFlags(EnumSet.of(Flag.LOOK));
    }

    public boolean canUse() {
        LivingEntity target = this.slime.getTarget();
        boolean validTarget = (target == null);

        boolean grounded = this.slime.isOnGround();
        boolean water = this.slime.isInWater();
        boolean lava = this.slime.isInWater();
        boolean levitation = this.slime.hasEffect(Effects.LEVITATION);
        boolean validLocation = (grounded || water || lava || levitation);

        MovementController moveControl = this.slime.getMoveControl();
        boolean validControl = (moveControl instanceof SlimeCreeperMovementController);
        return (validTarget && validLocation && validControl);
    }

    public void tick() {
        if (--this.nextRandomizeTime <= 0) {
            Random random = this.slime.getRandom();
            this.nextRandomizeTime = 40 + random.nextInt(60);
            this.chosenDegrees = random.nextInt(360);
        }

        MovementController moveControl = this.slime.getMoveControl();
        if (moveControl instanceof SlimeCreeperMovementController) {
            SlimeCreeperMovementController controller = (SlimeCreeperMovementController) moveControl;
            controller.setDirection(this.chosenDegrees, false);
        }
    }
}
