package com.sdev.opencreation.events;

import com.sdev.opencreation.OpenCreation;
import com.sdev.opencreation.screen.OpenCreationMenus;
import com.sdev.opencreation.screen.testblock.TestBlockScreen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

public class ClientEvents {

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(OpenCreationMenus.TEST_BLOCK_MENU.get(), TestBlockScreen::new);
    }

}