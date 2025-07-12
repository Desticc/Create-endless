package com.desticc.createnewages.mixin;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.LogoRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LogoRenderer.class)
public class LogoRendererMixin {
    @Inject(
            method = "renderLogo(Lnet/minecraft/client/gui/GuiGraphics;IFI)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void replaceLogo(GuiGraphics guiGraphics, int screenWidth, float transparency, int height, CallbackInfo ci) {
        // Your custom logo logic
        ResourceLocation MY_LOGO = ResourceLocation.fromNamespaceAndPath("createnewages", "textures/gui/title/minecraft_title.png");
        int x = screenWidth / 2 - 128;
        guiGraphics.blit(MY_LOGO, x, height, 0.0F, 0.0F, 256, 44, 256, 64);

        // Cancel vanilla logo
        ci.cancel();
    }


}
