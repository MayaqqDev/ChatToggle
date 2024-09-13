package dev.mayaqq.chatToggle;

import net.fabricmc.loader.api.FabricLoader;

import java.io.File;

public class ChatToggleConfig {
    public static Config CONFIG = new Config();

    private static File configFile = new File(
            //?if fabric {
            FabricLoader.getInstance().getConfigDir().toFile()
            //?}
            //?if forge {
            /*
             */
            , "chattoggle.json");


    public static class Config {
        //the thing to write in the config file
        public Boolean on = false;
        public String message = "ftbteams msg";

        public Config() {}
    }
}
