package dev.mayaqq.chattoggle.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import dev.mayaqq.chattoggle.ChatToggleConfig;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.player.Player;

import java.io.IOException;

public class ChatCommand {

    public static int chat(CommandContext<CommandSourceStack> context) {
        try {
            Player player = context.getSource().getPlayerOrException();
            player.displayClientMessage(new TextComponent("§aTo toggle chat do /chat toggle"), false);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int toggle(CommandContext<CommandSourceStack> context) {
        try  {
            Player player = context.getSource().getPlayerOrException();
            if (ChatToggleConfig.CONFIG.on) {
                ChatToggleConfig.CONFIG.on = false;
                save();
                player.displayClientMessage(new TextComponent("§cChat Toggle is now off"), false);
            } else {
                ChatToggleConfig.CONFIG.on = true;
                save();
                player.displayClientMessage(new TextComponent("§aChat Toggle is now on"), false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    public static int message(CommandContext<CommandSourceStack> context) {
        try {
            Player player = context.getSource().getPlayerOrException();
            String message = StringArgumentType.getString(context, "message");
            ChatToggleConfig.CONFIG.message = message;
            save();
            player.displayClientMessage(new TextComponent("§aMessage set to: " + message), false);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
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
