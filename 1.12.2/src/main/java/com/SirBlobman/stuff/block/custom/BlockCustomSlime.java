package com.SirBlobman.stuff.block.custom;

import com.SirBlobman.stuff.BSTabs;

import net.minecraft.block.BlockSlime;
import net.minecraft.block.SoundType;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class BlockCustomSlime extends BlockSlime {
    private final double bounceHeight;
    private final String color;
    
    /**
     * Create an instance of a custom slime block
     * @param color Color of the slime block
     * @param bounceHeight How high does the block bounce you back?
     */
    public BlockCustomSlime(String color, double bounceHeight) {
        super();
        this.color = color;
        this.bounceHeight = -bounceHeight;
        String name = color + "_slime_block";
        setRegistryName(name);
        setUnlocalizedName(name);
        setSoundType(SoundType.SLIME);
        setCreativeTab(BSTabs.BLOCKS);
    }
    
    public String getColor() {return color;}
    public double getBounceHeight() {return -bounceHeight;}
    
    @Override
    public void onLanded(World world, Entity en) {
        boolean sneak = en.isSneaking();
        boolean movingY = (en.motionY < 0.0D);
        if(sneak) super.onLanded(world, en);
        else if(movingY) {
            double motionY = en.motionY;
            double bounce = (bounceHeight * motionY);
            en.motionY = bounce;
        }
    }
}