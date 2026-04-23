package com.sdev.opencreation.item.custom.tools;

import com.sdev.opencreation.events.IStageRepairable;
import com.sdev.opencreation.util.RepairDefinition;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class OCShovelHoe extends DiggerItem implements IStageRepairable {

    private final Tier tier;
    private final RepairDefinition repair;

    private static final String STAGE_KEY = "RepairStage";

    public OCShovelHoe(Tier tier, Properties props, RepairDefinition repair) {
        super(tier, BlockTags.MINEABLE_WITH_SHOVEL, props);
        this.repair = repair != null ? repair : RepairDefinition.tier();
        this.tier = tier;
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {

        if (state.is(BlockTags.MINEABLE_WITH_HOE)) {
            return this.getTier().getSpeed();
        }

        return super.getDestroySpeed(stack, state);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {

        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();
        BlockState state = level.getBlockState(pos);

        if (player != null && player.isShiftKeyDown()) {

            BlockState hoe = getHoeResult(state);

            if (hoe != null) {

                level.setBlock(pos, hoe, 11);

                level.playSound(player, pos,
                        SoundEvents.HOE_TILL,
                        SoundSource.BLOCKS,
                        1.0F, 1.0F);

                damageItem(context, player);

                return InteractionResult.SUCCESS;
            }
        }

        BlockState shovel = getShovelResult(state);

        if (shovel != null) {

            level.setBlock(pos, shovel, 11);

            level.playSound(player, pos,
                    SoundEvents.SHOVEL_FLATTEN,
                    SoundSource.BLOCKS,
                    1.0F, 1.0F);

            damageItem(context, player);

            return InteractionResult.SUCCESS;
        }

        return super.useOn(context);
    }

    private BlockState getShovelResult(BlockState state) {

        if (state.is(Blocks.GRASS_BLOCK))
            return Blocks.DIRT_PATH.defaultBlockState();

        if (state.is(Blocks.DIRT))
            return Blocks.DIRT_PATH.defaultBlockState();

        if (state.is(Blocks.COARSE_DIRT))
            return Blocks.DIRT_PATH.defaultBlockState();

        return null;
    }

    private BlockState getHoeResult(BlockState state) {

        if (state.is(Blocks.DIRT))
            return Blocks.FARMLAND.defaultBlockState();

        if (state.is(Blocks.GRASS_BLOCK))
            return Blocks.FARMLAND.defaultBlockState();

        if (state.is(Blocks.COARSE_DIRT))
            return Blocks.DIRT.defaultBlockState();

        return null;
    }

    private void damageItem(UseOnContext context, Player player) {
        if (player != null) {
            ItemStack stack = context.getItemInHand();
            stack.hurtAndBreak(1, player,
                    LivingEntity.getSlotForHand(context.getHand()));
        }
    }

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
}