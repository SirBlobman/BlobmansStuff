package com.SirBlobman.stuff.blocks;

import com.SirBlobman.stuff.creative.tabs.SlimyTabs;

import net.minecraft.block.BlockSlime;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class CustomSlimeBlock extends BlockSlime 
{
	private final double bounceHeight;
	private final String color;
	
	/**
	 * Instance of a Custom Slime Block
	 * <br/> <b>Usage:</b> new CustomSlimeBlock(color, bh)
	 * @param color Color of the slime block, used in the name
	 * @param bh Bounce height of the slime block
	 * @see CustomSlimeBlock
	 * @see BlockSlime
	 * @see String
	 * @see Double
	 */
	public CustomSlimeBlock(String color, double bh)
	{
		super();
		setRegistryName(color + "_slime");
		setUnlocalizedName(color + "_slime");
		setCreativeTab(SlimyTabs.Blocks);
		bounceHeight = -1.0 * bh;
		this.color = color;
	}
	
	@Override
	public void onLanded(World w, Entity e)
	{
		if(e.isSneaking())
		{
			super.onLanded(w, e);
		}
		else if(e.motionY < 0.0)
		{
			e.motionY = (bounceHeight * e.motionY);
		}
	}
	
	public String getColor()
	{
		return color;
	}
}