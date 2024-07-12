package com.featherwhisker.halflifemenu.halflifemenu.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Overlay;
import net.minecraft.client.gui.screen.SplashOverlay;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(SplashOverlay.class)
public abstract class SplashOverlayMixin {
    @Final
    @Shadow
    private MinecraftClient client;
    @Shadow
    private long reloadCompleteTime;
    @Inject(
            method="render",
            at=@At("TAIL")
    )
    public void render(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        int width = client.getWindow().getScaledWidth();
        int height = client.getWindow().getScaledHeight();
        float f = this.reloadCompleteTime > -1L ? (float)(Util.getMeasuringTimeMs() - this.reloadCompleteTime) / 1000.0F : -1.0F;
        context.drawTexture(Identifier.of("halflifemenu:textures/blank.png"),0,0,0,0,width,height);
        context.drawTexture(Identifier.of("halflifemenu:textures/openyoureyes.png"),(width/2)-125,(height/2)-(187/2),0,0,250,187,250,187);
        if (f >= 1.0F) {
            this.client.setOverlay((Overlay)null);
        }
    }
}
