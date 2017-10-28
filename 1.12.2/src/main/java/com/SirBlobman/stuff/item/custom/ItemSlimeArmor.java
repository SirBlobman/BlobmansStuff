package com.SirBlobman.stuff.item.custom;

import com.SirBlobman.stuff.BSTabs;
import com.SirBlobman.stuff.utility.ItemUtil;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemSlimeArmor extends ItemArmor {
    private final String color;
    private final ItemStack repair;
    public ItemSlimeArmor(String color, ArmorMaterial mat, EntityEquipmentSlot slot, ItemStack repair) {
        super(mat, slot == EntityEquipmentSlot.LEGS ? 2 : 1, slot);
        this.color = color;
        this.repair = repair;
        String name = color + "_slime" + getType(slot);
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(BSTabs.ITEMS);
    }
    
    private String getType(EntityEquipmentSlot slot) {
        switch(slot) {
            case HEAD:  return "_helmet";
            case CHEST: return "_chestplate";
            case LEGS:  return "_leggings";
            case FEET:  return "_boots";
            default:    return "_armor";
        }
    }
    
    @Override
    public boolean getIsRepairable(ItemStack is1, ItemStack is2) {
        boolean equal = (repair.equals(is2));
        boolean repair = (equal ? true : super.getIsRepairable(is1, is2));
        return repair;
    }
    
    @Override
    public void onArmorTick(World world, EntityPlayer ep, ItemStack is) {
        boolean allArmor = ItemUtil.isWearingFullSlimeArmor(ep);
        if(allArmor) {
            PotionEffect jump = new PotionEffect(MobEffects.JUMP_BOOST, 20, 1);
            ep.addPotionEffect(jump);
        }
    }
    
    public String getColor() {return color;}
}