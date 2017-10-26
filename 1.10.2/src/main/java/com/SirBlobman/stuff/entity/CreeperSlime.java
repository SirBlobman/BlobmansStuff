package com.SirBlobman.stuff.entity;

import com.SirBlobman.stuff.config.Config;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class CreeperSlime extends CustomCreeper {
	public CreeperSlime(World w) {super(w);}
	
	@Override
	public void explode() {
		if(!worldObj.isRemote) {
			int i = Config.CREEPER_SLIME_DEATH_BLUE_SLIME_AMOUNT;
			int s = Config.CREEPER_SLIME_DEATH_BLUE_SLIME_SIZE;
			if(getPowered()) i *= 2;
			while(i > 0) {
				BlueSlime bs = new BlueSlime(worldObj);
				bs.setPosition(posX, posY, posZ);
				NBTTagCompound nbt = new NBTTagCompound();
				bs.writeEntityToNBT(nbt);
				nbt.setInteger("Size", s);
				bs.readEntityFromNBT(nbt);
				worldObj.spawnEntityInWorld(bs);
				i--;
			}
			setDead();
		}
	}
}