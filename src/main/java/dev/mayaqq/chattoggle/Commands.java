package dev.mayaqq.chattoggle;

import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientCommandSource;
import net.minecraft.client.network.ClientPlayNetworkHandler;

public class Commands extends ClientCommandSource {

    public Commands(ClientPlayNetworkHandler networkHandler, MinecraftClient client) {
        super(networkHandler, client);
    }

    public static void register() {
        ClientCommandManager.DISPATCHER.register(
                ClientCommandManager.literal("chat")
                        .executes(ChatCommand::chat)
                        .then(
                                ClientCommandManager.literal("toggle")
                                        .executes(ChatCommand::toggle)
                        )
                        .then(ClientCommandManager.literal("message")
                                .then(ClientCommandManager.argument("message", StringArgumentType.string())
                                        .executes(ChatCommand::message)
                                        ))
        );
    }
}