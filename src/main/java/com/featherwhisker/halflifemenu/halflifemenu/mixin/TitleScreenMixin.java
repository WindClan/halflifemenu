package com.featherwhisker.halflifemenu.halflifemenu.mixin;

import com.featherwhisker.halflifemenu.halflifemenu.HalfLifeTitle;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {

    private MinecraftClient client = MinecraftClient.getInstance();
    /**
     * @author Featherwhisker
     * @reason I'm replacing the entire menu
     */
    @Inject(at = @At("HEAD"), method = "init",cancellable = true)
    public void init(CallbackInfo ci) {
        if(!this.client.isDemo()) {
            this.client.setScreen(new HalfLifeTitle());
        }
        ci.cancel();
    }

    /**
     * @author Featherwhisker
     * @reason It stops the logo & background from rendering
     */
    @Inject(at = @At("HEAD"), method = "render",cancellable = true)
    public void render(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        ci.cancel();
    }
}
