package dev.mayaqq.chattoggle.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.tree.ArgumentCommandNode;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class ChatToggleCommands {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher,  Commands.CommandSelection environment, CommandBuildContext registryAccess) {
        LiteralCommandNode<CommandSourceStack> chat = dispatcher.register(Commands.literal("chat").executes(ChatCommand::chat));
        LiteralCommandNode<CommandSourceStack> toggle = Commands.literal("toggle").executes(ChatCommand::toggle).build();
        LiteralCommandNode<CommandSourceStack> message = Commands.literal("message").build();
        ArgumentCommandNode<CommandSourceStack, String> messageArg = Commands.argument("message", StringArgumentType.greedyString()).executes(ChatCommand::message).build();

        chat.addChild(toggle);
        chat.addChild(message);
        message.addChild(messageArg);
    }
}
