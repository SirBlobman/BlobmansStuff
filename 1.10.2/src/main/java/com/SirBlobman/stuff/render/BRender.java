package com.SirBlobman.stuff.render;

import com.SirBlobman.stuff.block.BBlocks;
import com.SirBlobman.stuff.entity.BlueSlime;
import com.SirBlobman.stuff.entity.CreeperSlime;
import com.SirBlobman.stuff.entity.ProjectileBlueSlimeball;
import com.SirBlobman.stuff.entity.ProjectileGreenSlimeball;
import com.SirBlobman.stuff.item.BItems;
import com.SirBlobman.stuff.render.entity.RenderBlueSlime;
import com.SirBlobman.stuff.render.entity.RenderCreeperSlime;
import com.SirBlobman.stuff.render.entity.RenderSlimeball;
import com.SirBlobman.stuff.utility.Util;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class BRender {
	public static void items() {
		Util.print("Registering Item renderers...");
		reg(BItems.SLIMEBALL_BLUE);
		reg(BItems.SLIMEBALL_YELLOW);
		reg(BItems.SLIMEBALL_RED);
		reg(BItems.SLIMEBALL_SHINY);
		reg(BItems.SLIMEBALL_BLACK);

		reg(BItems.BLOBMAN_CROWN);
		reg(BItems.BLOBMAN_CHEST);
		reg(BItems.BLOBMAN_LEGS);
		reg(BItems.BLOBMAN_FEET);

		reg(BItems.BLUE_SLIME_HELMET);
		reg(BItems.BLUE_SLIME_CHESTPLATE);
		reg(BItems.BLUE_SLIME_LEGGINGS);
		reg(BItems.BLUE_SLIME_BOOTS);
		
		reg(BItems.SHINY_SWORD);
		reg(BItems.ULTRA_SWORD);
		reg(BItems.SLIME_WAND_GREEN);
		reg(BItems.SLIME_WAND_BLUE);
		reg(BItems.WAND_STICK);
	}
	
	public static void blocks() {
		Util.print("Registering Block renderers...");
		reg(BBlocks.SLIME_BLOCK_BLUE);
		reg(BBlocks.SLIME_BLOCK_RED);
		reg(BBlocks.SLIME_BLOCK_YELLOW);
		reg(BBlocks.SLIME_BLOCK_SHINY);
		reg(BBlocks.SLIME_BLOCK_BLACK);
	}
	
	public static void entities() {
		Util.print("Registering Entity renderers...");
		RenderingRegistry.registerEntityRenderingHandler(BlueSlime.class, new IRenderFactory<BlueSlime>() {
			@Override
			public Render<BlueSlime> createRenderFor(RenderManager rm) {
				RenderBlueSlime rbs = new RenderBlueSlime(rm);
				return rbs;
			}
		});
		
		RenderingRegistry.registerEntityRenderingHandler(CreeperSlime.class, new IRenderFactory<CreeperSlime>() {
			@Override
			public Render<CreeperSlime> createRenderFor(RenderManager rm) {
				RenderCreeperSlime rcs = new RenderCreeperSlime(rm);
				return rcs;
			}
		});
		
		RenderingRegistry.registerEntityRenderingHandler(ProjectileBlueSlimeball.class, new IRenderFactory<ProjectileBlueSlimeball>() {
			@Override
			public Render<ProjectileBlueSlimeball> createRenderFor(RenderManager rm) {
				Minecraft mc = Minecraft.getMinecraft();
				RenderItem ri = mc.getRenderItem();
				RenderSlimeball<ProjectileBlueSlimeball> rs = new RenderSlimeball<ProjectileBlueSlimeball>(rm, ri, BItems.SLIMEBALL_BLUE);
				return rs;
			}
		});
		
		RenderingRegistry.registerEntityRenderingHandler(ProjectileGreenSlimeball.class, new IRenderFactory<ProjectileGreenSlimeball>() {
			@Override
			public Render<ProjectileGreenSlimeball> createRenderFor(RenderManager rm) {
				Minecraft mc = Minecraft.getMinecraft();
				RenderItem ri = mc.getRenderItem();
				RenderSlimeball<ProjectileGreenSlimeball> rs = new RenderSlimeball<ProjectileGreenSlimeball>(rm, ri, Items.SLIME_BALL);
				return rs;
			}
		});
	}
	
	private static void reg(Item i) {
		ResourceLocation rl = i.getRegistryName();
		ModelResourceLocation mrl = new ModelResourceLocation(rl, "inventory");
		ModelLoader.setCustomModelResourceLocation(i, 0, mrl);
	}
	
	private static void reg(Block b) {
		Item i = Item.getItemFromBlock(b);
		reg(i);
	}
}