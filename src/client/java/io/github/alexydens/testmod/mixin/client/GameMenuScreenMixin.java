package io.github.alexydens.testmod.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.alexydens.testmod.TestMod;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.text.Text;

@Mixin(GameMenuScreen.class)
public class GameMenuScreenMixin extends Screen {

    protected GameMenuScreenMixin(Text title) {
        super(title);
    }

    @Inject(method = "initWidgets", at = @At("TAIL"))
    private void addCustomButton(CallbackInfo ci) {
        this.addDrawableChild(
            ButtonWidget.builder(Text.literal("Button0"), button -> {
                TestMod.LOGGER.info("Button0 pressed.");
            })
                .dimensions(0, 0, 100, 20)
                .tooltip(Tooltip.of(Text.literal("Button0")))
                .build()
        );
        this.addDrawableChild(
            ButtonWidget.builder(Text.literal("Button1"), button -> {
                TestMod.LOGGER.info("Button1 pressed.");
            })
                .dimensions(0, 20, 100, 20)
                .tooltip(Tooltip.of(Text.literal("Button1")))
                .build()
        );
        this.addDrawableChild(new SliderWidget(0, 40, 100, 20, Text.literal("Slider"), 0.5) {
            @Override
            protected void updateMessage() {
                this.setMessage(Text.literal("value: " + (int) (this.value * 100) + "%"));
            }

            @Override
            protected void applyValue() {
                System.out.println("Value: " + this.value);
            }
        });
    }
}
