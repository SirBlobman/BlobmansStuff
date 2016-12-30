package com.SirBlobman.stuff.recipe;

import com.SirBlobman.stuff.block.BBlocks;
import com.SirBlobman.stuff.item.BItems;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BRecipes
{
	public static void createRecipes()
	{
	//ItemStacks
		ItemStack goldIngot = new ItemStack(Items.GOLD_INGOT, 1, 0);
		ItemStack blueSlimeball = new ItemStack(BItems.blueSlimeball);
		ItemStack inkSack = new ItemStack(Items.DYE);
		ItemStack lapisLazuli = new ItemStack(Items.DYE, 1, 4);
		ItemStack redDye = new ItemStack(Items.DYE, 1, 1);
		ItemStack boneMeal = new ItemStack(Items.DYE, 1, 15);
		ItemStack yellowDye = new ItemStack(Items.DYE, 1, 11);
		ItemStack lightBlueWool = new ItemStack(Blocks.WOOL, 1, 3);
	
	//SirBlobman Armor
		aR(new ItemStack(BItems.sirBlobmanCrown), new Object[] {"G G", "GBG", "GGG", 'G', goldIngot, 'B', BItems.blueSlimeball});
		aR(new ItemStack(BItems.sirBlobmanChest), new Object[] {"W W", "WWW", "WWW", 'W', lightBlueWool});
		aR(new ItemStack(BItems.sirBlobmanLegs), new Object[] {"WWW", "W W", "W W", 'W', lightBlueWool});
		aR(new ItemStack(BItems.sirBlobmanFeet), new Object[] {"W W", "W W", 'W', lightBlueWool});
		
	//Blue Slime Armor
		aR(new ItemStack(BItems.blueHelmet), new Object[] {"BBB", "B B", 'B', blueSlimeball});
		aR(new ItemStack(BItems.blueChestplate), new Object[] {"B B", "BBB", "BBB", 'B', blueSlimeball});
		aR(new ItemStack(BItems.blueLeggings), new Object[] {"BBB", "B B", "B B", 'B', blueSlimeball});
		aR(new ItemStack(BItems.blueLeggings), new Object[] {"B B", "B B", 'B', blueSlimeball});
		
	//Slimeballs
		aSR(blueSlimeball, new Object[] {Items.SLIME_BALL, lapisLazuli});
		aR(new ItemStack(BItems.yellowSlimeball), new Object[] {"BBB", "BGB", "ByB", 'B', blueSlimeball, 'G', goldIngot, 'y', yellowDye});
		aR(new ItemStack(BItems.redSlimeball), new Object[] {"YYY", "YRY", "YrY", 'Y', BItems.yellowSlimeball, 'R', Items.REDSTONE, 'r', redDye});
		aR(new ItemStack(BItems.shinySlimeball), new Object[] {"RRR", "RNR", "RwR", 'R', BItems.redSlimeball, 'N', Items.NETHER_STAR, 'w', boneMeal});
		aR(new ItemStack(BItems.blackSlimeball), new Object[] {"SSS", "SDS", "SiS", 'S', BItems.shinySlimeball, 'D', Blocks.DRAGON_EGG, 'i', inkSack});
		
	//Swords
		aR(new ItemStack(BItems.shinySword), new Object[] {"S  ", " S ", "  D", 'S', BItems.shinySlimeball, 'D', Items.DIAMOND_SWORD});
		aR(new ItemStack(BItems.ultraShinySword), new Object[] {"S  ", " S ", "  D", 'S', BBlocks.shinySlime, 'D', Blocks.DIAMOND_BLOCK});
	
	//Smelting
		aS(new ItemStack(Blocks.SLIME_BLOCK), new ItemStack(BBlocks.blueSlime), 16.0f);
	}
	
	private static void aR(ItemStack output, Object[] input)
	{
		GameRegistry.addRecipe(output, input);
	}
	
	
	private static void aSR(ItemStack output, Object[] inputs)
	{
		GameRegistry.addShapelessRecipe(output, inputs);
	}
	
	private static void aS(ItemStack output, ItemStack input, float xp)
	{
		GameRegistry.addSmelting(input, output, xp);
	}
}