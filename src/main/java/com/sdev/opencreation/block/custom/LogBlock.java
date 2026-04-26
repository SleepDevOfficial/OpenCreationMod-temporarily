package com.sdev.opencreation.block.custom;

import com.llamalad7.mixinextras.lib.antlr.runtime.atn.SemanticContext;
import com.sdev.opencreation.block.OpenCreationBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.common.ItemAbility;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class LogBlock extends RotatedPillarBlock {
    private static final Map<BlockPos, Integer> clickCounter = new HashMap<>();
    public LogBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isFlammable( BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
    }

    public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!level.isClientSide() && state.is(OpenCreationBlocks.HEVEA_LOG.get())) {
            ItemStack handItem = player.getItemInHand(player.getUsedItemHand());
            if (handItem.isEmpty() || (handItem.getItem() instanceof PickaxeItem)) {
                int clicks = clickCounter.getOrDefault(pos, 0) + 1;

                if (clicks >= 9) {
                    Direction facing = player.getDirection().getOpposite();
                    BlockState crackedState = OpenCreationBlocks.NOTCH_HEVEA_LOG.get().defaultBlockState();
                    if (crackedState.hasProperty(BlockStateProperties.HORIZONTAL_FACING)) {
                        crackedState = crackedState.setValue(BlockStateProperties.HORIZONTAL_FACING, facing);
                    }
                    level.setBlock(pos, crackedState, 3);
                    clickCounter.remove(pos);
                } else {
                    clickCounter.put(pos, clicks);
                }
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context,
                                                     ItemAbility itemAbility, boolean simulate) {
        if(context.getItemInHand().getItem() instanceof AxeItem) {
            if(state.is(OpenCreationBlocks.HEVEA_LOG)) {
                return OpenCreationBlocks.STRIPPED_HEVEA_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }

            if(state.is(OpenCreationBlocks.HEVEA_WOOD)) {
                return OpenCreationBlocks.STRIPPED_HEVEA_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
        }

        return super.getToolModifiedState(state, context, itemAbility, simulate);
    }
}
