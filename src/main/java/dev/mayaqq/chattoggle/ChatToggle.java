package dev.mayaqq.chattoggle;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;

public class ChatToggle implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ChatToggleCommands.register();
        ChatToggleConfig.register();
        ChatToggleKeybinds.register();
    }

    public static void toggle() {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player != null) {
            if (ChatToggleConfig.CONFIG.on) {
                ChatToggleConfig.CONFIG.on = false;
                player.sendMessage(Text.of("§cChat Toggle is now off"), false);
            } else {
                ChatToggleConfig.CONFIG.on = true;
                player.sendMessage(Text.of("§aChat Toggle is now on"), false);
            }
            ChatToggleConfig.save();
        }
    }
}
