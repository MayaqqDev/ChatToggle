package dev.mayaqq.chattoggle;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;


public class ChatToggleKeybinds {
    static KeyBinding toggle;

    public static void register() {
        final String category= "key.categories.chattoggle";
        toggle = new KeyBinding("key.chattoggle.toggle", GLFW.GLFW_KEY_Y, category);
        KeyBindingHelper.registerKeyBinding(toggle);
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (toggle.wasPressed()) {
                ChatToggle.toggle();
            }
        });
    }
}