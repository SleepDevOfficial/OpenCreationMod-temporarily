package com.sdev.opencreation.fluid;

import com.sdev.opencreation.OpenCreation;
import com.sdev.opencreation.item.OpenCreationItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.*;

import java.util.function.Consumer;

import static com.sdev.opencreation.item.OpenCreationItems.ITEMS;

public class FluidDeferredRegister {
    public static final DeferredRegister.Blocks FLUID_BLOCKS =
            DeferredRegister.createBlocks(OpenCreation.MODID);

    public final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(
                    NeoForgeRegistries.Keys.FLUID_TYPES,
                    OpenCreation.MODID
            );

    public final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(
                    Registries.FLUID,
                    OpenCreation.MODID
            );

    public FluidRegistryObject register(
            String name,
            int density,
            int viscosity,
            int temperature
    ) {

        var fluidType = FLUID_TYPES.register(name + "_type",
                () -> new FluidType(
                        FluidType.Properties.create()
                                .density(density)
                                .viscosity(viscosity)
                                .temperature(temperature)
                ) {
                    @Override
                    @SuppressWarnings("removal")
                    public void initializeClient(
                            Consumer<IClientFluidTypeExtensions> consumer
                    ) {
                        consumer.accept(new IClientFluidTypeExtensions() {

                            private final ResourceLocation STILL =
                                    ResourceLocation.fromNamespaceAndPath(
                                            OpenCreation.MODID,
                                            "block/fluid/still/" + name + "_still"
                                    );

                            private final ResourceLocation FLOW =
                                    ResourceLocation.fromNamespaceAndPath(
                                            OpenCreation.MODID,
                                            "block/fluid/flow/" + name + "_flow"
                                    );

                            @Override
                            public ResourceLocation getStillTexture() {
                                return STILL;
                            }

                            @Override
                            public ResourceLocation getFlowingTexture() {
                                return FLOW;
                            }
                        });
                    }
                });

        final DeferredHolder<Fluid, BaseFlowingFluid.Source>[] source = new DeferredHolder[1];
        final DeferredHolder<Fluid, BaseFlowingFluid.Flowing>[] flowing = new DeferredHolder[1];
        final DeferredBlock<LiquidBlock>[] block = new DeferredBlock[1];
        final DeferredItem<BucketItem>[] bucket = new DeferredItem[1];

        BaseFlowingFluid.Properties props =
                new BaseFlowingFluid.Properties(
                        fluidType,
                        () -> source[0].get(),
                        () -> flowing[0].get()
                );

        source[0] = FLUIDS.register(name,
                () -> new BaseFlowingFluid.Source(props));

        flowing[0] = FLUIDS.register("flowing_" + name,
                () -> new BaseFlowingFluid.Flowing(props));

        block[0] = FLUID_BLOCKS.register(name + "_block",
                () -> new LiquidBlock(
                        source[0].get(),
                        BlockBehaviour.Properties.of()
                                .mapColor(MapColor.WATER)
                                .noCollission()
                                .replaceable()
                                .strength(100F)
                ));

        bucket[0] = ITEMS.register(name + "_bucket",
                () -> new BucketItem(
                        source[0].get(),
                        new Item.Properties()
                                .stacksTo(1)
                                .craftRemainder(Items.BUCKET)
                ));

        props.block(block[0]).bucket(bucket[0]);

        return new FluidRegistryObject(
                fluidType,
                source[0],
                flowing[0],
                block[0],
                bucket[0]
        );
    }
}