package xyz.sirblobman.mod.blobmanstuff.entity.renderer;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import com.mojang.blaze3d.matrix.MatrixStack;

import xyz.sirblobman.mod.blobmanstuff.BlobmanStuffMod;
import xyz.sirblobman.mod.blobmanstuff.entity.EntityCreeperSlime;
import xyz.sirblobman.mod.blobmanstuff.entity.renderer.layer.CreeperSlimeGelLayer;
import xyz.sirblobman.mod.blobmanstuff.entity.renderer.layer.CreeperSlimePoweredLayer;
import xyz.sirblobman.mod.blobmanstuff.entity.renderer.model.CreeperSlimeModel;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public final class CreeperSlimeRenderer extends MobRenderer<EntityCreeperSlime, CreeperSlimeModel> {
    private static final ResourceLocation CREEPER_SLIME = new ResourceLocation(BlobmanStuffMod.MOD_ID,
            "textures/entity/creeper_slime.png");

    public CreeperSlimeRenderer(EntityRendererManager manager) {
        super(manager, new CreeperSlimeModel(16, 0.0F), 0.25F);
        addLayer(new CreeperSlimeGelLayer(this));
        addLayer(new CreeperSlimePoweredLayer(this));
    }

    @Override
    public void render(EntityCreeperSlime entity, float p_225623_2_, float p_225623_3_,
                       @NotNull MatrixStack matrixStack, @NotNull IRenderTypeBuffer bufferType, int p_225623_6_) {
        float slimeSize = entity.getSlimeSize();
        this.shadowRadius = 0.25F * slimeSize;
        super.render(entity, p_225623_2_, p_225623_3_, matrixStack, bufferType, p_225623_6_);
    }

    @Override
    protected void scale(EntityCreeperSlime creeperSlime, MatrixStack matrixStack, float p_225620_3_) {
        float slimeSize = creeperSlime.getSlimeSize();
        matrixStack.scale(0.999F, 0.999F, 0.999F);
        matrixStack.translate(0.0D, 0.001F, 0.0D);

        float f2 = MathHelper.lerp(p_225620_3_, creeperSlime.slimeOldSquish, creeperSlime.slimeSquish);
        f2 /= (slimeSize * 0.5F + 1.0F);

        float f3 = 1.0F / (f2 + 1.0F);
        matrixStack.scale(f3 * slimeSize, 1.0F / f3 * slimeSize, f3 * slimeSize);
    }

    @Override
    protected float getWhiteOverlayProgress(EntityCreeperSlime entity, float p_225625_2_) {
        float swelling = entity.getSwelling(p_225625_2_);
        return (swelling * 10.0F) % 2 == 0 ? 0.0F : MathHelper.clamp(swelling, 0.5F, 1.0F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull EntityCreeperSlime entity) {
        return CREEPER_SLIME;
    }
}
