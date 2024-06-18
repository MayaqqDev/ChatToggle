package dev.mayaqq.chattoggle.forge;

import dev.mayaqq.chattoggle.ChatToggle;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLPaths;

@Mod(ChatToggle.MOD_ID)
public class ChatToggleForge {
    public ChatToggleForge() {
        ChatToggle.init(FMLPaths.CONFIGDIR.get());
        ChatToggle.translationsWork = true;
    }
}