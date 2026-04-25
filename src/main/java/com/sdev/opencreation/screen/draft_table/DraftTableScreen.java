package com.sdev.opencreation.screen.draft_table;

import com.sdev.opencreation.OpenCreation;
import com.sdev.opencreation.data.BlueprintData;
import com.sdev.opencreation.blueprint.BlueprintRegistry;
import com.sdev.opencreation.network.OCNetwork;
import com.sdev.opencreation.network.blueprint.DraftTablePacket;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class DraftTableScreen extends AbstractContainerScreen<DraftTableMenu> {

    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(
                    OpenCreation.MODID,
                    "textures/gui/draft_table.png"
            );

    public DraftTableScreen(DraftTableMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);

        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    @Override
    protected void init() {
        super.init();

        int centerX = leftPos + imageWidth / 2;
        int y = topPos + 30;
        addRenderableWidget(Button.builder(Component.literal("<"), b -> {
            OCNetwork.sendToServer(new DraftTablePacket(-1));
        }).bounds(centerX - 60, y, 20, 20).build());

        addRenderableWidget(Button.builder(Component.literal(">"), b -> {
            OCNetwork.sendToServer(new DraftTablePacket(1));
        }).bounds(centerX + 40, y, 20, 20).build());

        addRenderableWidget(Button.builder(Component.literal("Создать"), b -> {
            minecraft.gameMode.handleInventoryButtonClick(menu.containerId, 0);
        }).bounds(centerX - 40, topPos + 50, 80, 20).build());
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {

        guiGraphics.blit(
                TEXTURE,
                leftPos,
                topPos,
                0,
                0,
                imageWidth,
                imageHeight
        );
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        super.renderLabels(guiGraphics, mouseX, mouseY);

        BlueprintData data =
                BlueprintRegistry.getTypes().get(menu.getSelectedIndex());

        int centerX = this.imageWidth / 2;

        guiGraphics.drawString(
                font,
                data.russianName(),
                centerX - font.width(data.russianName()) / 2,
                20,
                0x404040,
                false
        );
    }
}