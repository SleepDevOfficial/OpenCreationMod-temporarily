package com.sdev.opencreation.datagen;

import com.sdev.opencreation.OpenCreation;
import com.sdev.opencreation.item.OpenCreationItems;
import com.sdev.opencreation.util.OpenCreationTags;
import com.sdev.opencreation.util.OpenCreationTiers;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.event.entity.player.PlayerContainerEvent;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class OCItemTagProvider extends ItemTagsProvider {
    public OCItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, OpenCreation.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(OpenCreationTags.Items.PLANK)
                .add(OpenCreationItems.OAK_PLANK.get())
                .add(OpenCreationItems.DARK_OAK_PLANK.get())
                .add(OpenCreationItems.ACACIA_PLANK.get())
                .add(OpenCreationItems.CHERRY_PLANK.get())
                .add(OpenCreationItems.BIRCH_PLANK.get())
                .add(OpenCreationItems.JUNGLE_PLANK.get())
                .add(OpenCreationItems.SPRUCE_PLANK.get())
                .add(OpenCreationItems.MANGROVE_PLANK.get())
                .add(OpenCreationItems.BAMBOO_PLANK.get())
                .add(OpenCreationItems.CRIMSON_PLANK.get())
                .add(OpenCreationItems.WARPED_PLANK.get());
        tag(ItemTags.AXES)
                .add(OpenCreationItems.PRIMITIVE_AXE.get())
                .add(OpenCreationItems.IMPROVED_AXE.get())
                .add(OpenCreationItems.KAMINITE_AXE.get())
                .add(OpenCreationItems.ANDESITE_AXE.get())
                .add(OpenCreationItems.BASALT_AXE.get())
                .add(OpenCreationItems.WROUGHT_IRON_AXE.get());
        tag(ItemTags.PICKAXES)
                .add(OpenCreationItems.PRIMITIVE_PICKAXE.get())
                .add(OpenCreationItems.KAMINITE_PICKAXE.get())
                .add(OpenCreationItems.DIORITE_PICKAXE.get())
                .add(OpenCreationItems.BLACKSTONE_PICKAXE.get())
                .add(OpenCreationItems.STEEL_PICKAXE.get());
        tag(ItemTags.SWORDS)
                .add(OpenCreationItems.PRIMITIVE_SWORD.get())
                .add(OpenCreationItems.KAMINITE_SWORD.get())
                .add(OpenCreationItems.GRANITE_SWORD.get())
                .add(OpenCreationItems.BASALT_SWORD.get())
                .add(OpenCreationItems.WROUGHT_IRON_SWORD.get());
        tag(ItemTags.SHOVELS)
                .add(OpenCreationItems.PRIMITIVE_SHOVEL.get())
                .add(OpenCreationItems.KAMINITE_SHOVEL.get());
        tag(ItemTags.HOES)
                .add(OpenCreationItems.KAMINITE_HOE.get());
        tag(OpenCreationTags.Items.SHOVEL_HOE)
                .add(OpenCreationItems.DEEPSLATE_SHOVEL_HOE.get())
                .add(OpenCreationItems.BLACKSTONE_SHOVEL_HOE.get())
                .add(OpenCreationItems.STEEL_SHOVEL_HOE.get());
    }
}

