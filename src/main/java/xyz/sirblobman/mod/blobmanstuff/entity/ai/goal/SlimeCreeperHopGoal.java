package xyz.sirblobman.mod.blobmanstuff.entity.ai.goal;

import java.util.EnumSet;

import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.Goal;

import xyz.sirblobman.mod.blobmanstuff.entity.EntityCreeperSlime;

public final class SlimeCreeperHopGoal extends Goal {
    private final EntityCreeperSlime slime;

    public SlimeCreeperHopGoal(EntityCreeperSlime slime) {
        this.slime = slime;
        setFlags(EnumSet.of(Flag.JUMP, Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        return !this.slime.isPassenger();
    }

    @Override
    public void tick() {
        MovementController moveControl = this.slime.getMoveControl();
        if (moveControl instanceof SlimeCreeperMovementController) {
            SlimeCreeperMovementController controller = (SlimeCreeperMovementController) moveControl;
            controller.setWantedMovement(1.0D);
        }
    }
}
