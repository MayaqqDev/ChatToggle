package dev.mayaqq.chattoggle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientCommandSource;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.player.PlayerEntity;
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
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(
                    LiteralArgumentBuilder.<FabricClientCommandSource>literal("chat")
                            .executes(
                                    context -> {
                                        PlayerEntity player = context.getSource().getPlayer();
                                        player.sendMessage(Text.of("§f(§5ChatToggle§f) §aTo toggle chat do /chat toggle"), false);
                                        return 1;
                                    }
                            )
                            .then(
                                    LiteralArgumentBuilder.<FabricClientCommandSource>literal("toggle")
                                            .executes(
                                                    context -> {
                                                        PlayerEntity player = context.getSource().getPlayer();
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
