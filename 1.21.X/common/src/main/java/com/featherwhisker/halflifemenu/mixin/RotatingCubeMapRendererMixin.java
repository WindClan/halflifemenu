package com.featherwhisker.halflifemenu.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.CubeMapRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.RotatingCubeMapRenderer;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RotatingCubeMapRenderer.class)
public class RotatingCubeMapRendererMixin {
    @Unique
    private static Identifier bkg = Identifier.of("halflifemenu","bkg");
    /**
     * @author Featherwhisker
     * @reason Basically I need to be able to replace all backgrounds
     */
    @Overwrite
    public void render(DrawContext context, int width, int height, float alpha, float tickDelta) {
        RenderSystem.enableBlend();
        context.drawGuiTexture(bkg,0,0,width,height);
        RenderSystem.disableBlend();
    }
    @Inject(at = @At("TAIL"), method = "<init>")
    public void init(CubeMapRenderer cubeMap, CallbackInfo ci) {
        //MinecraftClient.getInstance().getTextureManager().bindTexture(bkg);
    }

}
