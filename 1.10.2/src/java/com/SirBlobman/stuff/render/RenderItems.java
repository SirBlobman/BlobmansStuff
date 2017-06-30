package com.SirBlobman.stuff.render;

import com.SirBlobman.stuff.item.BItems;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;

public class RenderItems
{
	public static void renderItems()
	{
	//Slimeballs
		reg(BItems.blueSlimeball);
		reg(BItems.yellowSlimeball);
		reg(BItems.redSlimeball);
		reg(BItems.shinySlimeball);
		reg(BItems.blackSlimeball);
		
	//Armor
		reg(BItems.sirBlobmanCrown);
		reg(BItems.sirBlobmanChest);
		reg(BItems.sirBlobmanLegs);
		reg(BItems.sirBlobmanFeet);
		
		reg(BItems.blueHelmet);
		reg(BItems.blueChestplate);
		reg(BItems.blueLeggings);
		reg(BItems.blueBoots);
		
	//Tools
		reg(BItems.shinySword);
		reg(BItems.ultraShinySword);
	}
	
	private static void reg(Item i)
	{
		Minecraft mc = Minecraft.getMinecraft();
		RenderItem ri = mc.getRenderItem();
		ItemModelMesher imm = ri.getItemModelMesher();
		ModelResourceLocation mrl = new ModelResourceLocation(i.getRegistryName(), "inventory");
		
		imm.register(i, 0, mrl);
	}
}