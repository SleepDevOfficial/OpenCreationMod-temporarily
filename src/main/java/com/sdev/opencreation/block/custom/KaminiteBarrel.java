package com.sdev.opencreation.block.custom;

import com.mojang.serialization.MapCodec;
import com.sdev.opencreation.block.bes.KaminiteBarrelEntity;
import com.sdev.opencreation.item.OpenCreationItems;
import com.sdev.opencreation.util.OCHelpers;
import com.sdev.researchcreation.research.PlayerResearchData;
import com.sdev.researchcreation.research.ResearchSavedData;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.items.ItemStackHandler;

public class KaminiteBarrel extends BaseEntityBlock {
    public static final MapCodec<KaminiteBarrel> CODEC =
            simpleCodec(KaminiteBarrel::new);

    public KaminiteBarrel(Properties prop) {
        super(prop);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new KaminiteBarrelEntity(pos, state);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level,
                                               BlockPos pos, Player player,
                                               BlockHitResult hit) {
        if (level.isClientSide) return InteractionResult.SUCCESS;

        BlockEntity be = level.getBlockEntity(pos);
        if (!(be instanceof KaminiteBarrelEntity barrel)) return InteractionResult.PASS;

        ItemStack held = player.getItemInHand(InteractionHand.MAIN_HAND);
        PlayerResearchData researchData = ResearchSavedData.get(player.getServer()).get(player.getUUID());

        if (researchData.has("primitive_mixing")) {
            if (player.isShiftKeyDown() && held.isEmpty()) {
                barrel.giveToPlayer(player);
                return InteractionResult.SUCCESS;
            }

            if (held.getItem() == Items.WATER_BUCKET && !player.isShiftKeyDown()) {
                if (barrel.getWater() < KaminiteBarrelEntity.MAX_WATER) {
                    barrel.setWater(barrel.getWater() + 1000);

                    if (!player.isCreative()) {
                        player.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(Items.BUCKET));
                    }

                    player.playSound(SoundEvents.BUCKET_EMPTY, 1f, 1f);
                    barrel.setChanged();
                }
                return InteractionResult.SUCCESS;
            }

            if (held.getItem() == Items.BUCKET) {
                if (barrel.getWater() >= 1000) {
                    barrel.setWater(barrel.getWater() - 1000);

                    if (!player.isCreative()) {
                        player.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(Items.WATER_BUCKET));
                    }

                    player.playSound(SoundEvents.BUCKET_FILL, 1f, 1f);
                    barrel.setChanged();
                }
                return InteractionResult.SUCCESS;
            }

            if (!player.isShiftKeyDown() && held.getItem() instanceof ShovelItem) {

                if (barrel.getWater() >= 200 &&
                        barrel.hasItem(OpenCreationItems.ASH.get(), 2) &&
                        barrel.hasItem(Items.CLAY_BALL, 2) &&
                        barrel.hasItem(Items.SAND, 3) &&
                        barrel.hasItem(Items.GRAVEL, 3)) {

                    barrel.setWater(barrel.getWater() - 200);
                    barrel.removeItem(OpenCreationItems.ASH.get(), 2);
                    barrel.removeItem(Items.CLAY_BALL, 2);
                    barrel.removeItem(Items.SAND, 3);
                    barrel.removeItem(Items.GRAVEL, 3);

                    ItemStack result = new ItemStack(OpenCreationItems.KAMINITE_MIXTURE.get());

                    ItemStack remaining = insertItemIntoInventory(barrel.getInventory(), result);

                    if (!remaining.isEmpty()) {
                        player.drop(remaining, false);
                    }

                    player.playSound(SoundEvents.GENERIC_EXTINGUISH_FIRE, 1f, 1f);
                    barrel.setChanged();

                    return InteractionResult.SUCCESS;
                }

                return InteractionResult.SUCCESS;
            }

            if (!held.isEmpty() && (held.getItem() == Items.SAND || held.getItem() == Items.GRAVEL || held.getItem() == Items.CLAY_BALL || held.getItem() == OpenCreationItems.ASH.get())) {

                ItemStackHandler inv = barrel.getInventory();
                ItemStack remaining = insertItemIntoInventory(inv, held.copy());

                if (remaining.getCount() < held.getCount()) {
                    if (!player.isCreative()) {
                        if (remaining.isEmpty()) {
                            player.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
                        } else {
                            player.setItemInHand(InteractionHand.MAIN_HAND, remaining);
                        }
                    }

                    player.playSound(SoundEvents.ITEM_PICKUP, 0.5f, 1f);
                    barrel.setChanged();
                    return InteractionResult.SUCCESS;
                }
            }
        } else {
            OCHelpers.Stupid(player);
        }

        return InteractionResult.PASS;
    }

    private ItemStack insertItemIntoInventory(ItemStackHandler inv, ItemStack stack) {
        ItemStack remaining = stack.copy();

        for (int i = 0; i < inv.getSlots(); i++) {
            ItemStack slot = inv.getStackInSlot(i);

            if (!slot.isEmpty() && ItemStack.isSameItemSameComponents(slot, remaining)) {
                int max = Math.min(slot.getMaxStackSize(), inv.getSlotLimit(i));
                int canInsert = max - slot.getCount();

                if (canInsert > 0) {
                    int move = Math.min(canInsert, remaining.getCount());
                    slot.grow(move);
                    remaining.shrink(move);

                    if (remaining.isEmpty()) {
                        return ItemStack.EMPTY;
                    }
                }
            }
        }

        for (int i = 0; i < inv.getSlots(); i++) {
            if (inv.getStackInSlot(i).isEmpty()) {
                inv.setStackInSlot(i, remaining.copy());
                return ItemStack.EMPTY;
            }
        }

        return remaining;
    }
}
