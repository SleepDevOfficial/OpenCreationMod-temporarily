package com.sdev.opencreation.multiblock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class MultiblockRegistry {

    private static final Map<Block, MultiblockPattern> PATTERNS = new HashMap<>();

    public static void register(Block controllerBlock, MultiblockPattern pattern) {
        PATTERNS.put(controllerBlock, pattern);
    }

    public static boolean check(Block controllerBlock, Level level, BlockPos pos, Direction facing) {
        MultiblockPattern pattern = PATTERNS.get(controllerBlock);
        if (pattern == null) {
            return false;
        }
        return pattern.matches(level, pos, facing);
    }

    public static class MultiblockPattern {
        private final Map<BlockPos, Predicate<BlockState>> blocks = new HashMap<>();

        public MultiblockPattern block(int x, int y, int z, String blockId) {
            ResourceLocation id = ResourceLocation.tryParse(blockId);
            Block block = BuiltInRegistries.BLOCK.get(id);
            blocks.put(new BlockPos(x, y, z), state -> state.is(block));
            return this;
        }

        public MultiblockPattern block(int x, int y, int z, Predicate<BlockState> predicate) {
            blocks.put(new BlockPos(x, y, z), predicate);
            return this;
        }

        public boolean matches(Level level, BlockPos controllerPos, Direction facing) {
            Direction back = facing.getOpposite();
            Direction right = facing.getClockWise();

            for (Map.Entry<BlockPos, Predicate<BlockState>> entry : blocks.entrySet()) {
                BlockPos relative = entry.getKey();
                BlockPos worldPos = controllerPos;

                if (relative.getZ() != 0) {
                    worldPos = worldPos.relative(back, relative.getZ());
                }
                if (relative.getX() != 0) {
                    worldPos = worldPos.relative(right, relative.getX());
                }
                if (relative.getY() != 0) {
                    worldPos = worldPos.relative(relative.getY() > 0 ? Direction.UP : Direction.DOWN, Math.abs(relative.getY()));
                }

                if (!entry.getValue().test(level.getBlockState(worldPos))) {
                    return false;
                }
            }

            return true;
        }
    }
}