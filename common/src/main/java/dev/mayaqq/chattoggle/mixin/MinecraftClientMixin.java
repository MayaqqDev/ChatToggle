package dev.mayaqq.chattoggle.mixin;

import dev.mayaqq.chattoggle.ChatToggleConfig;
import dev.mayaqq.chattoggle.ChatToggleKeybinds;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;

@Mixin(Minecraft.class)
public class MinecraftClientMixin {
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
            client.player.displayClientMessage(Component.literal("§cChat Toggle is now off"), false);
        } else {
            ChatToggleConfig.CONFIG.on = true;
            client.player.displayClientMessage(Component.literal("§aChat Toggle is now on"), false);
        }
        try {
            ChatToggleConfig.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}