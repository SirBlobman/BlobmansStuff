package xyz.sirblobman.mod.blobmanstuff.entity.renderer.layer;

import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.EnergyLayer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;

import xyz.sirblobman.mod.blobmanstuff.BlobmanStuffMod;
import xyz.sirblobman.mod.blobmanstuff.entity.EntityCreeperSlime;
import xyz.sirblobman.mod.blobmanstuff.entity.renderer.model.CreeperSlimeModel;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public final class CreeperSlimePoweredLayer extends EnergyLayer<EntityCreeperSlime, CreeperSlimeModel> {
    private static final ResourceLocation CREEPER_SLIME_POWER = new ResourceLocation(BlobmanStuffMod.MOD_ID,
            "textures/entity/creeper_armor.png");

    private final CreeperSlimeModel model;

    public CreeperSlimePoweredLayer(IEntityRenderer<EntityCreeperSlime, CreeperSlimeModel> renderer) {
        super(renderer);
        this.model = new CreeperSlimeModel(0, 1.0F);
    }

    @Override
    protected float xOffset(float p_225634_1_) {
        return p_225634_1_ * 0.01F;
    }

    @Override
    protected @NotNull ResourceLocation getTextureLocation() {
        return CREEPER_SLIME_POWER;
    }

    @Override
    protected @NotNull EntityModel<EntityCreeperSlime> model() {
        return this.model;
    }
}
