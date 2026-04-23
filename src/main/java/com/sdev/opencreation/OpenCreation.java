package com.sdev.opencreation;

import com.sdev.opencreation.command.OCChunkCommand;
import com.sdev.opencreation.command.OCDataCommand;
import com.sdev.opencreation.events.*;
import com.sdev.opencreation.multiblock.OCMultiblockPatterns;
import com.sdev.opencreation.network.OCNetwork;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;

import static com.sdev.opencreation.block.OpenCreationBlocks.BLOCKS;
import static com.sdev.opencreation.block.OCBlockEntities.BLOCK_ENTITIES;
import static com.sdev.opencreation.creativetab.GeneralTab.CREATIVE_MODE_TABS;
import static com.sdev.opencreation.data.OCDataComponents.DATA_COMPONENTS;
import static com.sdev.opencreation.item.OpenCreationItems.ITEMS;
import static com.sdev.opencreation.screen.OpenCreationMenus.MENUS;


@Mod(OpenCreation.MODID)
public class OpenCreation {
    public static final String MODID = "opencreation";
    public static final Logger LOGGER = LogUtils.getLogger();

    public OpenCreation(IEventBus modEventBus, ModContainer modContainer) {

        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
        NeoForge.EVENT_BUS.addListener(AnvilRepairHandler::onAnvilUpdate);
        NeoForge.EVENT_BUS.register(BreakVBlocks.class);
        NeoForge.EVENT_BUS.register(FlintNotDrop.class);
        NeoForge.EVENT_BUS.register(CampfirePutOut.class);
        BLOCKS.register(modEventBus);
        BLOCK_ENTITIES.register(modEventBus);
        MENUS.register(modEventBus);
        DATA_COMPONENTS.register(modEventBus);
        NeoForge.EVENT_BUS.addListener(this::onRegisterCommands);
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(ClientEvents::registerScreens);
        modEventBus.addListener(OCNetwork::register);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void onRegisterCommands(RegisterCommandsEvent event) {
        OCChunkCommand.register(event.getDispatcher(), event.getBuildContext());
        OCDataCommand.register(event.getDispatcher());
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(OCMultiblockPatterns::register);
    }
}
