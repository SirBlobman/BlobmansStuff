package xyz.sirblobman.mod.blobmanstuff.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

import xyz.sirblobman.mod.blobmanstuff.item.material.BlueSlimeArmorMaterial;

public final class ItemBlueSlimeArmor extends ArmorItem {
    private static final IArmorMaterial BLUE_SLIME = new BlueSlimeArmorMaterial();

    public ItemBlueSlimeArmor(EquipmentSlotType slot) {
        super(BLUE_SLIME, slot, new Properties().tab(BSItemGroups.MAIN));
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        if (isWearingFullArmor(player)) {
            EffectInstance jumpBoost = new EffectInstance(Effects.JUMP, 20, 1, true,
                    false, false);
            player.addEffect(jumpBoost);
        }
    }

    private boolean isWearingFullArmor(PlayerEntity player) {
        Iterable<ItemStack> armorSlots = player.getArmorSlots();
        for (ItemStack armorSlot : armorSlots) {
            if (armorSlot.isEmpty()) {
                return false;
            }

            Item item = armorSlot.getItem();
            if (!(item instanceof ItemBlueSlimeArmor)) {
                return false;
            }
        }

        return true;
    }
}
