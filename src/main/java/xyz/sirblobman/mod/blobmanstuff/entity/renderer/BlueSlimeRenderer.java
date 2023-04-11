package xyz.sirblobman.mod.blobmanstuff.entity.renderer;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import com.mojang.blaze3d.vertex.PoseStack;

import xyz.sirblobman.mod.blobmanstuff.BlobmanStuffMod;
import xyz.sirblobman.mod.blobmanstuff.entity.BlueSlime;
import xyz.sirblobman.mod.blobmanstuff.entity.renderer.layer.BlueSlimeOuterLayer;
import xyz.sirblobman.mod.blobmanstuff.entity.renderer.model.BSModelLayers;
import xyz.sirblobman.mod.blobmanstuff.entity.renderer.model.BlueSlimeModel;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public final class BlueSlimeRenderer extends MobRenderer<BlueSlime, BlueSlimeModel> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(BlobmanStuffMod.MOD_ID,
            "textures/entity/blue_slime.png");

    public BlueSlimeRenderer(Context context) {
        super(context, new BlueSlimeModel(context.bakeLayer(BSModelLayers.BLUE_SLIME)), 0.25F);
        addLayer(new BlueSlimeOuterLayer(this, context.getModelSet()));
    }

    @Override
    public void render(@NotNull BlueSlime entity, float p_115456_, float p_115457_, @NotNull PoseStack poseStack,
                       @NotNull MultiBufferSource bufferSource, int p_115460_) {
        float slimeSize = entity.getSize();
        this.shadowRadius = (0.25F * slimeSize);
        super.render(entity, p_115456_, p_115457_, poseStack, bufferSource, p_115460_);
    }

    @Override
    protected void scale(BlueSlime entity, PoseStack poseStack, float scale) {
        float value = 0.999F;
        poseStack.scale(value, value, value);
        poseStack.translate(0.0F, 0.001F, 0.0F);

        float slimeSize = entity.getSize();
        float lerp = Mth.lerp(scale, entity.oSquish, entity.squish);
        lerp /= (slimeSize * 0.5F + 1.0F);
        float oneDivided = (1.0F / (lerp + 1.0F));

        float xzScale = (oneDivided * slimeSize);
        float yScale = (1.0F / oneDivided * slimeSize);
        poseStack.scale(xzScale, yScale, xzScale);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull BlueSlime entity) {
        return TEXTURE;
    }
}
