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
                .add(OpenCreationBlocks.TEST_BLOCK.get());
    }
}
