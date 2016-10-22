package com.SirBlobman.stuff.blocks;

import com.SirBlobman.stuff.items.BItems;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BBlocks 
{
	public static CustomSlimeBlock blueSlime = new CustomSlimeBlock("blue", 1.5);
	public static CustomSlimeBlock yellowSlime = new CustomSlimeBlock("yellow", 2.0);
	public static CustomSlimeBlock redSlime = new CustomSlimeBlock("red", 3.0);
	public static CustomSlimeBlock shinySlime = new CustomSlimeBlock("shiny", 10.0);
	public static CustomSlimeBlock blackSlime = new CustomSlimeBlock("black", 100.0);
	
	public static void createBlocks()
	{
		rSlime(blueSlime, BItems.blueSlimeball);
		rSlime(yellowSlime, BItems.yellowSlimeball);
		rSlime(redSlime, BItems.redSlimeball);
		rSlime(shinySlime, BItems.shinySlimeball);
		rSlime(blackSlime, BItems.blackSlimeball);
	}
	
	private static void rSlime(CustomSlimeBlock b, Item slimeball)
	{
		GameRegistry.register(b);
		GameRegistry.register(new CustomSlimeBlockItem(b).setRegistryName(b.getRegistryName()));
		GameRegistry.addRecipe(new ItemStack(b), new Object[] {"SSS", "SSS", "SSS", 'S', slimeball});
	}
}