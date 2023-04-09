package xyz.sirblobman.mod.blobmanstuff.entity.ai.goal;

import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;

import xyz.sirblobman.mod.blobmanstuff.entity.EntityCreeperSlime;

public final class SlimeCreeperMovementController extends MovementController {
    private float yRot;
    private int jumpDelay;
    private final EntityCreeperSlime slime;
    private boolean isAggressive;

    public SlimeCreeperMovementController(EntityCreeperSlime p_i45821_1_) {
        super(p_i45821_1_);
        this.slime = p_i45821_1_;
        this.yRot = 180.0F * p_i45821_1_.yRot / (float) Math.PI;
    }

    public void setDirection(float p_179920_1_, boolean p_179920_2_) {
        this.yRot = p_179920_1_;
        this.isAggressive = p_179920_2_;
    }

    public void setWantedMovement(double p_179921_1_) {
        this.speedModifier = p_179921_1_;
        this.operation = MovementController.Action.MOVE_TO;
    }

    public void tick() {
        this.mob.yRot = this.rotlerp(this.mob.yRot, this.yRot, 90.0F);
        this.mob.yHeadRot = this.mob.yRot;
        this.mob.yBodyRot = this.mob.yRot;
        if (this.operation != MovementController.Action.MOVE_TO) {
            this.mob.setZza(0.0F);
        } else {
            this.operation = MovementController.Action.WAIT;
            if (this.mob.isOnGround()) {
                this.mob.setSpeed((float) (this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED)));
                if (this.jumpDelay-- <= 0) {
                    this.jumpDelay = this.slime.getJumpDelay();
                    if (this.isAggressive) {
                        this.jumpDelay /= 3;
                    }

                    this.slime.getJumpControl().jump();
                    if (this.slime.doPlayJumpSound()) {
                        this.slime.playSound(this.slime.getJumpSound(), this.slime.getSoundVolume(), this.slime.getSoundPitch());
                    }
                } else {
                    this.slime.xxa = 0.0F;
                    this.slime.zza = 0.0F;
                    this.mob.setSpeed(0.0F);
                }
            } else {
                this.mob.setSpeed((float) (this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED)));
            }

        }
    }
}
