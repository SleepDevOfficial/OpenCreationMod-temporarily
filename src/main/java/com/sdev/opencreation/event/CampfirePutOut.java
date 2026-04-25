package com.sdev.opencreation.event;

import com.sdev.opencreation.item.OpenCreationItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber
public class CampfirePutOut {

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if (!(event.getLevel() instanceof ServerLevel level)) return;

        BlockState state = level.getBlockState(event.getPos());

        if (!(state.getBlock() instanceof CampfireBlock)) return;

        if (state.getValue(CampfireBlock.LIT)) {
            level.scheduleTick(event.getPos(), state.getBlock(), 1);
            level.getServer().execute(() -> {
                BlockState newState = level.getBlockState(event.getPos());
                if (!newState.getValue(CampfireBlock.LIT)) {
                    int count = 1 + level.random.nextInt(2);
                    ItemStack drop = new ItemStack(OpenCreationItems.ASH.get(), count);
                    CampfireBlock.popResource(level, event.getPos(), drop);
                }
            });
        }
    }
}