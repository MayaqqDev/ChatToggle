package dev.mayaqq.chattoggle;

import dev.mayaqq.chattoggle.extensions.KeyBindingExtension;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

import java.util.Map;
import java.util.Optional;

public class ChatToggleKeybinds {

    public static final KeyMapping TOGGLE = new KeyMapping(getToggleTranslationKey(), GLFW.GLFW_KEY_Y, getCategoryTranslationKey());

    public static void register() {
        addCategory(getCategoryTranslationKey());
    }

    private static void addCategory(String categoryTranslationKey) {
        Map<String, Integer> map = ((KeyBindingExtension) TOGGLE).chattoggle$getCategoryMap();

        if (map.containsKey(categoryTranslationKey)) {
            return;
        }

        Optional<Integer> largest = map.values().stream().max(Integer::compareTo);
        int largestInt = largest.orElse(0);
        map.put(categoryTranslationKey, largestInt + 1);
    }

    public static String getCategoryTranslationKey() {
        return ChatToggle.translationsWork ? "key.categories.chattoggle" : "Chat Toggle";
    }
    public static String getToggleTranslationKey() {
        return ChatToggle.translationsWork ? "key.chattoggle.toggle" : "Toggle";
    }
}