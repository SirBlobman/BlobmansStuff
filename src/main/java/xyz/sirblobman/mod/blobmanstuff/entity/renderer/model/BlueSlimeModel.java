package xyz.sirblobman.mod.blobmanstuff.entity.renderer.model;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

import xyz.sirblobman.mod.blobmanstuff.entity.BlueSlime;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public final class BlueSlimeModel extends HierarchicalModel<BlueSlime> {
    private final ModelPart root;

    public BlueSlimeModel(ModelPart part) {
        this.root = part;
    }

    public static LayerDefinition createOuterBodyLayer() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();

        CubeListBuilder cube = CubeListBuilder.create();
        cube.texOffs(0, 0);
        cube.addBox(-4.0F, 16.0F, -4.0F, 8.0F, 8.0F, 8.0F);

        partDefinition.addOrReplaceChild("cube", cube, PartPose.ZERO);
        return LayerDefinition.create(meshDefinition, 64, 32);
    }

    public static LayerDefinition createInnerBodyLayer() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();

        CubeListBuilder cube = CubeListBuilder.create()
                .texOffs(0, 16)
                .addBox(-3.0F, 17.0F, -3.0F, 6.0F, 6.0F, 6.0F);
        partDefinition.addOrReplaceChild("cube", cube, PartPose.ZERO);

        CubeListBuilder rightEye = CubeListBuilder.create()
                .texOffs(32, 0)
                .addBox(-3.25F, 18.0F, -3.5F, 2.0F, 2.0F, 2.0F);
        partDefinition.addOrReplaceChild("right_eye", rightEye, PartPose.ZERO);

        CubeListBuilder leftEye = CubeListBuilder.create()
                .texOffs(32, 4)
                .addBox(1.25F, 18.0F, -3.5F, 2.0F, 2.0F, 2.0F);

        partDefinition.addOrReplaceChild("left_eye", leftEye, PartPose.ZERO);
        CubeListBuilder mouth = CubeListBuilder.create()
                .texOffs(32, 8)
                .addBox(0.0F, 21.0F, -3.5F, 1.0F, 1.0F, 1.0F);
        partDefinition.addOrReplaceChild("mouth", mouth, PartPose.ZERO);

        return LayerDefinition.create(meshDefinition, 64, 32);
    }

    @Override
    public void setupAnim(@NotNull BlueSlime slime, float p_103832_, float p_103833_, float p_103834_, float p_103835_,
                          float p_103836_) {
        // Empty Method
    }

    @Override
    public @NotNull ModelPart root() {
        return this.root;
    }
}
