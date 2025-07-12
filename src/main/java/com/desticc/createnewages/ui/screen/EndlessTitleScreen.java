package com.desticc.createnewages.ui.screen;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraft.client.gui.screens.options.OptionsScreen;
import net.minecraft.client.gui.screens.worldselection.SelectWorldScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.event.ScreenEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import com.desticc.createnewages.Createendlessnewages;
import net.neoforged.bus.EventBus;

import java.util.function.Supplier;

public class EndlessTitleScreen extends Screen {

    private static final ResourceLocation LOGO =  ResourceLocation.fromNamespaceAndPath("createnewages", "textures/gui/title/minecraft_title.png");
    private static final ResourceLocation RADIAL_GRAD_RIGHT = ResourceLocation.fromNamespaceAndPath("createnewages", "textures/gui/title/gradient_right.png");
    private static final ResourceLocation RADIAL_GRAD_LEFT = ResourceLocation.fromNamespaceAndPath("createnewages", "textures/gui/title/gradient_left.png");
    private static final ResourceLocation RADIAL_GRAD = ResourceLocation.fromNamespaceAndPath("createnewages", "textures/gui/title/gradient.png");


    public EndlessTitleScreen() {
        super(Component.literal("Create: Endless"));
    }

    @Override
    protected void init() {
        int buttonWidth = this.width;
        int buttonHeight = 30;
        int x = (this.width - buttonWidth) / 2; // centered horizontally

// Number of buttons
        int buttonCount = 4;

// Calculate total height of all buttons stacked without gaps
        int totalButtonsHeight = buttonHeight * buttonCount;

// Start Y so that buttons are at the bottom of the screen (bottom aligned)
        int startY = this.height - totalButtonsHeight;

        this.addRenderableWidget(new ColoredButton(x, startY, buttonWidth, buttonHeight,
                Component.literal("Singleplayer"), btn -> {
            this.minecraft.setScreen(new SelectWorldScreen(this));
        }, 0xFFED954C, 0xFFFFFF));

        this.addRenderableWidget(new ColoredButton(x, startY + buttonHeight, buttonWidth, buttonHeight,
                Component.literal("Multiplayer"), btn -> {
            this.minecraft.setScreen(new JoinMultiplayerScreen(this));
        }, 0xFFB36956, 0xFFFFFF));

        this.addRenderableWidget(new ColoredButton(x, startY + buttonHeight * 2, buttonWidth, buttonHeight,
                Component.literal("Options"), btn -> {
            this.minecraft.setScreen(new OptionsScreen(this, Minecraft.getInstance().options));
        }, 0xFF77445A, 0xFFFFFF));
        this.addRenderableWidget(new ColoredButton(x, startY + buttonHeight * 3, buttonWidth, buttonHeight,
                Component.literal("Exit"), btn -> {
            this.minecraft.close();
        }, 0xFF524372, 0xFFFFFF));
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);

        super.render(guiGraphics, mouseX, mouseY, partialTick);

        // Draw RIGHT gradient
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        guiGraphics.setColor(1.0f, 1.0f, 1.0f, 0.9f);
        guiGraphics.blit(RADIAL_GRAD, 0, 0, 0, 0, this.width, this.height, this.width, this.height);

        int x = (this.width - 200) / 2; // center logo
        int y = (this.height / 2) - 104;
        guiGraphics.setColor(1.0f, 1.0f, 1.0f, 1f);
        guiGraphics.blit(LOGO, x, y, 0, 0, 200, 64, 200, 64);



    }
}

