package dev.mayaqq.chattoggle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.*;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class ChatToggleConfig {
    public static Config CONFIG = new Config();

    private static final File configFile = new File(FabricLoader.getInstance().getConfigDir().toFile(),"chattoggle.json");
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void register() {load();}

    public static void load() {

        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
                save();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                CONFIG = gson.fromJson(new FileReader(configFile), Config.class);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void save() {
        try {
            var writer = new FileWriter(configFile);
            writer.write(gson.toJson(CONFIG));
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static class Config {
        public Boolean on = false;
        public String message = "ftbteams msg";

        public Config() {}
    }
}