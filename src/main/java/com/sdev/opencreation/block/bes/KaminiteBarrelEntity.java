package com.sdev.opencreation.block.bes;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.items.ItemStackHandler;

import java.util.List;

public class KaminiteBarrelEntity extends BlockEntity {

    private int waterAmount = 0;
    public static final int MAX_WATER = 4000;
    public int getWater() { return waterAmount; }
    public void setWater(int amount) { waterAmount = Mth.clamp(amount, 0, MAX_WATER); }
    public KaminiteBarrelEntity(BlockPos pos, BlockState state) {
        super(OpenCreationBlockEntities.KAMINITE_BARREL.get(), pos, state);
    }

    private final ItemStackHandler inventory = new ItemStackHandler(5);
     public ItemStackHandler getInventory() {
         return inventory;
     }
    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        tag.putInt("water", waterAmount);
        tag.put("inventory", inventory.serializeNBT(provider));
        super.saveAdditional(tag, provider);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.loadAdditional(tag, provider);
        waterAmount = tag.getInt("water");
        inventory.deserializeNBT(provider, tag.getCompound("inventory"));
    }

    public void giveToPlayer(Player player) {
        if (level == null || level.isClientSide) return;

        for (int i = 0; i < inventory.getSlots(); i++) {
            ItemStack stack = inventory.getStackInSlot(i);

            if (!stack.isEmpty()) {
                player.getInventory().placeItemBackInInventory(stack);
                inventory.setStackInSlot(i, ItemStack.EMPTY);
            }
        }

        setChanged();
    }

    public boolean hasItem(Item item, int count) {
        int found = 0;

        for (int i = 0; i < inventory.getSlots(); i++) {
            ItemStack stack = inventory.getStackInSlot(i);
            if (stack.getItem() == item) {
                found += stack.getCount();
            }
        }

        return found >= count;
    }

    public void removeItem(Item item, int count) {
        for (int i = 0; i < inventory.getSlots(); i++) {
            ItemStack stack = inventory.getStackInSlot(i);

            if (stack.getItem() == item) {
                int remove = Math.min(count, stack.getCount());
                stack.shrink(remove);
                count -= remove;

                if (count <= 0) break;
            }
        }
    }
}
