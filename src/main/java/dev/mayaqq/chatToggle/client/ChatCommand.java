package dev.mayaqq.chatToggle.client;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import dev.mayaqq.chatToggle.ChatToggleConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;

import java.io.IOException;

public class ChatCommand {
    public static <T> int chat(CommandContext<T> context) {
        Player player = Minecraft.getInstance().player;
        ChatToggleCommon.sendChatMessage("§aTo toggle chat do /chat toggle", player);
        return 1;
    }

    public static <T> int toggle(CommandContext<T> context) {
        Player player = Minecraft.getInstance().player;
        if (ChatToggleConfig.CONFIG.on) {
            ChatToggleConfig.CONFIG.on = false;
            save();
            ChatToggleCommon.sendChatMessage("§cChat Toggle is now off", player);
        } else {
            ChatToggleConfig.CONFIG.on = true;
            save();
            ChatToggleCommon.sendChatMessage("§aChat Toggle is now on", player);
        }
        return 1;
    }

    public static <T> int message(CommandContext<T> context) {
        Player player = Minecraft.getInstance().player;
        String message = StringArgumentType.getString(context, "message");
        ChatToggleConfig.CONFIG.message = message;
        save();
        ChatToggleCommon.sendChatMessage("§aMessage set to: " + message, player);
        return 1;
    }

    public static void save() {
        try {
            ChatToggleConfig.save();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}