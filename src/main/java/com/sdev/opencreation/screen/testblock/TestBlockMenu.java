package com.sdev.opencreation.screen.testblock;

import com.sdev.opencreation.block.bes.TestBlockEntity;
import com.sdev.opencreation.screen.OpenCreationMenus;
import com.sdev.opencreation.util.OpenCreationTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.SlotItemHandler;

import javax.annotation.Nullable;

public class TestBlockMenu extends AbstractContainerMenu {
    private final TestBlockEntity blockEntity;

    public TestBlockMenu(int containerId, Inventory playerInventory) {
        this(containerId, playerInventory, null);
    }

    public TestBlockMenu(int containerId, Inventory playerInventory, @Nullable TestBlockEntity blockEntity) {
        super(OpenCreationMenus.TEST_BLOCK_MENU.get(), containerId);
        this.blockEntity = blockEntity;

        ItemStackHandler handler;
        if (blockEntity != null) {
            handler = blockEntity.getItemHandler();
        } else {
            handler = new ItemStackHandler(13);
        }
        //input
        addSlot(new SlotItemHandler(handler, 0, 26, 20));
        addSlot(new SlotItemHandler(handler, 1, 44, 20));
        addSlot(new SlotItemHandler(handler, 2, 62, 20));
        addSlot(new SlotItemHandler(handler, 3, 26, 38));
        addSlot(new SlotItemHandler(handler, 4, 44, 38));
        addSlot(new SlotItemHandler(handler, 5, 62, 38));
        // output
        addSlot(new SlotItemHandler(handler, 6, 98, 20));
        addSlot(new SlotItemHandler(handler, 7, 116, 20));
        addSlot(new SlotItemHandler(handler, 8, 98, 38));
        addSlot(new SlotItemHandler(handler, 9, 116, 38));
        // fuel
        addSlot(new SlotItemHandler(handler, 10, 62, 60));
        addSlot(new SlotItemHandler(handler, 11, 80, 60));
        // blueprint
        addSlot(new SlotItemHandler(handler, 12, 80, 20));

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(
                        playerInventory,
                        col + row * 9 + 9,
                        8 + col * 18,
                        84 + row * 18
                ));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(
                    playerInventory,
                    col,
                    8 + col * 18,
                    142
            ));
        }
    }

    @Override
    public boolean stillValid(Player player) {
        if (blockEntity == null) return true;
        BlockPos pos = blockEntity.getBlockPos();
        return player.distanceToSqr(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5) <= 64.0;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasItem()) {
            ItemStack stackInSlot = slot.getItem();
            itemstack = stackInSlot.copy();

            int totalSlots = 13;
            int playerInvStart = totalSlots;
            int playerInvEnd = playerInvStart + 27;
            int hotbarStart = playerInvEnd;
            int hotbarEnd = hotbarStart + 9;

            if (index < totalSlots) {
                if (!this.moveItemStackTo(stackInSlot, playerInvStart, hotbarEnd, true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (stackInSlot.getBurnTime(null) > 0) {
                    if (this.moveItemStackTo(stackInSlot, 10, 12, false)) {
                        return itemstack;
                    }
                }

                if (!stackInSlot.isEmpty() && stackInSlot.is(OpenCreationTags.Items.BLUEPRINTS)) {
                    if (this.moveItemStackTo(stackInSlot, 12, 13, false)) {
                        return itemstack;
                    }
                }

                if (!stackInSlot.isEmpty()) {
                    if (!this.moveItemStackTo(stackInSlot, 0, 6, false)) {
                        if (index < hotbarStart) {
                            if (!this.moveItemStackTo(stackInSlot, hotbarStart, hotbarEnd, false)) {
                                return ItemStack.EMPTY;
                            }
                        } else if (index < hotbarEnd) {
                            if (!this.moveItemStackTo(stackInSlot, playerInvStart, playerInvEnd, false)) {
                                return ItemStack.EMPTY;
                            }
                        }
                    }
                }
            }

            if (stackInSlot.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (stackInSlot.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, stackInSlot);
        }

        return itemstack;
    }
}