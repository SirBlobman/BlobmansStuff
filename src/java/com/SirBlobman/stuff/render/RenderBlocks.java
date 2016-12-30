package com.SirBlobman.stuff.render;

import com.SirBlobman.stuff.block.BBlocks;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;

public class RenderBlocks
{
	public static void renderBlocks()
	{
		reg(BBlocks.blueSlime);
		reg(BBlocks.yellowSlime);
		reg(BBlocks.redSlime);
		reg(BBlocks.shinySlime);
		reg(BBlocks.blackSlime);
	}
	
	private static void reg(Block b)
	{
		Minecraft mc = Minecraft.getMinecraft();
		RenderItem ri = mc.getRenderItem();
		ItemModelMesher imm = ri.getItemModelMesher();
		Item i = Item.getItemFromBlock(b);
		ModelResourceLocation mrl = new ModelResourceLocation(b.getRegistryName(), "inventory");
		
		imm.register(i, 0, mrl);
	}
}