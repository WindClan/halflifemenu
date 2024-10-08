package com.featherwhisker.halflifemenu;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.Date;
import java.util.Random;

public class HalfLifeTitle extends Screen {
    private Date time = new Date();
    private Random logoRandom = new Random(time.getTime()/1000);
    private Identifier logo;
    public HalfLifeTitle() {
        super(Text.of("Title screen"));
        if(logoRandom.nextInt(100) == 0) {
            logo = Identifier.of("halflifemenu","minceraft");
        } else {
            logo = Identifier.of("halflifemenu","minecraft");
        }
    }
    private final static int buttonHeight = 15;
    private final static int buttonWidth = 100;

    @Override
    protected void init() {
        int baseHeight = this.height;

        this.addDrawableChild(ButtonWidget.builder(Text.translatable("menu.singleplayer").formatted(Formatting.YELLOW), button -> this.client.setScreen(new SelectWorldScreen(this)))
                .dimensions(4,  baseHeight - (buttonHeight+2)*6, buttonWidth, buttonHeight)
                .build());
        this.addDrawableChild(ButtonWidget.builder(Text.translatable("menu.multiplayer").formatted(Formatting.YELLOW), button -> this.client.setScreen(new MultiplayerScreen(this)))
                .dimensions(4,  baseHeight - (buttonHeight+2)*5, buttonWidth, buttonHeight)
                .build());
        this.addDrawableChild(ButtonWidget.builder(Text.translatable("menu.options").formatted(Formatting.YELLOW), button -> this.client.setScreen(new OptionsScreen(this, this.client.options)))
                .dimensions(4,  baseHeight - (buttonHeight+2)*4, buttonWidth, buttonHeight)
                .build());
        this.addDrawableChild(ButtonWidget.builder(Text.translatable("menu.quit").formatted(Formatting.YELLOW), button -> this.client.scheduleStop())
                .dimensions(4,  baseHeight - (buttonHeight+2)*3, buttonWidth, buttonHeight)
                .build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context,mouseX,mouseY,delta);
        this.renderPanoramaBackground(context,delta);
        int baseHeight = this.height;
        RenderSystem.enableBlend();
        context.drawGuiTexture(logo, (int)(buttonHeight*1.5),(int)(baseHeight-(buttonHeight+2)*1.5),buttonHeight*4,buttonHeight);
        RenderSystem.disableBlend();
    }
}
