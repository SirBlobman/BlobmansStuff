package com.SirBlobman.stuff.utility;

import static net.minecraft.inventory.EntityEquipmentSlot.*;

import com.SirBlobman.stuff.item.SlimeArmor;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class EntityUtil extends Util {
	public static boolean isWearingFullSet(EntityLivingBase elb, ItemStack h, ItemStack c, ItemStack l, ItemStack b) {
		ItemStack head = elb.getItemStackFromSlot(HEAD);
		ItemStack ches = elb.getItemStackFromSlot(CHEST);
		ItemStack legs = elb.getItemStackFromSlot(LEGS);
		ItemStack feet = elb.getItemStackFromSlot(FEET);
		if(head == null || ches == null || legs == null || feet == null) return false;
		boolean b1 = h.equals(head), b2 = c.equals(ches),
		b3 = l.equals(legs), b4 = b.equals(feet);
		boolean b5 = (b1 && b2 && b3 && b4);
		return b5;
	}
	
	public static boolean isWearingSlimeArmor(EntityLivingBase elb) {
		ItemStack head = elb.getItemStackFromSlot(HEAD);
		ItemStack ches = elb.getItemStackFromSlot(CHEST);
		ItemStack legs = elb.getItemStackFromSlot(LEGS);
		ItemStack feet = elb.getItemStackFromSlot(FEET);
		if(head == null || ches == null || legs == null || feet == null) return false;
		
		Item h = head.getItem(), c = ches.getItem(),
		l = legs.getItem(), f = feet.getItem();
		
		boolean b1 = (h instanceof SlimeArmor), b2 = (c instanceof SlimeArmor),
		b3 = (l instanceof SlimeArmor), b4 = (f instanceof SlimeArmor);
		boolean b5 = (b1 && b2 && b3 && b4);
		return b5;
	}
}