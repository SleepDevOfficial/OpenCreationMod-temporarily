package com.sdev.opencreation.util;

import com.sdev.opencreation.item.OpenCreationItems;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

import java.util.Map;

public class OpenCreationTiers {
    public static final Tier
            PRIMITIVE = new SimpleTier(OpenCreationTags.Blocks.INCORRECT_FOR_PRIMITIVE_TOOL,
                    32, 1f, 0f,3, () -> Ingredient.of(OpenCreationItems.FRAGMENTS_OF_STONES)),
            IMPROVED = new SimpleTier(OpenCreationTags.Blocks.INCORRECT_FOR_IMPROVED_TOOL,
                    64, 2f, 1f, 4, () -> Ingredient.of(OpenCreationTags.Items.PLANK)),
            KAMINITE = new SimpleTier(OpenCreationTags.Blocks.INCORRECT_FOR_KAMINITE_TOOL,
                    128, 3f, 2f, 4, () -> Ingredient.of(OpenCreationItems.KAMINITE)),
            ALLOYING = new SimpleTier(OpenCreationTags.Blocks.INCORRECT_FOR_ALLOYING_TOOL,
                    256, 4f, 3f, 8, () -> Ingredient.of(OpenCreationItems.GRANITE_ALLOY)),
            NETHER_ALLOYING = new SimpleTier(OpenCreationTags.Blocks.INCORRECT_FOR_NETHER_ALLOYING_TOOL,
                    512, 5f, 4f, 14, () -> Ingredient.of(OpenCreationItems.BASALT_ALLOY)),
            STEEL = new SimpleTier(OpenCreationTags.Blocks.INCORRECT_FOR_STEEL_TOOL,
                    1024, 6f, 5f, 18, () -> Ingredient.of(ItemStack.EMPTY));

    public static final Map<Tier, Integer> NUM_TIER = Map.of(
            PRIMITIVE, 0,
            IMPROVED, 1,
            KAMINITE, 2,
            ALLOYING, 3,
            NETHER_ALLOYING, 4,
            STEEL, 5
    );

    public static int getNumTier(Tier tier) {
        return NUM_TIER.getOrDefault(tier, 0);
    }
}
