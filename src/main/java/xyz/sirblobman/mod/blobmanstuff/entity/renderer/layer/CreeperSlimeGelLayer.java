package xyz.sirblobman.mod.blobmanstuff.entity.renderer.layer;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import xyz.sirblobman.mod.blobmanstuff.entity.EntityCreeperSlime;
import xyz.sirblobman.mod.blobmanstuff.entity.renderer.model.CreeperSlimeModel;

import org.jetbrains.annotations.NotNull;

public final class CreeperSlimeGelLayer extends LayerRenderer<EntityCreeperSlime, CreeperSlimeModel> {
    private final CreeperSlimeModel model;

    public CreeperSlimeGelLayer(IEntityRenderer<EntityCreeperSlime, CreeperSlimeModel> renderer) {
        super(renderer);
        this.model = new CreeperSlimeModel(0, 0.0F);
    }

    @Override
    public void render(@NotNull MatrixStack matrixStack, @NotNull IRenderTypeBuffer renderType, int p_225628_3_,
                       EntityCreeperSlime entity, float p_225628_5_, float p_225628_6_, float p_225628_7_,
                       float p_225628_8_, float p_225628_9_, float p_225628_10_) {
        if (!entity.isInvisible()) {
            this.getParentModel().copyPropertiesTo(this.model);
            this.model.prepareMobModel(entity, p_225628_5_, p_225628_6_, p_225628_7_);
            this.model.setupAnim(entity, p_225628_5_, p_225628_6_, p_225628_8_, p_225628_9_, p_225628_10_);
            IVertexBuilder ivertexbuilder = renderType.getBuffer(RenderType.entityTranslucent(this.getTextureLocation(entity)));
            this.model.renderToBuffer(matrixStack, ivertexbuilder, p_225628_3_, LivingRenderer.getOverlayCoords(entity, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
        }
    }
}
