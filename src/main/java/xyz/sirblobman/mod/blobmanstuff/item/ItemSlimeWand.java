package xyz.sirblobman.mod.blobmanstuff.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;

import xyz.sirblobman.mod.blobmanstuff.entity.ItemProjectile;

import org.jetbrains.annotations.NotNull;

public final class ItemSlimeWand extends Item {
    private final int level;

    public ItemSlimeWand(int level) {
        super(new Properties().stacksTo(1).rarity(level == 0 ? Rarity.UNCOMMON : Rarity.RARE));
        this.level = level;
    }

    public int getLevel() {
        return this.level;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level world, @NotNull Player player,
                                                           @NotNull InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        double x = player.getX();
        double y = player.getEyeY();
        double z = player.getZ();

        world.playLocalSound(x, y, z, SoundEvents.SLIME_BLOCK_BREAK, SoundSource.PLAYERS,
                1.0F, 1.0F, false);
        launchSlime(world, player);

        player.awardStat(Stats.ITEM_USED.get(this));
        return InteractionResultHolder.sidedSuccess(stack, world.isClientSide());
    }

    private void launchSlime(Level world, Player player) {
        if (world.isClientSide()) {
            return;
        }

        int level = getLevel();
        Item thrown = (level == 0 ? Items.SLIME_BALL : BSItems.BLUE_SLIME_BALL.get());
        float damage = (2.0F * (level + 1.0F));

        ItemProjectile projectile = new ItemProjectile(damage, world, player);
        projectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F,
                0.0F);

        ItemStack stack = new ItemStack(thrown, 1);
        projectile.setItem(stack);

        world.addFreshEntity(projectile);
    }
}
