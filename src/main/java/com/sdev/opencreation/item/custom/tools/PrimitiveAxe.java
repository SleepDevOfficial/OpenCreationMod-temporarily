package com.sdev.opencreation.item.custom.tools;

import com.sdev.opencreation.item.OpenCreationItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Map;
import java.util.Random;

public class PrimitiveAxe extends AxeItem {

    private static final Random RANDOM = new Random();

    private static final Map<Block, Block> STRIPPED_MAP = Map.ofEntries(
            Map.entry(Blocks.OAK_WOOD, Blocks.STRIPPED_OAK_WOOD),
            Map.entry(Blocks.OAK_LOG, Blocks.STRIPPED_OAK_LOG),
            Map.entry(Blocks.DARK_OAK_WOOD, Blocks.STRIPPED_DARK_OAK_WOOD),
            Map.entry(Blocks.DARK_OAK_LOG, Blocks.STRIPPED_DARK_OAK_LOG),
            Map.entry(Blocks.ACACIA_WOOD, Blocks.STRIPPED_ACACIA_WOOD),
            Map.entry(Blocks.ACACIA_LOG, Blocks.STRIPPED_ACACIA_LOG),
            Map.entry(Blocks.CHERRY_WOOD, Blocks.STRIPPED_CHERRY_WOOD),
            Map.entry(Blocks.CHERRY_LOG, Blocks.STRIPPED_CHERRY_LOG),
            Map.entry(Blocks.BIRCH_WOOD, Blocks.STRIPPED_BIRCH_WOOD),
            Map.entry(Blocks.BIRCH_LOG, Blocks.STRIPPED_BIRCH_LOG),
            Map.entry(Blocks.JUNGLE_WOOD, Blocks.STRIPPED_JUNGLE_WOOD),
            Map.entry(Blocks.JUNGLE_LOG, Blocks.STRIPPED_JUNGLE_LOG),
            Map.entry(Blocks.SPRUCE_WOOD, Blocks.STRIPPED_SPRUCE_WOOD),
            Map.entry(Blocks.SPRUCE_LOG, Blocks.STRIPPED_SPRUCE_LOG),
            Map.entry(Blocks.WARPED_STEM, Blocks.STRIPPED_WARPED_STEM),
            Map.entry(Blocks.WARPED_HYPHAE, Blocks.STRIPPED_WARPED_HYPHAE),
            Map.entry(Blocks.CRIMSON_STEM, Blocks.STRIPPED_CRIMSON_STEM),
            Map.entry(Blocks.CRIMSON_HYPHAE, Blocks.STRIPPED_CRIMSON_HYPHAE),
            Map.entry(Blocks.MANGROVE_WOOD, Blocks.STRIPPED_MANGROVE_WOOD),
            Map.entry(Blocks.MANGROVE_LOG, Blocks.STRIPPED_MANGROVE_LOG),
            Map.entry(Blocks.BAMBOO_BLOCK, Blocks.STRIPPED_BAMBOO_BLOCK)
    );

    public PrimitiveAxe(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();

        BlockState state = world.getBlockState(pos);
        Block block = state.getBlock();

        if (STRIPPED_MAP.containsKey(block)) {
            if (!world.isClientSide()) {
                Block stripped = STRIPPED_MAP.get(block);
                world.setBlockAndUpdate(pos, stripped.defaultBlockState());
                world.playSound(null, pos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0f, 1.0f);

                int count = 1 + RANDOM.nextInt(3);
                for (int i = 0; i < count; i++) {
                    Block.popResource(world, pos, new ItemStack(OpenCreationItems.BARK.get()));
                }

                if (player != null) {
                    ItemStack itemstack = context.getItemInHand(); itemstack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(context.getHand()));
                }
            }

            return InteractionResult.sidedSuccess(world.isClientSide());
        }

        return super.useOn(context);
    }
}