package dev.mayaqq.chattoggle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientCommandSource;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Commands extends ClientCommandSource {
    public static final Path configFile = FabricLoader.getInstance().getConfigDir().resolve("chattoggle.json");
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public Commands(ClientPlayNetworkHandler networkHandler, MinecraftClient client) {
        super(networkHandler, client);
    }

    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(
                    LiteralArgumentBuilder.<ServerCommandSource>literal("chat")
                            .executes(
                                    context -> {
                                        ServerPlayerEntity player = context.getSource().getPlayer();
                                        player.sendMessage(Text.of("§f(§5ChatToggle§f) §aTo toggle chat do /chat toggle"), false);
                                        return 1;
                                    }
                            )
                            .then(
                                    LiteralArgumentBuilder.<ServerCommandSource>literal("toggle")
                                            .executes(
                                                    context -> {
                                                        ServerPlayerEntity player = context.getSource().getPlayer();
                                                        if (Config.INSTANCE.on) {
                                                            Config.INSTANCE.on = false;
                                                            save();
                                                            player.sendMessage(Text.of("§cChat Toggle is now off"), false);
                                                        } else {
                                                            Config.INSTANCE.on = true;
                                                            save();
                                                            player.sendMessage(Text.of("§aChat Toggle is now on"), false);
                                                        }


                                                        return 1;
                                                    }
                                            )
                            )
            );
        });
    }
    public static void save() {
        try {
            Files.deleteIfExists(configFile);

            JsonObject json = new JsonObject();
            json.addProperty("on", Config.INSTANCE.on);
            json.addProperty("message", Config.INSTANCE.messagePrefix);

            Files.writeString(configFile, gson.toJson(json));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
