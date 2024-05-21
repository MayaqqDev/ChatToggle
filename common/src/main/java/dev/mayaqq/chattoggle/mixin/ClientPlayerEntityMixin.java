package dev.mayaqq.chattoggle.mixin;

import dev.mayaqq.chattoggle.ChatToggleConfig;
import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocalPlayer.class)
public abstract class ClientPlayerEntityMixin {
    @Shadow
    public void chat(String command) {
        return;
    }

    @Inject(
            method = "chat",
            at = @At("HEAD"),
            cancellable = true
    )
    private void onSendMessage(String message, CallbackInfo ci) {
        if (ChatToggleConfig.CONFIG.on) {
            if (!message.startsWith("/")) {
                // call with modified message
                String modified = ChatToggleConfig.CONFIG.message + " " + message;
                if (!modified.startsWith("/")) {
                    modified = "/" + modified;
                }
                if (modified.endsWith(" ")) {
                    modified = modified.substring(0, modified.length() - 1);
                }
                chat(modified);
                // cancel original method
                ci.cancel();
            }
        }
    }
}