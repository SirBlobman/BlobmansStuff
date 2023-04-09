package xyz.sirblobman.mod.blobmanstuff.entity.renderer.model;

import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;

import xyz.sirblobman.mod.blobmanstuff.entity.EntityCreeperSlime;

import com.google.common.collect.ImmutableList;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public final class CreeperSlimeModel extends SegmentedModel<EntityCreeperSlime> {
    private final ModelRenderer cube;

    public CreeperSlimeModel(int offset, float scale) {
        this.cube = new ModelRenderer(this, 0, offset);

        if (offset > 0) {
            this.cube.addBox(-3.0F, 17.0F, -3.0F, 6.0F, 6.0F, 6.0F, scale, scale, scale);
        } else {
            this.cube.addBox(-4.0F, 16.0F, -4.0F, 8.0F, 8.0F, 8.0F, scale, scale, scale);
        }
    }

    @Override
    public void setupAnim(EntityCreeperSlime slime, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {
        // Empty Method
    }

    @Override
    public @NotNull Iterable<ModelRenderer> parts() {
        return ImmutableList.of(this.cube);
    }
}
