package com.sdev.opencreation.multiblock;

import com.sdev.opencreation.block.OpenCreationBlocks;
import net.minecraft.world.level.block.Blocks;

public class OCMultiblockPatterns {

    public static void register() {
        MultiblockRegistry.register(
                OpenCreationBlocks.KAMINITE_FURNACE_CONTROLLER.get(),
                new MultiblockRegistry.MultiblockPattern()
                        .block(0, 0, 1, "minecraft:bricks")
                        .block(0, 1, 2, "opencreation:test_block")
        );
    }
}