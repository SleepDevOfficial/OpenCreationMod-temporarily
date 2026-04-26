package com.sdev.opencreation.worldgen;

import com.sdev.opencreation.OpenCreation;
import com.sdev.opencreation.block.OpenCreationBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class OCPlacedFeatures {
    public static final ResourceKey<PlacedFeature> PEBBLE_PLACED_KEY = registerKey("pebble_placed");
    public static final ResourceKey<PlacedFeature> TWIG_PLACED_KEY = registerKey("twig_placed");
    public static final ResourceKey<PlacedFeature> HEVEA_PLACED_KEY = registerKey("hevea_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context,
                PEBBLE_PLACED_KEY,
                configuredFeatures.getOrThrow(OCConfiguredFeatures.PEBBLE_KEY),
                List.of(
                        CountPlacement.of(2),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BlockPredicateFilter.forPredicate(
                                BlockPredicate.anyOf(
                                        BlockPredicate.matchesBlocks(
                                                new BlockPos(0, -1, 0),
                                                Blocks.GRAVEL,
                                                Blocks.SAND,
                                                Blocks.RED_SAND
                                        ),
                                        BlockPredicate.matchesTag(
                                                new BlockPos(0, -1, 0),
                                                BlockTags.DIRT

                                        ),
                                        BlockPredicate.matchesTag(
                                                new BlockPos(0, -1, 0),
                                                BlockTags.TERRACOTTA
                                                )
                                        )
                                )
                        )
                );

        register(context,
                TWIG_PLACED_KEY,
                configuredFeatures.getOrThrow(OCConfiguredFeatures.TWIG_KEY),
                List.of(
                        CountPlacement.of(2),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BlockPredicateFilter.forPredicate(
                                BlockPredicate.anyOf(
                                        BlockPredicate.matchesTag(
                                                new BlockPos(0, -1, 0),
                                                BlockTags.DIRT

                                        )
                                )
                        )
                )
        );

        register(context, HEVEA_PLACED_KEY, configuredFeatures.getOrThrow(OCConfiguredFeatures.HEVEA_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.25f, 1),
                        OpenCreationBlocks.HEVEA_SAPLING.get()));

    }


    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(OpenCreation.MODID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}