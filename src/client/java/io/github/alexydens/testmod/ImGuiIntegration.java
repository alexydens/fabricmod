package io.github.alexydens.testmod;

import imgui.ImGui;
import imgui.ImGuiIO;
import imgui.gl3.ImGuiImplGl3;
import net.minecraft.client.MinecraftClient;

public class ImGuiIntegration {
    private static final ImGuiImplGl3 impl = new ImGuiImplGl3();

    public static void init() {
        ImGui.createContext();
        ImGui.styleColorsDark();
        impl.init("#version 150");
    }

    public static void render() {
        // Update display size
        updateDisplaySize();

        impl.newFrame();
        ImGui.newFrame();

        // Example ImGui window
        ImGui.begin("Example Window");
        ImGui.text("Hello, Minecraft!");
        ImGui.end();

        ImGui.render();
        impl.renderDrawData(ImGui.getDrawData());
    }

    public static void updateDisplaySize() {
        ImGuiIO io = ImGui.getIO();
        MinecraftClient client = MinecraftClient.getInstance();

        if (client != null && client.getWindow() != null) {
            int width = client.getWindow().getFramebufferWidth();
            int height = client.getWindow().getFramebufferHeight();

            if (width > 0 && height > 0) {
                io.setDisplaySize(width, height);
            } else {
                System.err.println("Invalid display size: width=" + width + ", height=" + height);
            }
        }
    }

    public static void shutdown() {
        ImGui.destroyContext();
    }
}
