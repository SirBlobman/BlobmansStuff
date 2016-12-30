package com.SirBlobman.stuff.world;

import com.SirBlobman.stuff.Stuff;
import com.SirBlobman.stuff.world.dimension.slime.SlimeWP;

import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

public class BlobDimensions 
{
	public static DimensionType SLIME;
	
	public static void init()
	{
		types();
		dimensions();
	}
	
	private static void types()
	{
		SLIME = DimensionType.register(Stuff.MODID, "_slime", 19, SlimeWP.class, false);
	}
	
	private static void dimensions()
	{
		DimensionManager.registerDimension(19, SLIME);
	}
}