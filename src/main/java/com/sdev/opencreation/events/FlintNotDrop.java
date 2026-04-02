package com.sdev.opencreation.events;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.bus.api.SubscribeEvent;

public class FlintNotDrop {

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {

        BlockState state = event.getState();
        Player player = event.getPlayer();

        if (state.getBlock() == Blocks.GRAVEL) {
            ItemStack heldItem = player.getMainHandItem();
            if (heldItem.isEmpty() || !(heldItem.getItem() instanceof TieredItem)) {
                event.setCanceled(true);

                if (!event.getLevel().isClientSide()) {
                    event.getLevel().destroyBlock(event.getPos(), false);

                    Block.popResource(
                            (Level) event.getLevel(),
                            event.getPos(),
                            new ItemStack(Blocks.GRAVEL)
                    );
                }
            }
        }
    }
}