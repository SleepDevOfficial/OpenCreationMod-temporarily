package com.sdev.opencreation.util.slot;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ItemFilterSlot extends Slot {

    private final Item allowedItem;

    public ItemFilterSlot(Container container, int index, int x, int y, Item allowedItem) {
        super(container, index, x, y);
        this.allowedItem = allowedItem;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return stack.getItem() == allowedItem;
    }
}