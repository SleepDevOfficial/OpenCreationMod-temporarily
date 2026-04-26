package com.sdev.opencreation.block.custom;

import com.mojang.serialization.MapCodec;
import com.sdev.opencreation.block.OCBlockEntities;
import com.sdev.opencreation.block.bes.FaucetEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.items.ItemHandlerHelper;
import org.jetbrains.annotations.Nullable;

public class Faucet extends BaseEntityBlock {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final MapCodec<Faucet> CODEC = simpleCodec(Faucet::new);

    public Faucet(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<net.minecraft.world.level.block.Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new FaucetEntity(pos, state);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos,
                                               Player player, BlockHitResult hitResult) {
        if (!level.isClientSide) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof FaucetEntity faucetBE) {
                ItemStack handItem = player.getItemInHand(InteractionHand.MAIN_HAND);
                IFluidHandler fluidHandler = faucetBE.getFluidHandler();

                if (handItem.getItem() == Items.BUCKET) {
                    FluidStack fluidToDrain = fluidHandler.drain(1000, IFluidHandler.FluidAction.SIMULATE);
                    if (fluidToDrain.getAmount() >= 1000) {
                        fluidHandler.drain(1000, IFluidHandler.FluidAction.EXECUTE);
                        ItemStack latexBucket = new ItemStack(com.sdev.opencreation.fluid.OpenCreationFluids.RUBBER.getBucket().get());
                        if (!player.getAbilities().instabuild) {
                            handItem.shrink(1);
                        }
                        ItemHandlerHelper.giveItemToPlayer(player, latexBucket);
                        return InteractionResult.SUCCESS;
                    }
                }

                ItemStack rubberBucket = com.sdev.opencreation.fluid.OpenCreationFluids.RUBBER.getBucket().get().getDefaultInstance();
                if (handItem.getItem() == rubberBucket.getItem()) {
                    FluidStack fluidToFill = new FluidStack(com.sdev.opencreation.fluid.OpenCreationFluids.RUBBER.getSourceFluid(), 1000);
                    int filled = fluidHandler.fill(fluidToFill, IFluidHandler.FluidAction.SIMULATE);
                    if (filled >= 1000) {
                        fluidHandler.fill(fluidToFill, IFluidHandler.FluidAction.EXECUTE);
                        if (!player.getAbilities().instabuild) {
                            handItem.shrink(1);
                        }
                        ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(Items.BUCKET));
                        return InteractionResult.SUCCESS;
                    }
                }
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return !level.isClientSide ? createTickerHelper(type, OCBlockEntities.FAUCET.get(), FaucetEntity::tick) : null;
    }
}