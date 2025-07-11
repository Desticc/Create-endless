//package com.desticc.createnewages.ui.screen;
//
//import net.minecraft.client.gui.GuiGraphics;
//import net.minecraft.client.gui.components.Button;
//import net.minecraft.client.gui.screens.Screen;
//import net.minecraft.client.gui.screens.worldselection.SelectWorldScreen;
//import net.minecraft.client.Minecraft;
//import net.minecraft.network.chat.Component;
//import com.mojang.blaze3d.vertex.PoseStack;
//
//public class EndlessTitleScreen extends Screen {
//
//    public EndlessTitleScreen() {
//        super(Component.literal("Create: Endless"));
//    }
//
//    @Override
//    protected void init() {
//        // Create button using builder
//        Button playButton = Button.builder(Component.literal("Play"), button -> {
//            this.minecraft.setScreen(new SelectWorldScreen(this));
//        }).bounds(this.width / 2 - 100, this.height / 4 + 20, 200, 20).build();
//
//        Button quitButton = Button.builder(Component.literal("Quit"), button -> {
//            this.minecraft.stop();
//        }).bounds(this.width / 2 - 100, this.height / 4 + 60, 200, 20).build();
//
//        this.addRenderableWidget(playButton);
//        this.addRenderableWidget(quitButton);
//    }
//
//    @Override
//    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
//        this.renderBackground(guiGraphics); // renders background
//
//        // Draw centered title text
//        guiGraphics.drawCenteredString(
//                this.font,
//                Component.literal("Create: Endless"),
//                this.width / 2,
//                40,
//                0xFFFFFF
//        );
//        super.render(guiGraphics, mouseX, mouseY, partialTick);
//    }
//
//}
