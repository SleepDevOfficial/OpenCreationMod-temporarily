package com.sdev.opencreation.item.custom.tools;

import com.sdev.opencreation.event.IStageRepairable;
import com.sdev.opencreation.item.OpenCreationItems;
import com.sdev.opencreation.util.RepairDefinition;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Map;
import java.util.Random;

public class OCAxe extends AxeItem implements IStageRepairable {

    private static final Random RANDOM = new Random();
    private final Tier tier;
    private final RepairDefinition repair;
    private static final String STAGE_KEY = "RepairStage";

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

    private static final Map<Block, ItemStack> PLANKS_MAP = Map.ofEntries(
            Map.entry(Blocks.STRIPPED_OAK_LOG, new ItemStack(OpenCreationItems.OAK_PLANK.get(), 3)),
            Map.entry(Blocks.STRIPPED_OAK_WOOD, new ItemStack(OpenCreationItems.OAK_PLANK.get(), 3)),
            Map.entry(Blocks.STRIPPED_DARK_OAK_LOG, new ItemStack(OpenCreationItems.DARK_OAK_PLANK.get(), 3)),
            Map.entry(Blocks.STRIPPED_DARK_OAK_WOOD, new ItemStack(OpenCreationItems.DARK_OAK_PLANK.get(), 3)),
            Map.entry(Blocks.STRIPPED_ACACIA_LOG, new ItemStack(OpenCreationItems.ACACIA_PLANK.get(), 3)),
            Map.entry(Blocks.STRIPPED_ACACIA_WOOD, new ItemStack(OpenCreationItems.ACACIA_PLANK.get(), 3)),
            Map.entry(Blocks.STRIPPED_CHERRY_LOG, new ItemStack(OpenCreationItems.CHERRY_PLANK.get(), 3)),
            Map.entry(Blocks.STRIPPED_CHERRY_WOOD, new ItemStack(OpenCreationItems.CHERRY_PLANK.get(), 3)),
            Map.entry(Blocks.STRIPPED_BIRCH_LOG, new ItemStack(OpenCreationItems.BIRCH_PLANK.get(), 3)),
            Map.entry(Blocks.STRIPPED_BIRCH_WOOD, new ItemStack(OpenCreationItems.BIRCH_PLANK.get(), 3)),
            Map.entry(Blocks.STRIPPED_JUNGLE_LOG, new ItemStack(OpenCreationItems.JUNGLE_PLANK.get(), 3)),
            Map.entry(Blocks.STRIPPED_JUNGLE_WOOD, new ItemStack(OpenCreationItems.JUNGLE_PLANK.get(), 3)),
            Map.entry(Blocks.STRIPPED_SPRUCE_LOG, new ItemStack(OpenCreationItems.SPRUCE_PLANK.get(), 3)),
            Map.entry(Blocks.STRIPPED_SPRUCE_WOOD, new ItemStack(OpenCreationItems.SPRUCE_PLANK.get(), 3)),
            Map.entry(Blocks.STRIPPED_MANGROVE_LOG, new ItemStack(OpenCreationItems.MANGROVE_PLANK.get(), 3)),
            Map.entry(Blocks.STRIPPED_MANGROVE_WOOD, new ItemStack(OpenCreationItems.MANGROVE_PLANK.get(), 3)),
            Map.entry(Blocks.STRIPPED_BAMBOO_BLOCK, new ItemStack(OpenCreationItems.BAMBOO_PLANK.get(), 3)),
            Map.entry(Blocks.STRIPPED_CRIMSON_STEM, new ItemStack(OpenCreationItems.CRIMSON_PLANK.get(), 3)),
            Map.entry(Blocks.STRIPPED_CRIMSON_HYPHAE, new ItemStack(OpenCreationItems.CRIMSON_PLANK.get(), 3)),
            Map.entry(Blocks.STRIPPED_WARPED_STEM, new ItemStack(OpenCreationItems.WARPED_PLANK.get(), 3)),
            Map.entry(Blocks.STRIPPED_WARPED_HYPHAE, new ItemStack(OpenCreationItems.WARPED_PLANK.get(), 3))
    );
    public OCAxe(Tier tier, Properties properties, RepairDefinition repair) {
        super(tier, properties);
        this.tier = tier;
        this.repair = repair != null ? repair : RepairDefinition.tier();
    }
    //repair
    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack repairCandidate) {

        if (hasStages()) {
            return isValidStageRepair(stack, repairCandidate);
        }

        return repair.getDefaultIngredient(getTier()).get().test(repairCandidate);
    }
    @Override
    public boolean hasStages() {
        return repair.hasStages();
    }
    @Override
    public int getMaxStages() {
        return repair.hasStages() ? repair.getStages().size() : 0;
    }
    @Override
    public boolean isValidStageRepair(ItemStack stack, ItemStack repairCandidate) {
        int stage = getStage(stack);
        if (stage >= getMaxStages()) return false;

        return repair.getStages().get(stage).get().test(repairCandidate);
    }
    @Override
    public int getStage(ItemStack stack) {
        CustomData data = stack.get(DataComponents.CUSTOM_DATA);
        if (data == null) return 0;
        return data.copyTag().getInt(STAGE_KEY);
    }
    @Override
    public void setStage(ItemStack stack, int stage) {
        CustomData data = stack.get(DataComponents.CUSTOM_DATA);
        CompoundTag tag = data != null ? data.copyTag() : new CompoundTag();

        tag.putInt(STAGE_KEY, stage);

        stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
    }
    @Override
    public void advanceStage(ItemStack stack) {
        setStage(stack, getStage(stack) + 1);
    }

    //abilities
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
                world.playSound(null, pos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS);

                int count = 1 + RANDOM.nextInt(3);
                Block.popResource(world, pos, new ItemStack(OpenCreationItems.BARK.get(), count));

                if (player != null) {
                    ItemStack itemstack = context.getItemInHand();
                    itemstack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(context.getHand()));
                }
            }
            return InteractionResult.sidedSuccess(world.isClientSide());
        }

        if (PLANKS_MAP.containsKey(block)) {
            if (!world.isClientSide()) {
                world.destroyBlock(pos, false);
                world.playSound(null, pos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS);
                Block.popResource(world, pos, PLANKS_MAP.get(block));

                if (player != null) {
                    ItemStack itemstack = context.getItemInHand();
                    itemstack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(context.getHand()));
                }
            }
            return InteractionResult.sidedSuccess(world.isClientSide());
        }

        return super.useOn(context);
    }
}