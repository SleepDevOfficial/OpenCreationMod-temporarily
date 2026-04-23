package com.sdev.opencreation.block.bes;

import com.sdev.opencreation.block.OCBlockEntities;
import com.sdev.opencreation.util.OpenCreationTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public class TestBlockEntity extends BlockEntity {
    public static final int INPUT_SLOTS = 6;
    public static final int OUTPUT_SLOTS = 4;
    public static final int FUEL_SLOTS = 2;
    public static final int BLUEPRINT_SLOTS = 1;
    public static final int TOTAL_SLOTS = INPUT_SLOTS + OUTPUT_SLOTS + FUEL_SLOTS + BLUEPRINT_SLOTS;

    public static final int INPUT_START = 0;
    public static final int INPUT_END = INPUT_START + INPUT_SLOTS - 1;
    public static final int OUTPUT_START = INPUT_END + 1;
    public static final int OUTPUT_END = OUTPUT_START + OUTPUT_SLOTS - 1;
    public static final int FUEL_START = OUTPUT_END + 1;
    public static final int FUEL_END = FUEL_START + FUEL_SLOTS - 1;
    public static final int BLUEPRINT_SLOT = FUEL_END + 1;

    private int progress;

    public TestBlockEntity(BlockPos pos, BlockState state) {
        super(OCBlockEntities.TEST_BLOCK.get(), pos, state);
    }

    private final ItemStackHandler itemHandler = new ItemStackHandler(TOTAL_SLOTS) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }

        @Override
        public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
            if (slot >= OUTPUT_START && slot <= OUTPUT_END) {
                return false;
            }
            if (slot == BLUEPRINT_SLOT) {
                return stack.is(OpenCreationTags.Items.BLUEPRINTS);
            }
            if (slot >= FUEL_START && slot <= FUEL_END) {
                return stack.getBurnTime(null) > 0;
            }
            return true;
        }
    };

    public void dropContents() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public ItemStackHandler getItemHandler() {
        return itemHandler;
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.saveAdditional(tag, provider);
        tag.put("inventory", itemHandler.serializeNBT(provider));
        tag.putInt("progress", progress);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.loadAdditional(tag, provider);
        if (tag.contains("inventory")) {
            itemHandler.deserializeNBT(provider, tag.getCompound("inventory"));
        }
        progress = tag.getInt("progress");
    }
}