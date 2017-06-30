package com.SirBlobman.stuff.entity;

import com.SirBlobman.stuff.Stuff;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class CreeperSlime extends CustomCreeper 
{
	public CreeperSlime(World w)
	{
		super(w);
	}
	
	@Override
	public void explode()
	{
		if(!worldObj.isRemote)
		{
			int i = Stuff.creeperSlimeSpawnAmount;
			int size = Stuff.creeperSlimeBlueSize;
			if(getPowered()) i *= 2; 
			while(i > 0)
			{
				BlueSlime bs = new BlueSlime(worldObj);
				bs.setPosition(posX, posY, posZ);
				NBTTagCompound data = new NBTTagCompound();
				bs.writeEntityToNBT(data);
				data.setInteger("Size", size);
				bs.readEntityFromNBT(data);
				worldObj.spawnEntityInWorld(bs);
				i--;
			}
		}
	}
}
