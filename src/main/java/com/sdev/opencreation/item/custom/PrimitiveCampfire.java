package com.sdev.opencreation.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;

public class PrimitiveCampfire extends BlockItem {

    public PrimitiveCampfire(Properties properties) {
        super(Blocks.CAMPFIRE, properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Direction face = context.getClickedFace();
        BlockPos placePos = pos.relative(face);

        if (!level.getBlockState(placePos).canBeReplaced()) {
            return InteractionResult.FAIL;
        }

        BlockPlaceContext placeContext = new BlockPlaceContext(context);
        BlockState stateToPlace = Blocks.CAMPFIRE.getStateForPlacement(placeContext);

        if (stateToPlace == null) {
            stateToPlace = Blocks.CAMPFIRE.defaultBlockState()
                    .setValue(CampfireBlock.FACING, context.getHorizontalDirection().getOpposite())
                    .setValue(CampfireBlock.WATERLOGGED, false);
        }

        if (stateToPlace.hasProperty(CampfireBlock.LIT)) {
            stateToPlace = stateToPlace.setValue(CampfireBlock.LIT, false);
        }
        if (stateToPlace.hasProperty(CampfireBlock.SIGNAL_FIRE)) {
            stateToPlace = stateToPlace.setValue(CampfireBlock.SIGNAL_FIRE, false);
        }

        if (!level.isClientSide()) {
            level.setBlock(placePos, stateToPlace, 3);
            ItemStack stack = context.getItemInHand();
            if (context.getPlayer() == null || !context.getPlayer().isCreative()) {
                stack.shrink(1);
            }
        }

        return InteractionResult.sidedSuccess(level.isClientSide());
    }
}