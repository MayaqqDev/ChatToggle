package dev.mayaqq.chattoggle;

import net.fabricmc.api.ClientModInitializer;

public class ChatToggle implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        Commands.register();
    }
}
