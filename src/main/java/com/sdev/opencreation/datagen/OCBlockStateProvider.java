package com.sdev.opencreation.datagen;

import com.sdev.opencreation.OpenCreation;
import com.sdev.opencreation.block.OpenCreationBlocks;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class OCBlockStateProvider extends BlockStateProvider {
    public OCBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, OpenCreation.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(OpenCreationBlocks.TEST_BLOCK);
        orientableWithItem(OpenCreationBlocks.KAMINITE_FURNACE_CONTROLLER);
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
    private void orientableWithItem(DeferredBlock<?> deferredBlock) {
        Block block = deferredBlock.get();
        String name = deferredBlock.getId().getPath();

        ResourceLocation down = modLoc("block/orientable/" + name + "/" + name + "_down");
        ResourceLocation up = modLoc("block/orientable/" + name + "/" + name + "_up");
        ResourceLocation north = modLoc("block/orientable/" + name + "/" + name + "_front");
        ResourceLocation south = modLoc("block/orientable/" + name + "/" + name + "_back");
        ResourceLocation west = modLoc("block/orientable/" + name + "/" + name + "_left");
        ResourceLocation east = modLoc("block/orientable/" + name + "/" + name + "_right");

        ModelFile model = models().cube(name, down, up, north, south, west, east);

        getVariantBuilder(block)
                .partialState().with(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH)
                .addModels(new ConfiguredModel(model, 0, 0, false))
                .partialState().with(BlockStateProperties.HORIZONTAL_FACING, Direction.EAST)
                .addModels(new ConfiguredModel(model, 0, 90, false))
                .partialState().with(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH)
                .addModels(new ConfiguredModel(model, 0, 180, false))
                .partialState().with(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST)
                .addModels(new ConfiguredModel(model, 0, 270, false));

        simpleBlockItem(block, model);
    }

    private void directionalWithItem(DeferredBlock<?> deferredBlock) {
        Block block = deferredBlock.get();
        String name = deferredBlock.getId().getPath();

        ResourceLocation front = modLoc("block/" + name + "_front");
        ResourceLocation side = modLoc("block/" + name + "_side");
        ResourceLocation top = modLoc("block/" + name + "_top");
        ResourceLocation bottom = modLoc("block/" + name + "_bottom");

        ModelFile model = models().orientable(name, side, front, top)
                .texture("bottom", bottom);

        getVariantBuilder(block)
                .partialState().with(BlockStateProperties.FACING, Direction.NORTH)
                .addModels(new ConfiguredModel(model, 0, 0, false))
                .partialState().with(BlockStateProperties.FACING, Direction.EAST)
                .addModels(new ConfiguredModel(model, 0, 90, false))
                .partialState().with(BlockStateProperties.FACING, Direction.SOUTH)
                .addModels(new ConfiguredModel(model, 0, 180, false))
                .partialState().with(BlockStateProperties.FACING, Direction.WEST)
                .addModels(new ConfiguredModel(model, 0, 270, false))
                .partialState().with(BlockStateProperties.FACING, Direction.UP)
                .addModels(new ConfiguredModel(model, 270, 0, false))
                .partialState().with(BlockStateProperties.FACING, Direction.DOWN)
                .addModels(new ConfiguredModel(model, 90, 0, false));

        simpleBlockItem(block, model);
    }
}
