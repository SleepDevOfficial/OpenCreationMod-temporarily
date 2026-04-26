package com.sdev.opencreation.fluid;

import net.minecraft.world.item.BucketItem;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;

public class FluidRegistryObject {

    private final DeferredHolder<FluidType, ? extends FluidType> fluidType;
    private final DeferredHolder<Fluid, ? extends Fluid> source;
    private final DeferredHolder<Fluid, ? extends Fluid> flowing;
    private final DeferredBlock<LiquidBlock> block;
    private final DeferredItem<BucketItem> bucket;
    private final String name;

    public FluidRegistryObject(
            DeferredHolder<FluidType, ? extends FluidType> fluidType,
            DeferredHolder<Fluid, ? extends Fluid> source,
            DeferredHolder<Fluid, ? extends Fluid> flowing,
            DeferredBlock<LiquidBlock> block,
            DeferredItem<BucketItem> bucket,
            String name
    ) {
        this.fluidType = fluidType;
        this.source = source;
        this.flowing = flowing;
        this.block = block;
        this.bucket = bucket;
        this.name = name;
    }

    public DeferredHolder<FluidType, ? extends FluidType> getFluidType() {
        return fluidType;
    }

    public DeferredHolder<Fluid, ? extends Fluid> getSource() {
        return source;
    }

    public DeferredHolder<Fluid, ? extends Fluid> getFlowing() {
        return flowing;
    }

    public DeferredBlock<LiquidBlock> getBlock() {
        return block;
    }

    public DeferredItem<BucketItem> getBucket() {
        return bucket;
    }

    public String getName() {
        return name;
    }

    public Fluid getSourceFluid() {
        return source.get();
    }

    public Fluid getFlowingFluid() {
        return flowing.get();
    }

    public LiquidBlock getBlockBlock() {
        return block.get();
    }

    public BucketItem getBucketItem() {
        return bucket.get();
    }

    public FluidType getFluidTypeObject() {
        return fluidType.get();
    }
}