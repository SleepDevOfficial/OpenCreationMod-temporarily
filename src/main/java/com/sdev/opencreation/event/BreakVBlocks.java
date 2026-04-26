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
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

import java.util.Set;

@EventBusSubscriber
public class BreakVBlocks {
    private static final Set<TagKey<Block>> VBLOCKS = Set.of(
            BlockTags.LOGS, BlockTags.PLANKS
    );

    @SubscribeEvent
    public static void onLeftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
        Player player = event.getEntity();
        ItemStack tool = player.getMainHandItem();
        BlockState state = event.getLevel().getBlockState(event.getPos());

        if (VBLOCKS.stream().anyMatch(tag -> state.is(tag))) {
            Item item = tool.getItem();
            if (!(item instanceof TieredItem) ||
                    (item instanceof TieredItem tiered && tiered.getTier() == OpenCreationTiers.PRIMITIVE)) {

                event.setCanceled(true); // Отменяем начало разрушения
                player.displayClientMessage(
                        Component.literal("Нужен инструмент получше"),
                        true
                );
            }
        }
    }

}
