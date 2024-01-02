package dev.mayaqq.chattoggle.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import dev.mayaqq.chattoggle.ChatToggleConfig;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

import java.io.IOException;

public class ChatCommand {

    public static int chat(CommandContext<CommandSourceStack> context) {
        Player player = context.getSource().getPlayer();
        player.displayClientMessage(Component.literal("§aTo toggle chat do /chat toggle"), false);
        return 1;
    }

    public static int toggle(CommandContext<CommandSourceStack> context) {
        Player player = context.getSource().getPlayer();
        if (ChatToggleConfig.CONFIG.on) {
            ChatToggleConfig.CONFIG.on = false;
            save();
            player.displayClientMessage(Component.literal("§cChat Toggle is now off"), false);
        } else {
            ChatToggleConfig.CONFIG.on = true;
            save();
            player.displayClientMessage(Component.literal("§aChat Toggle is now on"), false);
        }
        return 1;
    }

    public static int message(CommandContext<CommandSourceStack> context) {
        Player player = context.getSource().getPlayer();
        String message = StringArgumentType.getString(context, "message");
        ChatToggleConfig.CONFIG.message = message;
        save();
        player.displayClientMessage(Component.literal("§aMessage set to: " + message), false);
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
