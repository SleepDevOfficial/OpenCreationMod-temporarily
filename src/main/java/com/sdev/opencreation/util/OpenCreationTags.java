package com.sdev.opencreation.util;

import com.sdev.opencreation.OpenCreation;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class OpenCreationTags {

    public static class Items {

        public static final TagKey<Item>
                PLANK = createTag("plank"),
                SHOVEL_HOE = createTag("shovel_hoe");
        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(OpenCreation.MODID, name));
        }
    }
    public static class Blocks {
        public static final TagKey<Block>
                NEEDS_PRIMITIVE_TOOL = createTag("needs_primitive_tool"),
                INCORRECT_FOR_PRIMITIVE_TOOL = createTag("incorrect_for_primitive_tool"),
                NEEDS_IMPROVED_TOOL = createTag("need_improved_tool"),
                INCORRECT_FOR_IMPROVED_TOOL = createTag("incorrect_improved_tool"),
                NEEDS_KAMINITE_TOOL = createTag("needs_kaminite_tool"),
                INCORRECT_FOR_KAMINITE_TOOL = createTag("incorrect_kaminite_tool"),
                NEEDS_ALLOYING_TOOL = createTag("needs_alloying_tool"),
                INCORRECT_FOR_ALLOYING_TOOL = createTag("incorrect_for_alloying_tool"),
                NEEDS_NETHER_ALLOYING_TOOL = createTag("needs_nether_alloying_tool"),
                INCORRECT_FOR_NETHER_ALLOYING_TOOL = createTag("incorrect_for_nether_alloying_tool"),
                NEEDS_STEEL_TOOL = createTag("needs_steel_tool"),
                INCORRECT_FOR_STEEL_TOOL = createTag("incorrect_for_steel_tool");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(OpenCreation.MODID, name));
        }

    }

}
