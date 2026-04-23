package com.sdev.opencreation.events;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.BlockEvent;
@EventBusSubscriber
public class CampfirePutOut {

    @SubscribeEvent
    public static void onBlockStateChange(BlockEvent.NeighborNotifyEvent event) {
        if (!(event.getLevel() instanceof ServerLevel level)) return;

        BlockPos pos = event.getPos();
        BlockState state = level.getBlockState(pos);

        if (!(state.getBlock() instanceof CampfireBlock)) return;

        if (!state.getValue(CampfireBlock.LIT)) {
            int count = 1 + level.random.nextInt(2);
            ItemStack drop = new ItemStack(Items.GUNPOWDER, count);
            CampfireBlock.popResource(level, pos, drop);
        }
    }
}