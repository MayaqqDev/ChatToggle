package dev.mayaqq.chattoggle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.*;

public class ConfigRegistry {
    public static Config CONFIG = new Config();

    private static File configFile = new File(FabricLoader.getInstance().getConfigDir().toFile(),"chattoggle.json");
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void load() {

        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
                saveConfig();
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

    public static void saveConfig() throws IOException {
        //Write some info into the file under here
        var writer = new FileWriter(configFile);
        writer.write(gson.toJson(CONFIG));
        writer.close();
    }

    public static class Config {
        //the thing to write in the config file
        public Boolean on = false;
        public String message = "ftbteams msg";

        public Config() {}
    }
}