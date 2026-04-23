package com.sdev.opencreation.item.custom;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class RepirableItem extends Item {
    private final TagKey<Item> repairTag;
    private final Item repairItem;
    public RepirableItem(Properties properties, Item repairItem, TagKey<Item> repairTag) {
        super(properties);
        this.repairTag = repairTag;
        this.repairItem = repairItem;
    }

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack repairCandidate) {
        if (repairItem != null && repairCandidate.is(repairItem)) return true;
        if (repairTag != null && repairCandidate.is(repairTag)) return true;
        return false;
    }

}
