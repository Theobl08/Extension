package net.theobl.extension.inventory;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.theobl.extension.Extension;

public class FletchingScreen extends AbstractContainerScreen<FletchingMenu> {
    private static final Identifier FLETCHING_TABLE_LOCATION = Extension.asResource("textures/gui/container/fletching_table.png");

    public FletchingScreen(FletchingMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.titleLabelX = 33;
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor guiGraphics, int x, int y, float partialTick) {
        super.extractRenderState(guiGraphics, x, y, partialTick);
        this.extractTooltip(guiGraphics, x, y);
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.extractBackground(guiGraphics, mouseX, mouseY, partialTick);
        int i = this.leftPos;
        int j = (this.height - this.imageHeight) / 2;
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, FLETCHING_TABLE_LOCATION, i, j, 0, 0, this.imageWidth, this.imageHeight, 256, 256);
    }
}
