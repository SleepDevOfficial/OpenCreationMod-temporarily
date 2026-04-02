package com.sdev.opencreation.screen.testblock;

import com.sdev.opencreation.OpenCreation;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.network.chat.Component;


public class TestBlockScreen extends AbstractContainerScreen<TestBlockMenu> {
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(OpenCreation.MODID, "textures/gui/test_block_gui.png");
    public TestBlockScreen(TestBlockMenu menu, Inventory inv, Component title) {
        super(menu, inv, title);
        this.imageHeight = 166;
        this.imageWidth = 176;
    }
    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);
    }
}

