package com.sdev.opencreation.screen.draft_table;

import com.sdev.opencreation.block.custom.DraftTablePrimitive;
import com.sdev.opencreation.data.BlueprintData;
import com.sdev.opencreation.blueprint.BlueprintRegistry;
import com.sdev.opencreation.data.OCDataComponents;
import com.sdev.opencreation.item.OpenCreationItems;
import com.sdev.opencreation.screen.OpenCreationMenus;
import com.sdev.opencreation.util.slot.ItemFilterSlot;
import com.sdev.opencreation.util.slot.OutputSlot;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class DraftTableMenu extends AbstractContainerMenu {
    private final Container container;
    private final ContainerLevelAccess access;
    private final DataSlot selectedIndexData = DataSlot.shared(new int[]{0}, 0);

    public DraftTableMenu(int id, Inventory playerInventory, Container container, ContainerLevelAccess access) {
        super(OpenCreationMenus.DRAFT_TABLE_MENU.get(), id);

        this.container = container;
        this.access = access;

        this.addSlot(new ItemFilterSlot(container, 0, 62, 30, OpenCreationItems.PENCIL.get()));
        this.addSlot(new ItemFilterSlot(container, 1, 80, 30, OpenCreationItems.RULER.get()));
        this.addSlot(new ItemFilterSlot(container, 2, 98, 30, OpenCreationItems.DRAFTING_COMPASS.get()));
        this.addSlot(new ItemFilterSlot(container, 3, 30, 52, Items.PAPER));
        this.addSlot(new OutputSlot(container, 4, 130, 52));
        this.addDataSlot(selectedIndexData);

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);

    }

    public DraftTableMenu(int id, Inventory playerInventory) {
        this(id, playerInventory, new SimpleContainer(5), ContainerLevelAccess.NULL);
    }

    private void addPlayerInventory(Inventory inventory) {
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.addSlot(new Slot(inventory, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory inventory) {
        for (int col = 0; col < 9; ++col) {
            this.addSlot(new Slot(inventory, col, 8 + col * 18, 142));
        }
    }

    public void next() {
        selectedIndexData.set((selectedIndexData.get() + 1) % BlueprintRegistry.getTypes().size());
    }

    public void prev() {
        int size = BlueprintRegistry.getTypes().size();
        selectedIndexData.set((selectedIndexData.get() - 1 + size) % size);
    }

    public int getSelectedIndex() {
        return selectedIndexData.get();
    }

    @Override
    public boolean stillValid(Player player) {
        return access.evaluate((level, pos) ->
                level.getBlockState(pos).getBlock() instanceof DraftTablePrimitive &&
                        player.distanceToSqr(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5) <= 64.0, true);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasItem()) {
            ItemStack stack = slot.getItem();
            itemstack = stack.copy();

            if (index < 5) {
                if (!this.moveItemStackTo(stack, 5, 41, true)) return ItemStack.EMPTY;
            } else {
                if (stack.is(OpenCreationItems.PENCIL.get())) {
                    if (!this.moveItemStackTo(stack, 0, 1, false)) return ItemStack.EMPTY;
                } else if (stack.is(OpenCreationItems.RULER.get())) {
                    if (!this.moveItemStackTo(stack, 1, 2, false)) return ItemStack.EMPTY;
                } else if (stack.is(OpenCreationItems.DRAFTING_COMPASS.get())) {
                    if (!this.moveItemStackTo(stack, 2, 3, false)) return ItemStack.EMPTY;
                } else if (stack.is(Items.PAPER)) {
                    if (!this.moveItemStackTo(stack, 3, 4, false)) return ItemStack.EMPTY;
                } else if (index < 32) {
                    if (!this.moveItemStackTo(stack, 32, 41, false)) return ItemStack.EMPTY;
                } else {
                    if (!this.moveItemStackTo(stack, 5, 32, false)) return ItemStack.EMPTY;
                }
            }

            if (stack.isEmpty()) slot.set(ItemStack.EMPTY);
            else slot.setChanged();
        }
        return itemstack;
    }
    public void craftBlueprint(Player player) {
        if (!container.getItem(4).isEmpty()) return;

        ItemStack pencil = container.getItem(0);
        ItemStack ruler = container.getItem(1);
        ItemStack compass = container.getItem(2);
        ItemStack paper = container.getItem(3);

        if (pencil.isEmpty() || ruler.isEmpty() || compass.isEmpty()) return;
        if (paper.isEmpty()) return;

        BlueprintData data = BlueprintRegistry.getTypes().get(getSelectedIndex());

        ItemStack blueprint = new ItemStack(OpenCreationItems.BLUEPRINT.get());
        blueprint.set(OCDataComponents.BLUEPRINT_DATA.get(), data);

        paper.shrink(1);

        damageTool(pencil, player, 0);
        damageTool(ruler, player, 1);
        damageTool(compass, player, 2);

        container.setItem(4, blueprint);
        container.setChanged();
    }

    private void damageTool(ItemStack stack, Player player, int slot) {
        if (!stack.isDamageableItem()) return;

        stack.setDamageValue(stack.getDamageValue() + 1);

        if (stack.getDamageValue() >= stack.getMaxDamage()) {
            stack.shrink(1);
        }

        container.setItem(slot, stack);
    }

    @Override
    public boolean clickMenuButton(Player player, int id) {
        if (id == 0) {
            craftBlueprint(player);
            return true;
        }
        return false;
    }
}