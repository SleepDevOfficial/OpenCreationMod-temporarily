package com.sdev.opencreation.item.custom;

import com.sdev.opencreation.events.IStageRepairable;
import com.sdev.opencreation.util.RepairDefinition;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.component.CustomData;

public class OCPickaxe extends PickaxeItem implements IStageRepairable {

    private final Tier tier;
    private final RepairDefinition repair;

    private static final String STAGE_KEY = "RepairStage";

    public OCPickaxe(Tier tier, Properties props, RepairDefinition repair) {
        super(tier, props);
        this.repair = repair != null ? repair : RepairDefinition.tier();
        this.tier = tier;
    }

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack repairCandidate) {

        if (hasStages()) {
            return isValidStageRepair(stack, repairCandidate);
        }

        return repair.getDefaultIngredient(getTier()).get().test(repairCandidate);
    }

    @Override
    public boolean hasStages() {
        return repair.hasStages();
    }

    @Override
    public int getMaxStages() {
        return repair.hasStages() ? repair.getStages().size() : 0;
    }

    @Override
    public boolean isValidStageRepair(ItemStack stack, ItemStack repairCandidate) {
        int stage = getStage(stack);
        if (stage >= getMaxStages()) return false;

        return repair.getStages().get(stage).get().test(repairCandidate);
    }

    @Override
    public int getStage(ItemStack stack) {
        CustomData data = stack.get(DataComponents.CUSTOM_DATA);
        if (data == null) return 0;
        return data.copyTag().getInt(STAGE_KEY);
    }

    @Override
    public void setStage(ItemStack stack, int stage) {
        CustomData data = stack.get(DataComponents.CUSTOM_DATA);
        CompoundTag tag = data != null ? data.copyTag() : new CompoundTag();

        tag.putInt(STAGE_KEY, stage);

        stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
    }

    @Override
    public void advanceStage(ItemStack stack) {
        setStage(stack, getStage(stack) + 1);
    }
}
