package dev.mayaqq.chattoggle.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import dev.mayaqq.chattoggle.ChatToggle;
import dev.mayaqq.chattoggle.ChatToggleConfig;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class ChatCommand {
    public static int chat(CommandContext<FabricClientCommandSource> context) {
        PlayerEntity player = context.getSource().getPlayer();
        player.sendMessage(Text.of("§aTo toggle chat do /chat toggle"), false);
        return 1;
    }

    public static int toggle(CommandContext<FabricClientCommandSource> context) {
        ChatToggle.toggle();
        return 1;
    }
    public static int message(CommandContext<FabricClientCommandSource> context) {
        PlayerEntity player = context.getSource().getPlayer();
        String message = StringArgumentType.getString(context, "message");
        ChatToggleConfig.CONFIG.message = message;
        ChatToggleConfig.save();
        player.sendMessage(Text.of("§aMessage set to: " + message), false);
        return 1;
    }
}
