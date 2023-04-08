package xyz.sirblobman.mod.blobmanstuff.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

import xyz.sirblobman.mod.blobmanstuff.entity.EntityCustomItemProjectile;

import org.jetbrains.annotations.NotNull;

public final class ItemSlimeWand extends Item {
    private final int level;

    public ItemSlimeWand(int level) {
        super(new Properties().stacksTo(1).tab(BSItemGroups.MAIN));
        this.level = level;
    }

    public int getLevel() {
        return this.level;
    }

    @Override
    public @NotNull ActionResult<ItemStack> use(@NotNull World world, @NotNull PlayerEntity player,
                                                @NotNull Hand hand) {
        ItemStack stack = player.getItemInHand(hand);
        double x = player.getX();
        double y = player.getEyeY();
        double z = player.getZ();

        world.playLocalSound(x, y, z, SoundEvents.SLIME_BLOCK_BREAK, SoundCategory.PLAYERS,
                1.0F, 1.0F, false);
        launchSlime(world, player);

        player.awardStat(Stats.ITEM_USED.get(this));
        return ActionResult.sidedSuccess(stack, world.isClientSide());
    }

    private void launchSlime(World world, PlayerEntity player) {
        if (world.isClientSide()) {
            return;
        }

        int level = getLevel();
        Item thrown = (level == 0 ? Items.SLIME_BALL : BSItems.BLUE_SLIME_BALL);
        float damage = (2.0F + level);

        EntityCustomItemProjectile projectile = new EntityCustomItemProjectile(damage, world, player);
        projectile.shootFromRotation(player, player.xRot, player.yRot,
                0.0F, 1.5F, 0.0F);

        ItemStack stack = new ItemStack(thrown, 1);
        projectile.setItem(stack);

        world.addFreshEntity(projectile);
    }
}
