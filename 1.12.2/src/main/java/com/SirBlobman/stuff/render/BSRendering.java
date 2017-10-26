package com.SirBlobman.stuff.render;

import com.SirBlobman.stuff.BlobmansStuff;
import com.SirBlobman.stuff.block.BSBlocks;
import com.SirBlobman.stuff.entity.custom.EntityBlueSlime;
import com.SirBlobman.stuff.entity.custom.EntityCreeperSlime;
import com.SirBlobman.stuff.entity.custom.EntityProjectileItem;
import com.SirBlobman.stuff.item.BSItems;
import com.SirBlobman.stuff.item.custom.ItemCustomSlimeball;
import com.SirBlobman.stuff.render.entity.RenderBlueSlime;
import com.SirBlobman.stuff.render.entity.RenderCreeperSlime;
import com.SirBlobman.stuff.render.entity.RenderProjectileItem;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public final class BSRendering {
    public static void items() {
        reg(BSItems.SLIMEBALL_BLUE, BSItems.SLIMEBALL_YELLOW, BSItems.SLIMEBALL_RED, BSItems.SLIMEBALL_SHINY, BSItems.SLIMEBALL_BLACK);
        reg(BSItems.WAND_STICK);
        
        reg(BSItems.SLIME_WAND, 0, "tool/green_slime_wand");
        reg(BSItems.SLIME_WAND, 1, "tool/blue_slime_wand");
    }
    
    public static void blocks() {
        reg(BSBlocks.SLIME_BLOCK_BLACK, BSBlocks.SLIME_BLOCK_BLUE, BSBlocks.SLIME_BLOCK_RED, BSBlocks.SLIME_BLOCK_SHINY, BSBlocks.SLIME_BLOCK_YELLOW);
    }
    
    public static void entities() {
        RenderingRegistry.registerEntityRenderingHandler(EntityBlueSlime.class, RenderBlueSlime::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityCreeperSlime.class, RenderCreeperSlime::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityProjectileItem.class, new IRenderFactory<EntityProjectileItem>() {
            @Override
            public Render<EntityProjectileItem> createRenderFor(RenderManager rm) {
                Minecraft mc = Minecraft.getMinecraft();
                RenderItem ri = mc.getRenderItem();
                RenderProjectileItem rpi = new RenderProjectileItem(rm, ri);
                return rpi;
            }
        });
    }
    
    private static void reg(Block... bb) {
        Item[] ii = new Item[bb.length];
        for(int i = 0; i < bb.length; i++) {
            Block b = bb[i];
            Item ib = Item.getItemFromBlock(b);
            ii[i] = ib;
        } reg(ii);
    }
    
    private static void reg(Item... ii) {
        for(Item i : ii) {
            ResourceLocation rl = i.getRegistryName();
            String path = rl.getResourcePath();
            
            if(i instanceof ItemBlock) path = "block/" + path;
            else if(i instanceof ItemCustomSlimeball) {
                ItemCustomSlimeball ics = (ItemCustomSlimeball) i;
                String color = ics.getColor();
                path = "slime_ball/" + color;
            } else if (i instanceof ItemArmor) path = "armor/" + path;
            reg(i, 0, path);
        }
    }
    
    private static void reg(Item i, int meta, String name) {
        ResourceLocation rl = new ResourceLocation(BlobmansStuff.MODID, name);
        ModelResourceLocation mrl = new ModelResourceLocation(rl, "inventory");
        ModelLoader.setCustomModelResourceLocation(i, meta, mrl);
    }
}