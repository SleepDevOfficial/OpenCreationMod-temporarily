package com.sdev.opencreation.block;

import com.sdev.opencreation.OpenCreation;
import com.sdev.opencreation.block.custom.*;
import com.sdev.opencreation.multiblock.kaminite_furnace.KaminiteFurnaceController;
import com.sdev.opencreation.util.tree.OCTreeGrowers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static com.sdev.opencreation.item.OpenCreationItems.ITEMS;

public class OpenCreationBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(OpenCreation.MODID);

    public static final DeferredBlock<Block>
            TEST_BLOCK = registerBlock("test_block",
                    () -> new TestBlock(BlockBehaviour.Properties.of().strength(2.5f, 6f).requiresCorrectToolForDrops())),
            BARK_BLOCK = registerBlock("bark_block",
                    () -> new Block(BlockBehaviour.Properties.of().strength(1f, 1f).requiresCorrectToolForDrops())),
            GRANITE_FRAME = registerBlock("granite_frame",
                    () -> new Block(BlockBehaviour.Properties.of().strength(2f, 1f).requiresCorrectToolForDrops())),
            DIORITE_FRAME = registerBlock("diorite_frame",
                    () -> new Block(BlockBehaviour.Properties.of().strength(2f, 1f).requiresCorrectToolForDrops())),
            DEEPSLATE_FRAME = registerBlock("deepslate_frame",
                    () -> new Block(BlockBehaviour.Properties.of().strength(2f, 1f).requiresCorrectToolForDrops())),
            BASALT_FRAME = registerBlock("basalt_frame",
                    () -> new Block(BlockBehaviour.Properties.of().strength(2f, 1f).requiresCorrectToolForDrops())),
            BLACKSTONE_FRAME = registerBlock("blackstone_frame",
                    () -> new Block(BlockBehaviour.Properties.of().strength(1f, 1f).requiresCorrectToolForDrops())),
            KAMINITE_BARREL = registerBlock("kaminite_barrel",
                    () -> new KaminiteBarrel(BlockBehaviour.Properties.of().strength(2.5f, 6f).requiresCorrectToolForDrops())),
            TWIG_BLOCK = registerBlock("twig_block",
                    () -> new twig(BlockBehaviour.Properties.of().strength(1f, 1f).requiresCorrectToolForDrops())),
            PEBBLE_BLOCK = registerBlock("pebble_block",
                    () -> new pebble(BlockBehaviour.Properties.of().strength(1f, 1f).requiresCorrectToolForDrops())),
            DRAFT_TABLE_PRIMITIVE = registerBlock("draft_table_primitive",
                    () -> new DraftTablePrimitive(BlockBehaviour.Properties.of().strength(1f, 1f).requiresCorrectToolForDrops())),
            RECIPE_TABLE = registerBlock("recipe_table_primitive",
                    () -> new RecipeTable(BlockBehaviour.Properties.of().strength(1f, 1f).requiresCorrectToolForDrops())),
            FAUCET = registerBlock("faucet",
                    () -> new Faucet(BlockBehaviour.Properties.of()));

    public static final DeferredBlock<Block>
            HEVEA_LOG = registerBlock("hevea_log",
                    () -> new LogBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG))),
            HEVEA_WOOD = registerBlock("hevea_wood",
                    () -> new LogBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD))),
            STRIPPED_HEVEA_LOG = registerBlock("stripped_hevea_log",
                    () -> new LogBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG))),
            STRIPPED_HEVEA_WOOD = registerBlock("stripped_hevea_wood",
                    () -> new LogBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD))),
            HEVEA_LEAVES = registerBlock("hevea_leaves",
                    () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)) {
                        @Override
                        public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                            return true;
                        }
                        @Override
                        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                            return 60;
                        }
                        @Override
                        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                            return 30;
                        }
                    }),
            HEVEA_SAPLING = registerBlock("hevea_sapling",
                    () -> new SaplingBlock(OCTreeGrowers.HEVEA, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING))),
            NOTCH_HEVEA_LOG = registerBlock("notch_hevea_log",
                    () -> new FullRotatedBlock(BlockBehaviour.Properties.of()));

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