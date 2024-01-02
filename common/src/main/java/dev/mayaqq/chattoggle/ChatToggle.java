package dev.mayaqq.chattoggle;

import java.nio.file.Path;

public class ChatToggle {
    public static final String MOD_ID = "chattoggle";
    public static boolean translationsWork = false;

    public static Path configFolderPath;

    public static void init(Path configFolderPath) {
        ChatToggle.configFolderPath = configFolderPath;
        ChatToggleConfig.load();
        ChatToggleKeybinds.register();
    }
}
