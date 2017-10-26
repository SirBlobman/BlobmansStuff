package com.SirBlobman.stuff.item;

import static com.SirBlobman.stuff.Stuff.MODID;
import static net.minecraft.inventory.EntityEquipmentSlot.*;

import com.SirBlobman.stuff.creative.SlimyTabs;
import com.SirBlobman.stuff.utility.Util;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class BItems {
	public static final ArmorMaterial SIRBLOBMAN = EnumHelper.addArmorMaterial("SirBlobman", MODID + ":SirBlobman", 100, new int[] {0, 0, 0, 20}, 30, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 2);
	public static final ArmorMaterial BLUE_SLIME = EnumHelper.addArmorMaterial("BlueSlime", MODID + ":blue_slime", 100, new int[] {5, 5, 5, 5}, 10, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 2);
	
	public static final ToolMaterial ULTRA_SHINY = EnumHelper.addToolMaterial("UltraShiny", 50, Integer.MAX_VALUE, Float.MAX_VALUE, Float.POSITIVE_INFINITY, Integer.MAX_VALUE);
	public static final ToolMaterial SHINY = EnumHelper.addToolMaterial("Shiny", 50, 1000000, 80.0F, 96.0F, 100);
	
	public static final Slimeball SLIMEBALL_BLUE = new Slimeball("blue");
	public static final Slimeball SLIMEBALL_YELLOW = new Slimeball("yellow");
	public static final Slimeball SLIMEBALL_RED = new Slimeball("red");
	public static final Slimeball SLIMEBALL_SHINY = new Slimeball("shiny");
	public static final Slimeball SLIMEBALL_BLACK = new Slimeball("black");
	
	public static final ShinySword SHINY_SWORD = new ShinySword(null, SHINY);
	public static final ShinySword ULTRA_SWORD = new ShinySword("ultra", ULTRA_SHINY);

	public static final SirBlobmanArmor BLOBMAN_CROWN = new SirBlobmanArmor("crown", 1, HEAD);
	public static final SirBlobmanArmor BLOBMAN_CHEST = new SirBlobmanArmor("chest", 1, CHEST);
	public static final SirBlobmanArmor BLOBMAN_LEGS = new SirBlobmanArmor("legs", 2, LEGS);
	public static final SirBlobmanArmor BLOBMAN_FEET = new SirBlobmanArmor("feet", 1, FEET);

	public static final SlimeArmor BLUE_SLIME_HELMET = new SlimeArmor("blue", BLUE_SLIME, "helmet", 1, HEAD, SLIMEBALL_BLUE);
	public static final SlimeArmor BLUE_SLIME_CHESTPLATE = new SlimeArmor("blue", BLUE_SLIME, "chestplate", 1, CHEST, SLIMEBALL_BLUE);
	public static final SlimeArmor BLUE_SLIME_LEGGINGS = new SlimeArmor("blue", BLUE_SLIME, "leggings", 2, LEGS, SLIMEBALL_BLUE);
	public static final SlimeArmor BLUE_SLIME_BOOTS = new SlimeArmor("blue", BLUE_SLIME, "boots", 1, FEET, SLIMEBALL_BLUE);
	
	public static final SlimeWand SLIME_WAND_GREEN = new SlimeWand(1);
	public static final SlimeWand SLIME_WAND_BLUE = new SlimeWand(2);
	public static final Item WAND_STICK = new Item().setRegistryName("wand_stick").setUnlocalizedName("wand_stick").setCreativeTab(SlimyTabs.ITEMS);
	
	public static void items() {
		Util.print("Registering Items...");
		regAll(
			SLIMEBALL_BLUE, SLIMEBALL_YELLOW, 
			SLIMEBALL_RED, SLIMEBALL_SHINY, 
			SLIMEBALL_BLACK,
			
			SHINY_SWORD, ULTRA_SWORD,
			
			BLOBMAN_CROWN, BLOBMAN_CHEST,
			BLOBMAN_LEGS, BLOBMAN_FEET,
			
			BLUE_SLIME_HELMET, BLUE_SLIME_CHESTPLATE,
			BLUE_SLIME_LEGGINGS, BLUE_SLIME_BOOTS,
			
			SLIME_WAND_GREEN, SLIME_WAND_BLUE, WAND_STICK
		);
	}
	
	public static void regAll(Item... ii) {
		for(Item i : ii) {
			GameRegistry.register(i);
		}
	}
}