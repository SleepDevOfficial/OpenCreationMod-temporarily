package com.sdev.opencreation.block.bes;

import com.sdev.opencreation.OpenCreation;
import com.sdev.opencreation.block.OpenCreationBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class OpenCreationBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, OpenCreation.MODID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TestBlockEntity>> TEST_BLOCK =
            BLOCK_ENTITIES.register("test_block",
                    () -> BlockEntityType.Builder.of(
                            TestBlockEntity::new, OpenCreationBlocks.TEST_BLOCK.get()
                    ).build(null));

    public static final Supplier<BlockEntityType<KaminiteBarrelEntity>> KAMINITE_BARREL =
            BLOCK_ENTITIES.register("item_collector",
                    () -> BlockEntityType.Builder.of(
                            KaminiteBarrelEntity::new,
                            OpenCreationBlocks.KAMINITE_BARREL.get()
                    ).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<DraftTablePrimitiveEntity>> DRAFT_TABLE =
            BLOCK_ENTITIES.register("draft_table",
                    () -> BlockEntityType.Builder.of(
                            DraftTablePrimitiveEntity::new, OpenCreationBlocks.DRAFT_TABLE_PRIMITIVE.get()
                    ).build(null));
}
