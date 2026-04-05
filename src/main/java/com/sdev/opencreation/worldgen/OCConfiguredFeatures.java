package com.sdev.opencreation.worldgen;


import com.sdev.opencreation.OpenCreation;
import com.sdev.opencreation.block.OpenCreationBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

import java.util.List;

public class OCConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> PEBBLE_KEY = registerKey("pebble");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TWIG_KEY = registerKey("twig");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {

        register(context, PEBBLE_KEY, Feature.RANDOM_PATCH,
                new RandomPatchConfiguration(
                        3, 0, 0,
                        PlacementUtils.onlyWhenEmpty(
                                Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        BlockStateProvider.simple(
                                                OpenCreationBlocks.PEBBLE_BLOCK.get().defaultBlockState()
                                        )
                                )
                        )
                )
        );

        register(context, TWIG_KEY, Feature.RANDOM_PATCH,
                new RandomPatchConfiguration(
                        3, 0, 0,
                        PlacementUtils.onlyWhenEmpty(
                                Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        BlockStateProvider.simple(
                                                OpenCreationBlocks.TWIG_BLOCK.get().defaultBlockState()
                                        )
                                )
                        )
                )
        );

    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(OpenCreation.MODID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}