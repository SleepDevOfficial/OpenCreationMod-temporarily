package com.sdev.opencreation.item.custom;

import com.sdev.opencreation.blueprint.BlueprintData;
import com.sdev.opencreation.data.OCDataComponents;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;

import java.util.List;

public class BlueprintItem extends Item {
    public BlueprintItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context,
                                List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);

        BlueprintData data = stack.get(OCDataComponents.BLUEPRINT_DATA.get());
        if (data != null) {
            tooltipComponents.add(Component.literal("Тип: " + data.russianName()));
        }
    }
}