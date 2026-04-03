package com.sdev.opencreation.datagen;

import com.sdev.opencreation.OpenCreation;
import com.sdev.opencreation.worldgen.OCBiomeModifiers;
import com.sdev.opencreation.worldgen.OCConfiguredFeatures;
import com.sdev.opencreation.worldgen.OCPlacedFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class OCDatapackProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, OCConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, OCPlacedFeatures::bootstrap)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, OCBiomeModifiers::bootstrap);

    public OCDatapackProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(OpenCreation.MODID));
    }
}