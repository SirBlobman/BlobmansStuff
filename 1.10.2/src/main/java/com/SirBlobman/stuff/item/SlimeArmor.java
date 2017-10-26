package com.SirBlobman.stuff.item;

import com.SirBlobman.stuff.creative.SlimyTabs;
import com.SirBlobman.stuff.utility.EntityUtil;
import com.SirBlobman.stuff.utility.Util;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class SlimeArmor extends ItemArmor {
	private final String color, type;
	private final ItemStack repair;
	public SlimeArmor(String color, ArmorMaterial mat, String type, int i, EntityEquipmentSlot slot, Item repair) {
		super(mat, i, slot);
		this.color = color;
		this.type = type;
		this.repair = new ItemStack(repair);
		String uname = color + "_slime_" + type;
		String reg = "armor/" + uname;
		setUnlocalizedName(uname);
		setRegistryName(reg);
		setCreativeTab(SlimyTabs.ARMOR);
		canRepair = true;
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack is) {
		String type = getType();
		String f = Util.format("armor." + type, "Slime");
		String t = f.trim();
		return t;
	}
	
	@Override
	public void addInformation(ItemStack is, EntityPlayer ep, List<String> list, boolean adv) {
		String color = getColor();
		String c = Util.format("color." + color);
		String f = Util.format("color.color", c);
		String t = f.trim();
		list.add(t);
	}
	
	@Override
	public boolean getIsRepairable(ItemStack is1, ItemStack is2) {
		boolean e = (repair.equals(is2));
		boolean r = e ? true : super.getIsRepairable(is1, is2);
		return r;
	}
	
	@Override
	public void onArmorTick(World w, EntityPlayer ep, ItemStack is) {
		boolean wear = EntityUtil.isWearingSlimeArmor(ep);
		if(wear) {
			Potion j = MobEffects.JUMP_BOOST;
			PotionEffect pe = new PotionEffect(j, 1, 1);
			ep.addPotionEffect(pe);
		}
	}
	
	public String getColor() {return color;}
	public String getType() {return type;}
}