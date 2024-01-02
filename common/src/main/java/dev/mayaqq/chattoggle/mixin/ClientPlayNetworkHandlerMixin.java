package dev.mayaqq.chattoggle.mixin;

import dev.mayaqq.chattoggle.ChatToggleConfig;
import net.minecraft.client.multiplayer.ClientPacketListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ClientPacketListener.class, priority = 1)
public abstract class ClientPlayNetworkHandlerMixin {
    @Shadow public abstract void sendCommand(String string);

    @Inject(method = "sendChat", at = @At("HEAD"), cancellable = true)
    private void onSendChat(String message, CallbackInfo ci) {
        if (ChatToggleConfig.CONFIG.on) {
            if (message.startsWith("/")) return;
            String modified = ChatToggleConfig.CONFIG.message + " " + message;
            if (modified.startsWith("/")) modified = modified.substring(1);
            if (modified.endsWith(" ")) modified = modified.substring(0, modified.length() - 1);
            sendCommand(modified);
            ci.cancel();
        }
    }
}
