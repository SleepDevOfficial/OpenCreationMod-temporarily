package com.sdev.opencreation.block.custom;

import com.sdev.opencreation.item.OpenCreationItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

public class Faucet extends Block {

    public static final BooleanProperty FILLED = BooleanProperty.create("filled");

    public Faucet(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FILLED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FILLED);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        ItemStack handItem = player.getItemInHand(InteractionHand.MAIN_HAND);

        if (!level.isClientSide()) {
            if (handItem.getItem() == Items.BUCKET && state.getValue(FILLED)) {
                player.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(OpenCreationItems.DEBUG_TOAST.get()));
                level.setBlock(pos, state.setValue(FILLED, false), 3);
                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.PASS;
    }
}