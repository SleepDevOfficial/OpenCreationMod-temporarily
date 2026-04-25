package com.sdev.opencreation.event;

import com.sdev.opencreation.util.OpenCreationTiers;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import java.util.Set;

@EventBusSubscriber
public class BreakVBlocks {
    private static final Set<TagKey<Block>> VBLOCKS = Set.of(
            BlockTags.LOGS, BlockTags.PLANKS
    );

    @SubscribeEvent
    public static void onBreakVBlock(PlayerEvent.BreakSpeed event) {

        Player player = event.getEntity();
        ItemStack tool = player.getMainHandItem();


        if (VBLOCKS.stream().anyMatch(tag -> event.getState().is(tag))) {

            Item item = tool.getItem();
            if (!(item instanceof TieredItem) || (item instanceof TieredItem tiered && tiered.getTier() == OpenCreationTiers.PRIMITIVE)) {

                event.setNewSpeed(0f);

                player.displayClientMessage(
                        Component.literal("Нужен инструмент получше"),
                        true
                );
            }
        }
    }

}
