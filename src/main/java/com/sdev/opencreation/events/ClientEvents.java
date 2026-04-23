package com.sdev.opencreation.events;

import com.sdev.opencreation.OpenCreation;
import com.sdev.opencreation.screen.OpenCreationMenus;
import com.sdev.opencreation.screen.draft_table.DraftTableScreen;
import com.sdev.opencreation.screen.recipe_table.RecipeTableScreen;
import com.sdev.opencreation.screen.testblock.TestBlockScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

public class ClientEvents {

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(OpenCreationMenus.TEST_BLOCK_MENU.get(), TestBlockScreen::new);
        event.register(OpenCreationMenus.DRAFT_TABLE_MENU.get(), DraftTableScreen::new);
        event.register(OpenCreationMenus.RECIPE_TABLE_MENU.get(), RecipeTableScreen::new);
    }

}