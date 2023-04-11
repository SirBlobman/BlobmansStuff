package xyz.sirblobman.mod.blobmanstuff.item;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;

import xyz.sirblobman.mod.blobmanstuff.item.material.BlobmanArmorMaterial;

public final class ItemBlobmanArmor extends ArmorItem {
    private static final ArmorMaterial BLOBMAN = new BlobmanArmorMaterial();

    public ItemBlobmanArmor(Type slot) {
        super(BLOBMAN, slot, new Properties());
    }
}
