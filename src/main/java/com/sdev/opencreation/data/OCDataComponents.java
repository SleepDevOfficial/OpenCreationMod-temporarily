package com.sdev.opencreation.data;

import com.sdev.opencreation.OpenCreation;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class OCDataComponents {
    public static final DeferredRegister.DataComponents DATA_COMPONENTS =
            DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, OpenCreation.MODID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<BlueprintData>> BLUEPRINT_DATA =
            DATA_COMPONENTS.registerComponentType("blueprint_data",
                    builder -> builder
                            .persistent(BlueprintData.CODEC)       // codec for save on disk
                            .networkSynchronized(BlueprintData.STREAM_CODEC) // codec for sync with client
            );
}
