package com.SirBlobman.stuff.item;

import com.SirBlobman.stuff.Stuff;

import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class BItems 
{
//Armor Materials
	public static ArmorMaterial SirBlobman = EnumHelper.addArmorMaterial("sirblobman", Stuff.MODID + ":sirblobman", 100, new int[] {20, 0, 0, 0}, 30, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 2);
	public static ArmorMaterial BlueSlime = EnumHelper.addArmorMaterial("BlueSlime", Stuff.MODID + ":blue_slime", 100, new int[] {5, 5, 5, 5}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 2);
//Tool Materials
	public static ToolMaterial UltraShiny = EnumHelper.addToolMaterial("ultrashiny", 50, 999999999, 800.0f, 99996.0f, 1000);
	public static ToolMaterial Shiny = EnumHelper.addToolMaterial("shiny", 50, 1000000, 80.0f, 96.0f, 100);
//Items
	//Slimeballs
	public static Item blueSlimeball = new Slimeball("blue");
	public static Item yellowSlimeball = new Slimeball("yellow");
	public static Item redSlimeball = new Slimeball("red");
	public static Item shinySlimeball = new Slimeball("shiny");
	public static Item blackSlimeball = new Slimeball("black");
	
	//Tools
	public static Item shinySword = new ShinySword("", Shiny);
	public static Item ultraShinySword = new ShinySword("ultra", UltraShiny);
	
	//Armor
	public static Item sirBlobmanCrown = new SirBlobmanArmor("crown", SirBlobman, 1, EntityEquipmentSlot.HEAD);
	public static Item sirBlobmanChest = new SirBlobmanArmor("chest", SirBlobman, 1, EntityEquipmentSlot.CHEST);
	public static Item sirBlobmanLegs = new SirBlobmanArmor("legs", SirBlobman, 2, EntityEquipmentSlot.LEGS);
	public static Item sirBlobmanFeet = new SirBlobmanArmor("feet", SirBlobman, 1, EntityEquipmentSlot.FEET);
	
	public static Item blueHelmet = new SlimeArmor("blue", "helmet", BlueSlime, 1, EntityEquipmentSlot.HEAD, blueSlimeball);
	public static Item blueChestplate = new SlimeArmor("blue", "chestplate", BlueSlime, 1, EntityEquipmentSlot.CHEST, blueSlimeball);
	public static Item blueLeggings = new SlimeArmor("blue", "leggings", BlueSlime, 2, EntityEquipmentSlot.LEGS, blueSlimeball);
	public static Item blueBoots = new SlimeArmor("blue", "boots", BlueSlime, 1, EntityEquipmentSlot.FEET, blueSlimeball);
	
	//Wands
	public static Item slimeWandGreen = new SlimeWand(1);
	public static Item slimeWandBlue = new SlimeWand(2);
	
	public static void createItems()
	{
		r(blueSlimeball);
		r(yellowSlimeball);
		r(redSlimeball);
		r(shinySlimeball);
		r(blackSlimeball);
		
		r(shinySword);
		r(ultraShinySword);
		
		r(sirBlobmanCrown);
		r(sirBlobmanChest);
		r(sirBlobmanLegs);
		r(sirBlobmanFeet);
		
		r(blueHelmet);
		r(blueChestplate);
		r(blueLeggings);
		r(blueBoots);
		
		r(slimeWandGreen);
		r(slimeWandBlue);
	}
	
	private static void r(Item i)
	{
		GameRegistry.register(i);
	}
}