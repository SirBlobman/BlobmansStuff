package xyz.sirblobman.mod.blobmanstuff.entity.renderer;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SlimeRenderer;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.util.ResourceLocation;

import xyz.sirblobman.mod.blobmanstuff.BlobmanStuffMod;

import org.jetbrains.annotations.NotNull;

public final class BlueSlimeRenderer extends SlimeRenderer {
    private static final ResourceLocation BLUE_SLIME = new ResourceLocation(BlobmanStuffMod.MOD_ID,
            "textures/entity/blue_slime.png");
    public BlueSlimeRenderer(EntityRendererManager manager) {
        super(manager);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull SlimeEntity slime) {
        return BLUE_SLIME;
    }
}
