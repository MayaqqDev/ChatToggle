package dev.mayaqq.chattoggle;

import dev.mayaqq.chattoggle.mixin.KeyBindingMixin;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

import java.util.Map;
import java.util.Optional;

public class ChatToggleKeybinds {
    public static final KeyMapping TOGGLE = new KeyMapping("key.chattoggle.toggle", GLFW.GLFW_KEY_Y, "key.categories.chattoggle");

    public static void register() {
        addCategory("key.categories.chattoggle");
    }

    private static boolean addCategory(String categoryTranslationKey) {
        Map<String, Integer> map = KeyBindingMixin.chattoggle$getCategoryMap();

        if (map.containsKey(categoryTranslationKey)) {
            return false;
        }

        Optional<Integer> largest = map.values().stream().max(Integer::compareTo);
        int largestInt = largest.orElse(0);
        map.put(categoryTranslationKey, largestInt + 1);
        return true;
    }
}