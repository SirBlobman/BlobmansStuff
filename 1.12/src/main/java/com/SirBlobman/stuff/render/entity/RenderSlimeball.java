package com.SirBlobman.stuff.render.entity;

import com.SirBlobman.stuff.entity.ProjectileSlimeball;

import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.item.Item;

public class RenderSlimeball<T extends ProjectileSlimeball> extends RenderSnowball<T> {
	public RenderSlimeball(RenderManager rm, RenderItem ri, Item i) {super(rm, i, ri);}
}