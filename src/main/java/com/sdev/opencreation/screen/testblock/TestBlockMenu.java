package com.sdev.opencreation.screen.testblock;

import com.sdev.opencreation.block.bes.TestBlockEntity;
import com.sdev.opencreation.screen.OpenCreationMenus;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
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

       if (blockEntity != null) {
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    addSlot(new SlotItemHandler(
                            blockEntity.getItemHandler(),
                            col + row * 3,
                            62 + col * 18,
                            17 + row * 18
                    ));
                }
            }
        } else {
            for (int i = 0; i < 9; i++) {
                addSlot(new SlotItemHandler(new ItemStackHandler(9), i, 62 + (i % 3) * 18, 17 + (i / 3) * 18));
            }
        }

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
        return true;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasItem()) {
            ItemStack stack = slot.getItem();
            itemstack = stack.copy();

            if (index < 9) {
                if (!this.moveItemStackTo(stack, 9, 45, true)) {
                    return ItemStack.EMPTY;
                }
            }
            else {
                if (!this.moveItemStackTo(stack, 0, 9, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (stack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            slot.onTake(player, stack);
        }

        return itemstack;
    }
}
