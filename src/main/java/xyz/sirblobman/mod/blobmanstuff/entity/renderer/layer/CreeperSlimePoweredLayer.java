package xyz.sirblobman.mod.blobmanstuff.entity.renderer.layer;

import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EnergySwirlLayer;
import net.minecraft.resources.ResourceLocation;

import xyz.sirblobman.mod.blobmanstuff.BlobmanStuffMod;
import xyz.sirblobman.mod.blobmanstuff.entity.CreeperSlime;
import xyz.sirblobman.mod.blobmanstuff.entity.renderer.model.BSModelLayers;
import xyz.sirblobman.mod.blobmanstuff.entity.renderer.model.CreeperSlimeModel;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public final class CreeperSlimePoweredLayer extends EnergySwirlLayer<CreeperSlime, CreeperSlimeModel> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(BlobmanStuffMod.MOD_ID,
            "textures/entity/creeper_armor.png");

    private final CreeperSlimeModel model;

    public CreeperSlimePoweredLayer(RenderLayerParent<CreeperSlime, CreeperSlimeModel> parent,
                                    EntityModelSet set) {
        super(parent);
        ModelPart part = set.bakeLayer(BSModelLayers.CREEPER_SLIME_ARMOR);
        this.model = new CreeperSlimeModel(part);
    }

    @Override
    protected float xOffset(float scale) {
        return (scale * 0.01F);
    }

    @Override
    protected @NotNull ResourceLocation getTextureLocation() {
        return TEXTURE;
    }

    @Override
    protected @NotNull CreeperSlimeModel model() {
        return this.model;
    }
}
