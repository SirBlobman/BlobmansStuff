package xyz.sirblobman.mod.blobmanstuff.data.provider;

import java.util.Collection;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.level.block.Block;

import xyz.sirblobman.mod.blobmanstuff.BlobmanStuffMod;
import xyz.sirblobman.mod.blobmanstuff.block.BSBlocks;
import xyz.sirblobman.mod.blobmanstuff.block.BlockColoredSlime;

import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ExistingFileHelper.ResourceType;
import net.minecraftforge.registries.RegistryObject;

public class BlobmanBlockStateProvider extends BlockStateProvider {
    private final ExistingFileHelper existingFileHelper;

    public BlobmanBlockStateProvider(PackOutput output, ExistingFileHelper helper) {
        super(output, BlobmanStuffMod.MOD_ID, helper);
        this.existingFileHelper = helper;
    }

    @Override
    protected void registerStatesAndModels() {
        Collection<RegistryObject<Block>> entries = BSBlocks.BLOCKS.getEntries();
        ResourceType texture = new ResourceType(PackType.CLIENT_RESOURCES, ".png", "textures");
        for (RegistryObject<Block> entry : entries) {
            ResourceLocation resourceLocation = entry.getId();
            ResourceLocation textureLocation = new ResourceLocation(resourceLocation.getNamespace(),
                    ModelProvider.BLOCK_FOLDER + "/" + resourceLocation.getPath());
            if (!existingFileHelper.exists(textureLocation, texture)) {
                continue;
            }

            Block block = entry.get();
            if (block instanceof BlockColoredSlime coloredSlime) {
                BlockModelProvider models = models();
                String name = resourceLocation.getPath();

                ResourceLocation slimeBlock = new ResourceLocation("minecraft", "block/slime_block");
                BlockModelBuilder builder = models.withExistingParent(name, slimeBlock);
                builder.renderType("translucent");

                builder.texture("particle", textureLocation);
                builder.texture("texture", textureLocation);

                simpleBlockWithItem(coloredSlime, builder);
            } else {
                ModelFile modelFile = cubeAll(block);
                simpleBlockWithItem(block, modelFile);
            }
        }
    }
}
