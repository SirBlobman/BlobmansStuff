package com.SirBlobman.stuff.item.custom;

import com.SirBlobman.stuff.entity.custom.EntityProjectileItem;
import com.SirBlobman.stuff.item.BSItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class ItemSlimeWand extends QuickItem {
    public ItemSlimeWand() {
        super("slime_wand");
        setMaxStackSize(1);
        setHasSubtypes(true);
    }
    
    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (isInCreativeTab(tab)) {
            ItemStack is0 = new ItemStack(this, 1, 0);
            ItemStack is1 = new ItemStack(this, 1, 1);
            items.add(is0); items.add(is1);
        }
    }
    
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer ep, EnumHand eh) {
        ItemStack is = ep.getHeldItem(eh);
        double x = ep.posX, y = ep.posY, z = ep.posZ;
        SoundEvent slime = SoundEvents.BLOCK_SLIME_BREAK;
        SoundCategory category = SoundCategory.HOSTILE;
        world.playSound(null, x, y, z, slime, category, 1.0F, 1.0F);
        slime(world, ep, is);
        StatBase sb = StatList.getObjectUseStats(this);
        ep.addStat(sb);
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, is);
    }
    
    private void slime(World world, EntityPlayer ep, ItemStack is) {
        if(!world.isRemote) {
            int level = is.getItemDamage();
            EntityProjectileItem en = new EntityProjectileItem(world, ep);
            en.setItem((level == 0) ? new ItemStack(Items.SLIME_BALL) : new ItemStack(BSItems.SLIMEBALL_BLUE));
            en.setDamage(1.0F + level);
            en.shoot(ep, ep.rotationPitch, ep.rotationYaw, 0.0F, 1.5F, 0.0F);
            world.spawnEntity(en);
        }
    }
}