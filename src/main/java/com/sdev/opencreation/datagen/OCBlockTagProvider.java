package com.sdev.opencreation.datagen;

import com.sdev.opencreation.OpenCreation;
import com.sdev.opencreation.block.OpenCreationBlocks;
import com.sdev.opencreation.item.OpenCreationItems;
import com.sdev.opencreation.util.OpenCreationTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class OCBlockTagProvider extends BlockTagsProvider {
    public OCBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, OpenCreation.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        tag(OpenCreationTags.Blocks.NEEDS_PRIMITIVE_TOOL)
                .addTag(BlockTags.NEEDS_STONE_TOOL)
                .remove(BlockTags.COPPER_ORES)
                .remove(BlockTags.IRON_ORES);

        tag(OpenCreationTags.Blocks.INCORRECT_FOR_PRIMITIVE_TOOL)
                .addTag(BlockTags.INCORRECT_FOR_WOODEN_TOOL)
                .remove(OpenCreationTags.Blocks.NEEDS_PRIMITIVE_TOOL);

        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(OpenCreationBlocks.TEST_BLOCK.get())
                .add(OpenCreationBlocks.BARK_BLOCK.get())
                .add(OpenCreationBlocks.GRANITE_FRAME.get())
                .add(OpenCreationBlocks.DIORITE_FRAME.get())
                .add(OpenCreationBlocks.DEEPSLATE_FRAME.get())
                .add(OpenCreationBlocks.BASALT_FRAME.get())
                .add(OpenCreationBlocks.BLACKSTONE_FRAME.get());

        tag(BlockTags.LOGS)
                .add(OpenCreationBlocks.HEVEA_LOG.get());

        tag(BlockTags.SAPLINGS)
                .add(OpenCreationBlocks.HEVEA_SAPLING.get());

        tag(BlockTags.LOGS_THAT_BURN)
                .add(OpenCreationBlocks.HEVEA_LOG.get())
                .add(OpenCreationBlocks.HEVEA_WOOD.get())
                .add(OpenCreationBlocks.STRIPPED_HEVEA_LOG.get())
                .add(OpenCreationBlocks.STRIPPED_HEVEA_WOOD.get());
    }
}
