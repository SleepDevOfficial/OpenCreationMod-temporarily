package com.sdev.opencreation.screen;

import com.sdev.opencreation.OpenCreation;
import com.sdev.opencreation.screen.testblock.TestBlockMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class OpenCreationMenus {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, OpenCreation.MODID);
    public static final Supplier<MenuType<TestBlockMenu>> TEST_BLOCK_MENU =
            MENUS.register("test_block_menu",
                    () -> new MenuType<>(TestBlockMenu::new, FeatureFlags.DEFAULT_FLAGS));
}
