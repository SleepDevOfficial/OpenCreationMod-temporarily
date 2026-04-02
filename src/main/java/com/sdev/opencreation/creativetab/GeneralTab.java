package com.sdev.opencreation.creativetab;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.sdev.opencreation.OpenCreation.MODID;
import static com.sdev.opencreation.item.OpenCreationItems.*;

public class GeneralTab {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> GENERAL_TAB = CREATIVE_MODE_TABS.register("general_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.opencreation"))
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> FRAGMENTS_OF_STONES.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                ITEMS.getEntries().forEach(entry -> output.accept(entry.get()));
            }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
