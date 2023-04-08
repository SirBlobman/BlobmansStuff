package xyz.sirblobman.mod.blobmanstuff.item;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;

import xyz.sirblobman.mod.blobmanstuff.item.material.BlobmanArmorMaterial;

public final class ItemBlobmanArmor extends ArmorItem {
    private static final IArmorMaterial BLOBMAN = new BlobmanArmorMaterial();

    public ItemBlobmanArmor(EquipmentSlotType slot) {
        super(BLOBMAN, slot, new Properties().tab(BSItemGroups.MAIN));
    }
}
