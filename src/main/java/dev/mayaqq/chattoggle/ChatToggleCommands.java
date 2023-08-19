package dev.mayaqq.chattoggle;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.tree.ArgumentCommandNode;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.mojang.brigadier.tree.RootCommandNode;
import dev.mayaqq.chattoggle.commands.ChatCommand;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;

public class ChatToggleCommands {
    public static void register() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            LiteralCommandNode<FabricClientCommandSource> chatNode = ClientCommandManager.literal("chat").executes(ChatCommand::chat).build();
            LiteralCommandNode<FabricClientCommandSource> toggleNode = ClientCommandManager.literal("toggle").executes(ChatCommand::toggle).build();
            LiteralCommandNode<FabricClientCommandSource> messageNode = ClientCommandManager.literal("message").build();
            ArgumentCommandNode<FabricClientCommandSource, String> messageArgumentNode = ClientCommandManager.argument("message", StringArgumentType.greedyString()).executes(ChatCommand::message).build();

            RootCommandNode<FabricClientCommandSource> rootCommandNode = dispatcher.getRoot();
            rootCommandNode.addChild(chatNode);
            chatNode.addChild(toggleNode);
            chatNode.addChild(messageNode);
            messageNode.addChild(messageArgumentNode);
        });
    }
}