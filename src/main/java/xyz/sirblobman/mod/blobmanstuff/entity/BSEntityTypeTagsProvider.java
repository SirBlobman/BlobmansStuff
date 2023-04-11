package xyz.sirblobman.mod.blobmanstuff.entity;

import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;

import xyz.sirblobman.mod.blobmanstuff.BlobmanStuffMod;

import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

public final class BSEntityTypeTagsProvider extends EntityTypeTagsProvider {
    public BSEntityTypeTagsProvider(PackOutput output, CompletableFuture<Provider> lookupProvider,
                                    ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, BlobmanStuffMod.MOD_ID, existingFileHelper);
    }

    @Override
    public void addTags(@NotNull Provider lookupProvider) {
        IntrinsicTagAppender<EntityType<?>> fallImmune = tag(EntityTypeTags.FALL_DAMAGE_IMMUNE);
        fallImmune.add(BSEntityTypes.BLUE_SLIME.get());
        fallImmune.add(BSEntityTypes.CREEPER_SLIME.get());
    }
}
