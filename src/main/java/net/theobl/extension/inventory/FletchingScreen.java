package net.theobl.extension.inventory;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.theobl.extension.Extension;

public class FletchingScreen extends AbstractContainerScreen<FletchingMenu> {
    private static final ResourceLocation FLETCHING_TABLE_LOCATION = ResourceLocation.fromNamespaceAndPath(Extension.MODID, "textures/gui/container/fletching_table.png");

    public FletchingScreen(FletchingMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.titleLabelX = 33;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int x, int y, float partialTick) {
        super.render(guiGraphics, x, y, partialTick);
        this.renderTooltip(guiGraphics, x, y);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        int i = this.leftPos;
        int j = (this.height - this.imageHeight) / 2;
        guiGraphics.blit(RenderType::guiTextured, FLETCHING_TABLE_LOCATION, i, j, 0, 0, this.imageWidth, this.imageHeight, 256, 256);
    }
}
