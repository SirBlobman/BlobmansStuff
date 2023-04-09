package xyz.sirblobman.mod.blobmanstuff.entity.ai.goal;

import java.util.EnumSet;
import java.util.Random;

import net.minecraft.entity.ai.controller.JumpController;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.pathfinding.PathNavigator;

import xyz.sirblobman.mod.blobmanstuff.entity.EntityCreeperSlime;

public final class SlimeCreeperFloatGoal extends Goal {
    private final EntityCreeperSlime slime;

    public SlimeCreeperFloatGoal(EntityCreeperSlime slime) {
        this.slime = slime;
        setFlags(EnumSet.of(Flag.JUMP, Flag.MOVE));

        PathNavigator navigation = slime.getNavigation();
        navigation.setCanFloat(true);
    }

    public boolean canUse() {
        boolean water = this.slime.isInWater();
        boolean lava = this.slime.isInLava();
        boolean liquid = (water || lava);

        MovementController moveControl = this.slime.getMoveControl();
        boolean validControl = (moveControl instanceof SlimeCreeperMovementController);

        return (liquid && validControl);
    }

    @Override
    public void tick() {
        Random random = this.slime.getRandom();
        if (random.nextFloat() < 0.8F) {
            JumpController jumpControl = this.slime.getJumpControl();
            jumpControl.jump();
        }

        MovementController moveControl = this.slime.getMoveControl();
        if (moveControl instanceof SlimeCreeperMovementController) {
            SlimeCreeperMovementController controller = (SlimeCreeperMovementController) moveControl;
            controller.setWantedMovement(1.2D);
        }
    }
}
