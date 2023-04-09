package dev.mayaqq.chattoggle;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v1.FabricClientCommandSource;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ChatCommand {
    public static final Path configFile = FabricLoader.getInstance().getConfigDir().resolve("chattoggle.json");
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static int chat(CommandContext<FabricClientCommandSource> context) {
        PlayerEntity player = context.getSource().getPlayer();
        player.sendMessage(Text.of("§f[§5ChatToggle§f] §aTo toggle chat do /chat toggle"), false);
        return 1;
    }

    public static int toggle(CommandContext<FabricClientCommandSource> context) {
        PlayerEntity player = context.getSource().getPlayer();
        if (ConfigRegistry.CONFIG.on) {
            ConfigRegistry.CONFIG.on = false;
            save();
            player.sendMessage(Text.of("§f[§5ChatToggle§f] §cChat Toggle is now off"), false);
        } else {
            ConfigRegistry.CONFIG.on = true;
            save();
            player.sendMessage(Text.of("§f[§5ChatToggle§f] §aChat Toggle is now on"), false);
        }
        return 1;
    }
    public static int message(CommandContext<FabricClientCommandSource> context) {
        PlayerEntity player = context.getSource().getPlayer();
        String message = StringArgumentType.getString(context, "message");
        ConfigRegistry.CONFIG.message = message;
        save();
        player.sendMessage(Text.of("§f[§5ChatToggle§f] §aMessage set to: " + message), false);
        return 1;
    }

    public static void save() {
        try {
            Files.deleteIfExists(configFile);

            JsonObject json = new JsonObject();
            json.addProperty("on", ConfigRegistry.CONFIG.on);
            json.addProperty("message", ConfigRegistry.CONFIG.message);

            Files.writeString(configFile, gson.toJson(json));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
