package dev.mayaqq.chattoggle.mixin;

import dev.mayaqq.chattoggle.ChatToggleConfig;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {
    @Inject(method = "sendChatMessage(Ljava/lang/String;)V", at = @At("HEAD"), cancellable = true)
    private void onSendChatMessage(String message, CallbackInfo ci) {
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
                ((ClientPlayNetworkHandler) (Object) this).sendCommand(modified);
                // cancel original method
                ci.cancel();
            }
        }
    }
}