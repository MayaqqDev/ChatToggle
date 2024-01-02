package dev.mayaqq.chattoggle.forge;

import dev.mayaqq.chattoggle.ChatToggle;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod(ChatToggle.MOD_ID)
public class ChatToggleForge {
    public ChatToggleForge() {
        ChatToggle.init(FMLPaths.CONFIGDIR.get());
    }
}