package com.SirBlobman.stuff.block;

import com.SirBlobman.stuff.creative.SlimyTabs;

import net.minecraft.block.BlockTNT;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CustomTNT extends BlockTNT {
	public CustomTNT() {
		super();
		String name = "black_slime_tnt";
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(SlimyTabs.BLOCKS);
	}
	
	@Override
    public void explode(World w, BlockPos bp, IBlockState ibs, EntityLivingBase lighter) {
		if(!w.isRemote) {
			boolean explode = ibs.getValue(EXPLODE);
			if(explode) {
				int tnt = 0;
				while(tnt < 100) {
					double x = bp.getX() + 0.5D;
					double y = bp.getY() + 0.0D;
					double z = bp.getZ() + 0.5D;
					w.createExplosion(lighter, x, y, z, 50.0F, true);
					
					SoundEvent se = SoundEvents.ENTITY_TNT_PRIMED;
					SoundCategory sc = SoundCategory.BLOCKS;
					float v = 1.0F;
					float p = 1.0F;
					w.playSound(null, x, y, z, se, sc, v, p);
					tnt++;
				}
			}
		}
	}
}