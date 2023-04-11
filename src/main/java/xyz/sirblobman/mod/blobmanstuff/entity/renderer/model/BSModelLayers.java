package xyz.sirblobman.mod.blobmanstuff.entity.renderer.model;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

import xyz.sirblobman.mod.blobmanstuff.BlobmanStuffMod;

public final class BSModelLayers {
    public static final ModelLayerLocation BLUE_SLIME = register("blue_slime");
    public static final ModelLayerLocation BLUE_SLIME_OUTER = register("blue_slime", "outer");

    public static final ModelLayerLocation CREEPER_SLIME = register("slime");
    public static final ModelLayerLocation CREEPER_SLIME_OUTER = register("slime", "outer");
    public static final ModelLayerLocation CREEPER_SLIME_ARMOR = register("slime", "armor");

    private static ModelLayerLocation register(String mobName) {
        return register(mobName, "main");
    }

    private static ModelLayerLocation register(String mobName, String partId) {
        ResourceLocation location = new ResourceLocation(BlobmanStuffMod.MOD_ID, mobName);
        return new ModelLayerLocation(location, partId);
    }
}
