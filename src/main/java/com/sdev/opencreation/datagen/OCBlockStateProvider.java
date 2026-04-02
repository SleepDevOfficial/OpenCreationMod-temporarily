package com.sdev.opencreation.datagen;

import com.sdev.opencreation.OpenCreation;
import com.sdev.opencreation.block.OpenCreationBlocks;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class OCBlockStateProvider extends BlockStateProvider {
    public OCBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, OpenCreation.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(OpenCreationBlocks.TEST_BLOCK);
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
}
