package com.SirBlobman.stuff.item.custom;

import com.SirBlobman.stuff.BSTabs;
import com.SirBlobman.stuff.BlobmansStuff;

import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

public class ItemSirBlobmanArmor extends ItemArmor {
    private static final ArmorMaterial SIRBLOBMAN = EnumHelper.addArmorMaterial("SIRBLOBMAN", BlobmansStuff.MODID + ":sirblobman", 1024, new int[] {5, 5, 5, 5}, 30, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 2.0F);
    public ItemSirBlobmanArmor(EntityEquipmentSlot slot) {
        super(SIRBLOBMAN, slot == EntityEquipmentSlot.LEGS ? 2 : 1, slot);
        String name = "sirblobman" + getType(slot);
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(BSTabs.ITEMS);
    }
    
    private String getType(EntityEquipmentSlot slot) {
        switch(slot) {
            case HEAD:  return "_crown";
            case CHEST: return "_suit";
            case LEGS:  return "_pants";
            case FEET:  return "_shoes";
            default:    return "_armor";
        }
    }
}