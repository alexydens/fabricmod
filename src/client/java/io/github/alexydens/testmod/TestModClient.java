package io.github.alexydens.testmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;

public class TestModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		
		WorldRenderEvents.BEFORE_ENTITIES.register(
			context -> {
				ImGuiIntegration.init();
				ImGuiIntegration.render();
			}
		);
	}
}