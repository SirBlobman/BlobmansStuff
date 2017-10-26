package com.SirBlobman.stuff.entity.custom.ai;

import com.SirBlobman.stuff.entity.custom.EntityCustomCreeper;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAICustomCreeperSwell extends EntityAIBase {
    EntityCustomCreeper creeper;
    EntityLivingBase target;
    
    public EntityAICustomCreeperSwell(EntityCustomCreeper ecc) {
        this.creeper = ecc;
        setMutexBits(1);
    }
    
    @Override
    public boolean shouldExecute() {
        EntityLivingBase elb = creeper.getAttackTarget();
        return creeper.getCreeperState() > 0 || elb != null && creeper.getDistanceSq(elb) < 9.0D;
    }
    
    @Override
    public void startExecuting() {
        creeper.getNavigator().clearPath();
        target = creeper.getAttackTarget();
    }
    
    @Override
    public void resetTask() {target = null;}
    
    @Override
    public void updateTask() {
        if(target == null) creeper.setCreeperState(-1);
        else if(creeper.getDistanceSq(target)> 49.0D) creeper.setCreeperState(-1);
        else if(!creeper.getEntitySenses().canSee(target)) creeper.setCreeperState(-1);
        else creeper.setCreeperState(1);
    }
}