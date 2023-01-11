package dev.mayaqq.chattoggle;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

import java.io.IOException;

public class Keybinds {
    static KeyBinding toggle;

    public static void registerKeybind() {
        final String category="key.categories.chattoggle";
        toggle = new KeyBinding("key.chattoggle.toggle", GLFW.GLFW_KEY_Y, category);
        KeyBindingHelper.registerKeyBinding(toggle);
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (toggle.wasPressed()) {
                keyPressed(client);
            }
        });
    }

    public static void keyPressed(MinecraftClient client) {
        ConfigRegistry.load();
        if (ConfigRegistry.CONFIG.on) {
            ConfigRegistry.CONFIG.on = false;
            client.player.sendMessage(Text.of("§cChat Toggle is now off"), false);
        } else {
            ConfigRegistry.CONFIG.on = true;
            client.player.sendMessage(Text.of("§aChat Toggle is now on"), false);
        }
        try {
            ConfigRegistry.saveConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}