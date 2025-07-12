package com.desticc.createnewages.ui.screen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

import java.util.function.Supplier;

public class ColoredButton extends Button {
    private final int backgroundColor;
    private final int textColor;

    public ColoredButton(int x, int y, int width, int height, Component text, OnPress onPress, int backgroundColor, int textColor) {
        super(x, y, width, height, text, onPress, Supplier::get);
        this.backgroundColor = backgroundColor;
        this.textColor = textColor;
    }

    @Override
    public void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        int x = this.getX();
        int y = this.getY();
        int width = this.getWidth();
        int height = this.getHeight();

        graphics.fill(x, y, x + width, y + height, backgroundColor);

        if (this.isHoveredOrFocused()) {
            graphics.fill(x, y, x + width, y + height, backgroundColor | 0xAA000000);
        }

        int textWidth = Minecraft.getInstance().font.width(this.getMessage());
        int textX = x + (width - textWidth) / 2;
        int textY = y + (height - 8) / 2;

        graphics.drawString(Minecraft.getInstance().font, this.getMessage(), textX, textY, textColor, false);
    }

}
