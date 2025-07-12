package com.desticc.createnewages.mixin;

import com.desticc.createnewages.ui.screen.ColoredButton;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.LogoRenderer;
import net.minecraft.client.gui.components.SplashRenderer;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraft.client.gui.screens.options.OptionsScreen;
import net.minecraft.client.gui.screens.worldselection.SelectWorldScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.network.chat.Component;

import javax.annotation.Nullable;

@Mixin(TitleScreen.class)
public abstract class TitleScreenMixin extends Screen {

    @Shadow @Nullable private SplashRenderer splash;
    private static final ResourceLocation RADIAL_GRAD = ResourceLocation.fromNamespaceAndPath("createnewages", "textures/gui/title/gradient.png");
    private static final ResourceLocation BEAM = ResourceLocation.fromNamespaceAndPath("createnewages", "textures/gui/title/frame.png");

    protected TitleScreenMixin(Component title) {
        super(title);
    }

    // Inject into init to modify or replace buttons
    @Inject(method = "init", at = @At("TAIL"))
    private void createnewages$injectCustomButtons(CallbackInfo ci) {
        int buttonWidth = this.width;
        int buttonHeight = 30;
        int x = (this.width - buttonWidth) / 2;
        int buttonCount = 4;
        int totalButtonsHeight = buttonHeight * buttonCount;
        int startY = this.height - totalButtonsHeight;

        this.clearWidgets(); // remove vanilla buttons
        this.splash = null;

        this.addRenderableWidget(new ColoredButton(x, startY, buttonWidth, buttonHeight,
                Component.literal("Singleplayer"), btn -> this.minecraft.setScreen(new SelectWorldScreen((TitleScreen)(Object)this)),
                0xFFED954C, 0xFFFFFF));

        this.addRenderableWidget(new ColoredButton(x, startY + buttonHeight, buttonWidth, buttonHeight,
                Component.literal("Multiplayer"), btn -> this.minecraft.setScreen(new JoinMultiplayerScreen((TitleScreen)(Object)this)),
                0xFFB36956, 0xFFFFFF));

        this.addRenderableWidget(new ColoredButton(x, startY + buttonHeight * 2, buttonWidth, buttonHeight,
                Component.literal("Options"), btn -> this.minecraft.setScreen(new OptionsScreen((TitleScreen)(Object)this, this.minecraft.options)),
                0xFF77445A, 0xFFFFFF));

        this.addRenderableWidget(new ColoredButton(x, startY + buttonHeight * 3, buttonWidth, buttonHeight,
                Component.literal("Exit"), btn -> this.minecraft.close(),
                0xFF524372, 0xFFFFFF));
    }

    // Inject into render to draw gradients and logo
    @Inject(method = "render", at = @At("TAIL"))
    private void createnewages$renderLogo(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        guiGraphics.setColor(1.0f, 1.0f, 1.0f, 0.9f);
        guiGraphics.blit(RADIAL_GRAD, 0, 0, 0, 0, this.width, this.height, this.width, this.height);
        guiGraphics.setColor(1.0f, 1.0f, 1.0f, 1.0f);
       // guiGraphics.blit(BEAM, 0, 0, 0, 0, this.width, this.height, this.width, this.height);
    }
}

