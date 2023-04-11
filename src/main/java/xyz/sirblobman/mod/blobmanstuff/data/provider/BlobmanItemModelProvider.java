package xyz.sirblobman.mod.blobmanstuff.data.provider;

import java.util.Collection;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import xyz.sirblobman.mod.blobmanstuff.BlobmanStuffMod;
import xyz.sirblobman.mod.blobmanstuff.item.BSItems;

import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public final class BlobmanItemModelProvider extends ItemModelProvider {
    public BlobmanItemModelProvider(PackOutput output, ExistingFileHelper helper) {
        super(output, BlobmanStuffMod.MOD_ID, helper);
    }

    @Override
    protected void registerModels() {
        Collection<RegistryObject<Item>> items = BSItems.ITEMS.getEntries();
        for (RegistryObject<Item> object : items) {
            Item item = object.get();
            ResourceLocation resourceLocation = object.getId();

            if (item instanceof ForgeSpawnEggItem) {
                ResourceLocation spawnEgg = new ResourceLocation("minecraft", "item/template_spawn_egg");
                withExistingParent(resourceLocation.getPath(), spawnEgg);
            } else {
                ResourceLocation textureLocation = new ResourceLocation(resourceLocation.getNamespace(),
                        "item/" + resourceLocation.getPath());
                if (!existingFileHelper.exists(textureLocation, ModelProvider.TEXTURE)) {
                    continue;
                }

                basicItem(item);
            }
        }
    }
}
