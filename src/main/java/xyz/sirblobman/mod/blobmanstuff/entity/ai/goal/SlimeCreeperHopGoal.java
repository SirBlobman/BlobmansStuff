package xyz.sirblobman.mod.blobmanstuff.entity.ai.goal;

import java.util.EnumSet;

import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;

import xyz.sirblobman.mod.blobmanstuff.entity.CreeperSlime;

public final class SlimeCreeperHopGoal extends Goal {
    private final CreeperSlime slime;

    public SlimeCreeperHopGoal(CreeperSlime slime) {
        this.slime = slime;
        setFlags(EnumSet.of(Flag.JUMP, Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        return !this.slime.isPassenger();
    }

    @Override
    public void tick() {
        MoveControl moveControl = this.slime.getMoveControl();
        if (moveControl instanceof SlimeCreeperMovementController controller) {
            controller.setWantedMovement(1.0D);
        }
    }
}
