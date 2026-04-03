package com.sdev.opencreation.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.blocks.BlockStateArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.chunk.LevelChunk;

public class OCChunkCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext context) {
        dispatcher.register(Commands.literal("occhunk")
                .requires(source -> source.hasPermission(2))
                .then(Commands.argument("block", BlockStateArgument.block(context))
                        .executes(ctx -> execute(ctx,
                                BlockStateArgument.getBlock(ctx, "block").getState().getBlock()
                        ))
                )
        );
    }


    private static int execute(CommandContext<CommandSourceStack> context, Block block) throws CommandSyntaxException {

        ServerPlayer player = context.getSource().getPlayerOrException();

        ServerLevel level = player.serverLevel();

        ChunkPos chunkPos = player.chunkPosition();

        LevelChunk chunk = level.getChunk(chunkPos.x, chunkPos.z);

        int count = 0;

        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

        for (int x = 0; x < 16; x++) {
            for (int y = level.getMinBuildHeight(); y < level.getMaxBuildHeight(); y++) {
                for (int z = 0; z < 16; z++) {

                    pos.set(
                            chunkPos.getMinBlockX() + x,
                            y,
                            chunkPos.getMinBlockZ() + z
                    );

                    if (chunk.getBlockState(pos).getBlock() == block) {
                        count++;
                    }
                }
            }
        }

        final int finalCount = count;

        context.getSource().sendSuccess(() ->
                        Component.literal("Найдено блоков: " + finalCount),
                false
        );

        return finalCount;
    }
}