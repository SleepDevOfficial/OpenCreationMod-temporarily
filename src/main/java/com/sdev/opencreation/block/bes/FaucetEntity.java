package com.sdev.opencreation.block.bes;

import com.sdev.opencreation.block.OCBlockEntities;
import com.sdev.opencreation.block.OpenCreationBlocks;
import com.sdev.opencreation.block.custom.Faucet;
import com.sdev.opencreation.fluid.OpenCreationFluids;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;

public class FaucetEntity extends BlockEntity {

    private final FluidTank tank = new FluidTank(1500) {
        @Override
        protected void onContentsChanged() {
            setChanged();
            if (level != null && !level.isClientSide) {
                level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
                level.invalidateCapabilities(worldPosition);
            }
        }

        @Override
        public boolean isFluidValid(FluidStack stack) {
            return stack.getFluid() == OpenCreationFluids.RUBBER.getSourceFluid();
        }
    };

    public FaucetEntity(BlockPos pos, BlockState state) {
        super(OCBlockEntities.FAUCET.get(), pos, state);
    }

    public int getFluidAmount() {
        return tank.getFluidAmount();
    }

    public int getCapacity() {
        return tank.getCapacity();
    }

    public IFluidHandler getFluidHandler() {
        return tank;
    }

    public void addLatex(int amount) {
        if (tank.getFluidAmount() + amount <= tank.getCapacity()) {
            tank.fill(new FluidStack(OpenCreationFluids.RUBBER.getSourceFluid(), amount), IFluidHandler.FluidAction.EXECUTE);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tank.writeToNBT(registries, tag);
    }

    @Override
    public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        tank.readFromNBT(registries, tag);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, FaucetEntity entity) {
        if (level.isClientSide) return;

        // get facing
        Direction facing = state.getValue(Faucet.FACING);
        // back - opposite
        Direction backSide = facing.getOpposite();

        // check back block
        BlockPos backPos = pos.relative(backSide);
        BlockState backState = level.getBlockState(backPos);

        // if notch_hevea_log
        if (backState.is(OpenCreationBlocks.NOTCH_HEVEA_LOG.get())) {
            // add 1m latex every 100 tick
            if (level.getGameTime() % 100 == 0) {
                if (entity.getFluidAmount() < entity.getCapacity()) {
                    entity.addLatex(1);
                    entity.setChanged();
                }
            }
        }
    }
}