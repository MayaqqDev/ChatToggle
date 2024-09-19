package dev.mayaqq.chatToggle.mixin;

import dev.mayaqq.chatToggle.ChatToggleConfig;
import dev.mayaqq.chatToggle.client.ChatToggleCommon;
import dev.mayaqq.chatToggle.client.ChatToggleKeybinds;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    @Inject(at = @At("HEAD"), method = "tick")
    private void tick(CallbackInfo ci) {
        if (ChatToggleConfig.CONFIG == null) return;
        if (ChatToggleKeybinds.TOGGLE.consumeClick()) {
            chattoggle$keyPressed((Minecraft) (Object) this);
        }
    }

    @Unique
    private static void chattoggle$keyPressed(Minecraft client) {
        ChatToggleConfig.load();
        if (client.player == null) return;
        if (ChatToggleConfig.CONFIG.on) {
            ChatToggleConfig.CONFIG.on = false;
            ChatToggleCommon.sendChatMessage("§cChat Toggle is now off", client.player);
        } else {
            ChatToggleConfig.CONFIG.on = true;
            ChatToggleCommon.sendChatMessage("§aChat Toggle is now on", client.player);
        }
        try {
            ChatToggleConfig.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
