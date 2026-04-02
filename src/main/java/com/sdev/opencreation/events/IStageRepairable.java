package com.sdev.opencreation.events;

import net.minecraft.world.item.ItemStack;

public interface IStageRepairable {
    boolean hasStages();
    int getMaxStages();
    boolean isValidStageRepair(ItemStack stack, ItemStack repairCandidate);
    void advanceStage(ItemStack stack);
    int getStage(ItemStack stack);
    void setStage(ItemStack stack, int stage);
}
