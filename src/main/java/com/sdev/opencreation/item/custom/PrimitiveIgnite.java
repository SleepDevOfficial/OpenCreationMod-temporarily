package com.sdev.opencreation.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;

public class PrimitiveIgnite extends Item {

    public PrimitiveIgnite(Properties properties) {
        super(properties.durability(64));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(player.getItemInHand(hand));
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 72000;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW;
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int timeLeft) {
        if (!(entity instanceof Player player)) return;
        if (!(level instanceof ServerLevel serverLevel)) return;

        int useTime = this.getUseDuration(stack, entity) - timeLeft;
        if (useTime < 10) return;

        var hit = player.pick(5.0D, 0.0F, false);

        if (hit.getType() != net.minecraft.world.phys.HitResult.Type.BLOCK) return;

        var blockHit = (net.minecraft.world.phys.BlockHitResult) hit;

        BlockPos clickedPos = blockHit.getBlockPos();
        BlockPos placePos = clickedPos.relative(blockHit.getDirection());

        BlockState clickedState = level.getBlockState(clickedPos);

        if (clickedState.getBlock() instanceof CampfireBlock) {
            if (!clickedState.getValue(CampfireBlock.LIT)) {
                level.setBlock(clickedPos, clickedState.setValue(CampfireBlock.LIT, true), 3);
                damageItem(stack, player);
            }
            return;
        }

        if (BaseFireBlock.canBePlacedAt(level, placePos, blockHit.getDirection())) {
            level.setBlock(placePos, BaseFireBlock.getState(level, placePos), 11);
            damageItem(stack, player);
        }
    }

    private void damageItem(ItemStack stack, Player player) {
        stack.hurtAndBreak(1, player, player.getEquipmentSlotForItem(stack));
    }

}
