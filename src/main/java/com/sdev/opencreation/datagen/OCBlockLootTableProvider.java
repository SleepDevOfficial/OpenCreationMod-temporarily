package com.sdev.opencreation.datagen;

import com.sdev.opencreation.block.OpenCreationBlocks;
import com.sdev.opencreation.item.OpenCreationItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Set;

public class OCBlockLootTableProvider extends BlockLootSubProvider {
    protected OCBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }


    @Override
    protected void generate() {
        dropSelf(OpenCreationBlocks.TEST_BLOCK.get());
        dropSelf(OpenCreationBlocks.KAMINITE_BARREL.get());
        dropSelf(OpenCreationBlocks.KAMINITE_FURNACE_CONTROLLER.get());
        dropSelf(OpenCreationBlocks.BARK_BLOCK.get());
        dropSelf(OpenCreationBlocks.GRANITE_FRAME.get());
        dropSelf(OpenCreationBlocks.DIORITE_FRAME.get());
        dropSelf(OpenCreationBlocks.DEEPSLATE_FRAME.get());
        dropSelf(OpenCreationBlocks.BASALT_FRAME.get());
        dropSelf(OpenCreationBlocks.BLACKSTONE_FRAME.get());
        dropSelf(OpenCreationBlocks.DRAFT_TABLE_PRIMITIVE.get());
        dropSelf(OpenCreationBlocks.RECIPE_TABLE.get());
        dropSelf(OpenCreationBlocks.STRIPPED_HEVEA_LOG.get());
        dropSelf(OpenCreationBlocks.HEVEA_WOOD.get());
        dropSelf(OpenCreationBlocks.STRIPPED_HEVEA_WOOD.get());
        dropSelf(OpenCreationBlocks.NOTCH_HEVEA_LOG.get());
        dropSelf(OpenCreationBlocks.HEVEA_SAPLING.get());

        add(OpenCreationBlocks.HEVEA_LEAVES.get(), block ->
                createLeavesDrops(block, OpenCreationBlocks.HEVEA_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        add(OpenCreationBlocks.HEVEA_LOG.get(), block ->
                createSingleItemTable(OpenCreationBlocks.NOTCH_HEVEA_LOG));
        add(OpenCreationBlocks.TWIG_BLOCK.get(), block ->
                createSingleItemTable(OpenCreationItems.TWIG.get()));
        add(OpenCreationBlocks.PEBBLE_BLOCK.get(), block ->
                createSingleItemTable(OpenCreationItems.FRAGMENTS_OF_STONES.get()));
    }



    protected LootTable.Builder createMultipleOreDrops(Block pBlock, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock, LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                        .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))));
    }


    @Override
    protected Iterable<Block> getKnownBlocks() {
        return OpenCreationBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
