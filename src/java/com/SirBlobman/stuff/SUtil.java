package com.SirBlobman.stuff;

import com.SirBlobman.stuff.items.SlimeArmor;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SUtil 
{
	public static final String stuff = I18n.format("prefix.blobmans-stuff") + " ";
	
	/**
	 * Check if an entity has a certain kind of armor on
	 * @param el Entity to check
	 * @param helmet ItemStack of the helmet
	 * @param chestplate ItemStack of the chestplate
	 * @param leggings ItemStack of the leggings
	 * @param boots ItemStack of the boots
	 * @return <b>true</b> if the entity is wearing that armor<br> <b>false</b> if they aren't
	 * @see EntityLiving
	 * @see EntityLiving#getItemStackFromSlot(EntityEquipmentSlot)
	 * @see ItemStack
	 */
	public static boolean isWearingFullSet(EntityLivingBase el, ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots)
	{
		ItemStack head = el.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
		ItemStack chest = el.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
		ItemStack legs = el.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
		ItemStack feet = el.getItemStackFromSlot(EntityEquipmentSlot.FEET);
		return (helmet.isItemEqual(head) && chestplate.isItemEqual(chest) && leggings.isItemEqual(legs) && boots.isItemEqual(feet));
	}
	
	/**
	 * Check if an Entity has Slime Armor on
	 * @param el EntityLiving of the entity to check
	 * @return <b>true</b> if the entity is wearing a full set of Slime Armor<br><b>false</b> if the Entity is missing an armor piece of doesn't have slime armor
	 */
	public static boolean isWearingSlimeArmor(EntityLivingBase el)
	{
		Item helmet = el.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem();
		Item chest = el.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem();
		Item legs = el.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem();
		Item boot = el.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem();
		
		return (helmet instanceof SlimeArmor && chest instanceof SlimeArmor && legs instanceof SlimeArmor && boot instanceof SlimeArmor);
	}
	
	/**
	 * Register a full class of events
	 * @param listener Class full of listeners
	 * @see SubscribeEvent
	 * @see Object
	 * @see MinecraftForge#EVENT_BUS
	 */
	public static void regEvents(Object listener)
	{
		EventBus eb = MinecraftForge.EVENT_BUS;
		eb.register(listener);
	}
	
	/**
	 * Print a message to the FML Logger
	 * @param msg Message to log
	 * @see String
	 * @see FMLLog
	 */
	public static void print(String msg) {FMLLog.info(stuff + msg);}
}