package com.SirBlobman.stuff.world.dimension.slime;

import com.SirBlobman.stuff.world.BlobDimensions;

import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkGenerator;

public class SlimeWP extends WorldProvider
{
	@Override
	public DimensionType getDimensionType()
	{
		DimensionType SLIME = BlobDimensions.SLIME;
		return SLIME;
	}
	
	@Override
	public String getSaveFolder()
	{
		String save = "Slime";
		return save;
	}
	
	@Override
	public IChunkGenerator createChunkGenerator()
	{
		SlimeCG cg = new SlimeCG(worldObj);
		return ch;
	}
}