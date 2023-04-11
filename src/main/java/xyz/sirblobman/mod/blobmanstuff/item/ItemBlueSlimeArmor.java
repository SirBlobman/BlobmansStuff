package xyz.sirblobman.mod.blobmanstuff.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import xyz.sirblobman.mod.blobmanstuff.item.material.BlueSlimeArmorMaterial;

public final class ItemBlueSlimeArmor extends ArmorItem {
    private static final ArmorMaterial BLUE_SLIME = new BlueSlimeArmorMaterial();

    public ItemBlueSlimeArmor(Type slot) {
        super(BLUE_SLIME, slot, new Properties());
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        if (isWearingFullArmor(player)) {
            MobEffectInstance jumpBoost = new MobEffectInstance(MobEffects.JUMP, 20, 1, true,
                    false, false);
            player.addEffect(jumpBoost);
        }
    }

    private boolean isWearingFullArmor(Player player) {
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
