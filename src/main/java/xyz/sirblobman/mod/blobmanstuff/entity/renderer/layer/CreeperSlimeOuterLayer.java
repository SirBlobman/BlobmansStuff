package xyz.sirblobman.mod.blobmanstuff.entity.renderer.layer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import xyz.sirblobman.mod.blobmanstuff.entity.CreeperSlime;
import xyz.sirblobman.mod.blobmanstuff.entity.renderer.model.BSModelLayers;
import xyz.sirblobman.mod.blobmanstuff.entity.renderer.model.CreeperSlimeModel;

import org.jetbrains.annotations.NotNull;

public final class CreeperSlimeOuterLayer extends RenderLayer<CreeperSlime, CreeperSlimeModel> {
    private final CreeperSlimeModel model;

    public CreeperSlimeOuterLayer(RenderLayerParent<CreeperSlime, CreeperSlimeModel> parent,
                                  EntityModelSet modelSet) {
        super(parent);
        ModelPart modelPart = modelSet.bakeLayer(BSModelLayers.CREEPER_SLIME_OUTER);
        this.model = new CreeperSlimeModel(modelPart);
    }

    @Override
    public void render(@NotNull PoseStack poseStack, @NotNull MultiBufferSource bufferSource, int p_117472_,
                       @NotNull CreeperSlime entity, float p_117474_, float p_117475_, float p_117476_,
                       float p_117477_, float p_117478_, float p_117479_) {
        Minecraft minecraft = Minecraft.getInstance();
        boolean glowing = minecraft.shouldEntityAppearGlowing(entity);
        boolean invisible = entity.isInvisible();

        if (!invisible || glowing) {
            ResourceLocation texture = getTextureLocation(entity);
            VertexConsumer vertexConsumer;
            if (glowing) {
                RenderType outline = RenderType.outline(texture);
                vertexConsumer = bufferSource.getBuffer(outline);
            } else {
                RenderType translucent = RenderType.entityTranslucent(texture);
                vertexConsumer = bufferSource.getBuffer(translucent);
            }

            CreeperSlimeModel parentModel = getParentModel();
            parentModel.copyPropertiesTo(this.model);

            this.model.prepareMobModel(entity, p_117474_, p_117475_, p_117476_);
            this.model.setupAnim(entity, p_117474_, p_117475_, p_117477_, p_117478_, p_117479_);

            int overlay = LivingEntityRenderer.getOverlayCoords(entity, 0.0F);
            this.model.renderToBuffer(poseStack, vertexConsumer, p_117472_, overlay,
                    1.0F, 1.0F, 1.0F, 1.0F);
        }
    }
}
