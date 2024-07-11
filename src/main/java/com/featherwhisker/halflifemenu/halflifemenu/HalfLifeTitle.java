package com.featherwhisker.halflifemenu.halflifemenu;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class HalfLifeTitle extends Screen {
    public HalfLifeTitle() {
        super(Text.of("Title screen"));
    }
    public static final Identifier logo = Identifier.ofVanilla("textures/gui/title/minecraft.png");
    @Override
    protected void init() {
        int baseHeight = (this.height-(this.height / 8));
        int buttonHeight = 15;
        int buttonWidth = 100;
        this.addDrawableChild(
                ButtonWidget.builder(Text.translatable("menu.singleplayer"), button -> this.client.setScreen(new SelectWorldScreen(this)))
                        .dimensions(4,  baseHeight - (buttonHeight+2)*4, buttonWidth, buttonHeight)
                        .build()
        );
        this.addDrawableChild(
                ButtonWidget.builder(Text.translatable("menu.multiplayer"), button -> this.client.setScreen(new MultiplayerScreen(this)))
                        .dimensions(4,  baseHeight - (buttonHeight+2)*3, buttonWidth, buttonHeight)
                        .build()
        );
        this.addDrawableChild(
                ButtonWidget.builder(Text.translatable("menu.options"), button -> this.client.setScreen(new OptionsScreen(this, this.client.options)))
                        .dimensions(4,  baseHeight - (buttonHeight+2)*2, buttonWidth, buttonHeight)
                        .build()
        );
        this.addDrawableChild(
                ButtonWidget.builder(Text.translatable("menu.quit"), button -> this.client.scheduleStop())
                        .dimensions(4,  baseHeight - (buttonHeight+2)*1, buttonWidth, buttonHeight)
                        .build()
        );
        MinecraftClient.getInstance().getTextureManager().bindTexture(logo);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        int baseHeight = (this.height-(this.height / 8));
        int buttonHeight = 15;
        RenderSystem.enableBlend();
        context.drawGuiTexture(logo,4,baseHeight,buttonHeight*4,buttonHeight);
        RenderSystem.disableBlend();
    }
}
