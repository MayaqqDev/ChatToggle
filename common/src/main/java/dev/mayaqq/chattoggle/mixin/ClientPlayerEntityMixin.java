package dev.mayaqq.chattoggle.mixin;

import dev.mayaqq.chattoggle.ChatToggleConfig;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocalPlayer.class)
public abstract class ClientPlayerEntityMixin {
    @Shadow
    private void sendCommand(String command, @Nullable Component preview) {
        return;
    }

    @Inject(
            method = "sendChat",
            at = @At("HEAD"),
            cancellable = true
    )
    private void onSendMessage(String message, Component component, CallbackInfo ci) {
        if (ChatToggleConfig.CONFIG.on) {
            if (!message.startsWith("/")) {
                // call with modified message
                String modified = ChatToggleConfig.CONFIG.message + " " + message;
                if (modified.startsWith("/")) {
                    modified = modified.substring(1);
                }
                if (modified.endsWith(" ")) {
                    modified = modified.substring(0, modified.length() - 1);
                }
                sendCommand(modified, component);
                // cancel original method
                ci.cancel();
            }
        }
    }
}
