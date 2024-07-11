package com.featherwhisker.halflifemenu.halflifemenu.mixin;

import com.featherwhisker.halflifemenu.halflifemenu.HalfLifeTitle;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {

    private MinecraftClient client = MinecraftClient.getInstance();
    /**
     * @author Featherwhisker
     * @reason I'm replacing the entire menu
     */
    @Overwrite
    public void init() {
        if(!this.client.isDemo()) {
            this.client.setScreen(new HalfLifeTitle());
        }
    }

    /**
     * @author Featherwhisker
     * @reason It stops the logo & background from rendering
     */
    @Overwrite
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {}
}
