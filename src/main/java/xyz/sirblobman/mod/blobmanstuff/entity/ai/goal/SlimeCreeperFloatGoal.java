package xyz.sirblobman.mod.blobmanstuff.entity.ai.goal;

import java.util.EnumSet;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.ai.control.JumpControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;

import xyz.sirblobman.mod.blobmanstuff.entity.CreeperSlime;

public final class SlimeCreeperFloatGoal extends Goal {
    private final CreeperSlime slime;

    public SlimeCreeperFloatGoal(CreeperSlime slime) {
        this.slime = slime;
        setFlags(EnumSet.of(Flag.JUMP, Flag.MOVE));

        PathNavigation navigation = slime.getNavigation();
        navigation.setCanFloat(true);
    }

    public boolean canUse() {
        boolean water = this.slime.isInWater();
        boolean lava = this.slime.isInLava();
        boolean liquid = (water || lava);

        MoveControl moveControl = this.slime.getMoveControl();
        boolean validControl = (moveControl instanceof SlimeCreeperMovementController);

        return (liquid && validControl);
    }

    @Override
    public void tick() {
        RandomSource random = this.slime.getRandom();
        if (random.nextFloat() < 0.8F) {
            JumpControl jumpControl = this.slime.getJumpControl();
            jumpControl.jump();
        }

        MoveControl moveControl = this.slime.getMoveControl();
        if (moveControl instanceof SlimeCreeperMovementController controller) {
            controller.setWantedMovement(1.2D);
        }
    }
}
