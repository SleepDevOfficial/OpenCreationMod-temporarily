package com.sdev.opencreation.block;

import com.sdev.opencreation.OpenCreation;
import com.sdev.opencreation.block.custom.*;
import com.sdev.opencreation.item.OpenCreationItems;
import com.sdev.opencreation.multiblock.kaminite_furnace.KaminiteFurnaceController;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static com.sdev.opencreation.item.OpenCreationItems.ITEMS;

public class OpenCreationBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(OpenCreation.MODID);

    public static final DeferredBlock<Block>
            TEST_BLOCK = registerBlock("test_block",
                    () -> new TestBlock(BlockBehaviour.Properties.of().strength(2.5f, 6f).requiresCorrectToolForDrops())),
            KAMINITE_BARREL = registerBlock("kaminite_barrel",
                    () -> new KaminiteBarrel(BlockBehaviour.Properties.of().strength(2.5f, 6f).requiresCorrectToolForDrops())),
            TWIG_BLOCK = registerBlock("twig_block",
                    () -> new twig(BlockBehaviour.Properties.of().strength(1f, 1f).requiresCorrectToolForDrops())),
            PEBBLE_BLOCK = registerBlock("pebble_block",
                    () -> new pebble(BlockBehaviour.Properties.of().strength(1f, 1f).requiresCorrectToolForDrops())),
            DRAFT_TABLE_PRIMITIVE = registerBlock("draft_table_primitive",
                    () -> new DraftTablePrimitive(BlockBehaviour.Properties.of().strength(1f, 1f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block>
            KAMINITE_FURNACE_CONTROLLER = registerBlock("kaminite_furnace_controller",
            () -> new KaminiteFurnaceController(BlockBehaviour.Properties.of()));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }
    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
}