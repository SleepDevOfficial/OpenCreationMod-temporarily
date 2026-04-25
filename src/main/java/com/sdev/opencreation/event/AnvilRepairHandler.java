package com.sdev.opencreation.event;

import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.AnvilUpdateEvent;

public class AnvilRepairHandler {

    @SubscribeEvent
    public static void onAnvilUpdate(AnvilUpdateEvent event) {

        ItemStack left = event.getLeft();
        ItemStack right = event.getRight();

        if (!(left.getItem() instanceof IStageRepairable item)) return;
        if (!item.hasStages()) return;

        int stage = item.getStage(left);

        if (stage >= (item.getMaxStages())) return;

        if (!item.isValidStageRepair(left, right)) return;

        ItemStack output = left.copy();

        int repairAmount = 50;
        int newDamage = Math.max(0, output.getDamageValue() - repairAmount);
        output.setDamageValue(newDamage);

        stage++;

        if (newDamage == 0) {
            stage = 0;
        }

        item.setStage(output, stage);

        event.setOutput(output);
        event.setCost(1);
        event.setMaterialCost(1);
    }
}
