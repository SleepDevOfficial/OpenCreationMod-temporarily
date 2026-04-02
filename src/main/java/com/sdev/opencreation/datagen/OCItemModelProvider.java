package com.sdev.opencreation.datagen;

import com.sdev.opencreation.OpenCreation;
import com.sdev.opencreation.item.OpenCreationItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class OCItemModelProvider extends ItemModelProvider {
    public OCItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, OpenCreation.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(OpenCreationItems.FRAGMENTS_OF_STONES.get());
    }
}
