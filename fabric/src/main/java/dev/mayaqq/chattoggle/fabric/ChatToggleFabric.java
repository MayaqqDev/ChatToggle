package dev.mayaqq.chattoggle.fabric;

import dev.mayaqq.chattoggle.ChatToggle;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class ChatToggleFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        ChatToggle.init(FabricLoader.getInstance().getConfigDir());
    }
}