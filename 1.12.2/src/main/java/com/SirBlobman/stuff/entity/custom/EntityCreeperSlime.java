package com.SirBlobman.stuff.entity.custom;

import com.SirBlobman.stuff.BSConfig;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityCreeperSlime extends EntityCustomCreeper {
    public EntityCreeperSlime(World world) {super(world);}
    
    @Override
    public void explode() {
        if(!world.isRemote) {
            int i = BSConfig.BLUE_SLIME_AMOUNT; 
            int s = BSConfig.BLUE_SLIME_SIZE;
            if(getPowered()) i *= 2;
            while(i > 0) {
                EntityBlueSlime ebs = new EntityBlueSlime(world);
                ebs.setPosition(posX, posY, posZ);
                NBTTagCompound nbt = new NBTTagCompound();
                ebs.writeEntityToNBT(nbt);
                nbt.setInteger("Size", s);
                ebs.readEntityFromNBT(nbt);
                world.spawnEntity(ebs);
                i--;
            } setDead();
        }
    }
}