package io.github.alexydens.testmod.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.alexydens.testmod.TestMod;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.text.Text;

@Mixin(GameMenuScreen.class)
public class GameMenuScreenMixin extends Screen {

    protected GameMenuScreenMixin(Text title) {
        super(title);
    }

    @Inject(method = "initWidgets", at = @At("TAIL"))
    private void addCustomButton(CallbackInfo ci) {
        // Add your button at a specific position
        this.addDrawableChild(
            ButtonWidget.builder(Text.literal("Test button"), button -> {
                TestMod.LOGGER.info("Test button was pressed.");
            })
                .dimensions(this.width / 2 - 100, this.height / 4 + 120, 200, 20)
                .tooltip(Tooltip.of(Text.literal("This is the tooltip")))
                .build()
        );
    }
}
