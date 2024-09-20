package dev.mayaqq.chatToggle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

//? if fabric {
/*import net.fabricmc.loader.api.FabricLoader;
*///?}

//? if neoforge {
import net.neoforged.fml.loading.FMLPaths;
//?}

//? if forge {
/*import net.minecraftforge.fml.loading.FMLPaths;
*///?}

import java.io.*;

public class ChatToggleConfig {
    public static Config CONFIG = new Config();

    private static File configFile = new File(
            //? if fabric {
            /*FabricLoader.getInstance().getConfigDir().toFile()
            *///?}
            //? if forge-like {
            FMLPaths.CONFIGDIR.get().toFile()
            //?}
            , "chattoggle.json");
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

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

    public static void save() throws IOException {
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