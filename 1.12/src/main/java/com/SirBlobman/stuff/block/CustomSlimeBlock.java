package com.SirBlobman.stuff.block;

import com.SirBlobman.stuff.creative.SlimyTabs;

import net.minecraft.block.BlockSlime;
import net.minecraft.block.SoundType;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class CustomSlimeBlock extends BlockSlime {
	private final double bounceHeight;
	private final String color;
	public CustomSlimeBlock(String color, double bh) {
		super();
		this.color = color;
		this.bounceHeight = -1.0D * bh;
		String name = color + "_slime";
		setUnlocalizedName(name);
		setRegistryName(name);
		setSoundType(SoundType.SLIME);
		setCreativeTab(SlimyTabs.BLOCKS);
	}
	
	@Override
	public void onLanded(World w, Entity e) {
		boolean s = e.isSneaking();
		boolean ey = (e.motionY < 0.0D);
		if(s) super.onLanded(w, e);
		else if(ey) {
			double y = e.motionY;
			double d = (bounceHeight * y);
			e.motionY = d;
		}
	}
	
	public String getColor() {return color;}
	public double getBounceHeight() {return (-1 * bounceHeight);}
}