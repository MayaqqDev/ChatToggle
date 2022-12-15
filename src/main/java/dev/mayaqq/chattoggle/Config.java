package dev.mayaqq.chattoggle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import dev.isxander.yacl.api.ConfigCategory;
import dev.isxander.yacl.api.Option;
import dev.isxander.yacl.api.YetAnotherConfigLib;
import dev.isxander.yacl.gui.controllers.TickBoxController;
import dev.isxander.yacl.gui.controllers.string.StringController;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Config {
    public static final Config INSTANCE = new Config();

    public final Path configFile = FabricLoader.getInstance().getConfigDir().resolve("chattoggle.json");
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final YetAnotherConfigLib config;

    public boolean on = false;
    public String messagePrefix = "/ftbteams msg ";

    public Config() {
        this.config = YetAnotherConfigLib.createBuilder()
                .title(Text.of("Chat Toggle"))
                .category(ConfigCategory.createBuilder()
                        .name(Text.of("General"))
                        .option(Option.createBuilder(boolean.class)
                                .name(Text.of("On"))
                                .binding(
                                        true,
                                        () -> on,
                                        value -> on = value
                                )
                                .controller(TickBoxController::new)
                                .build())
                        .option(Option.createBuilder(String.class)
                                .name(Text.of("Message"))
                                .binding(
                                        "Message",
                                        () -> messagePrefix,
                                        value -> messagePrefix = value
                                )
                                .controller(StringController::new)
                                .build())
                        .build())
                .save(this::save)
                .build();
    }

    public void save() {
        try {
            Files.deleteIfExists(configFile);

            JsonObject json = new JsonObject();
            json.addProperty("on", on);
            json.addProperty("message", messagePrefix);

            Files.writeString(configFile, gson.toJson(json));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        try {
            if (Files.notExists(configFile)) {
                save();
                return;
            }

            JsonObject json = gson.fromJson(Files.readString(configFile), JsonObject.class);

            if (json.has("on"))
                on = json.getAsJsonPrimitive("on").getAsBoolean();
            if (json.has("message"))
                messagePrefix = json.getAsJsonPrimitive("message").getAsString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Screen makeScreen(Screen parent) {
        return config.generateScreen(parent);
    }
}
