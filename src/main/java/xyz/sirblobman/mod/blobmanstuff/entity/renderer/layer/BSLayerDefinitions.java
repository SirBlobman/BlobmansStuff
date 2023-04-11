package xyz.sirblobman.mod.blobmanstuff.entity.renderer.layer;

import java.util.Map;
import java.util.function.Supplier;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;

import xyz.sirblobman.mod.blobmanstuff.entity.renderer.model.BSModelLayers;
import xyz.sirblobman.mod.blobmanstuff.entity.renderer.model.BlueSlimeModel;
import xyz.sirblobman.mod.blobmanstuff.entity.renderer.model.CreeperSlimeModel;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent.RegisterLayerDefinitions;

@OnlyIn(Dist.CLIENT)
public final class BSLayerDefinitions {
    public static Map<ModelLayerLocation, Supplier<LayerDefinition>> createRoots() {
        Builder<ModelLayerLocation, Supplier<LayerDefinition>> builder = ImmutableMap.builder();

        builder.put(BSModelLayers.BLUE_SLIME, BlueSlimeModel::createInnerBodyLayer);
        builder.put(BSModelLayers.BLUE_SLIME_OUTER, BlueSlimeModel::createOuterBodyLayer);

        builder.put(BSModelLayers.CREEPER_SLIME, CreeperSlimeModel::createInnerBodyLayer);
        builder.put(BSModelLayers.CREEPER_SLIME_OUTER, CreeperSlimeModel::createOuterBodyLayer);
        builder.put(BSModelLayers.CREEPER_SLIME_ARMOR, CreeperSlimeModel::createCreeperArmorLayer);

        return builder.build();
    }

    public static void registerDefinitions(RegisterLayerDefinitions e) {
        Map<ModelLayerLocation, Supplier<LayerDefinition>> layers = createRoots();
        layers.forEach(e::registerLayerDefinition);
    }
}
