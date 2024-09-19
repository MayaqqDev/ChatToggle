package dev.mayaqq.chatToggle.mixin;

import dev.mayaqq.chatToggle.client.ChatToggleCommon;
import net.minecraft.client.multiplayer.ClientPacketListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPacketListener.class)
public abstract class ClientPlayerNetworkHandlerMixin {
    //? >=1.20 {
    /*@Shadow
    public abstract void sendCommand(String string);

    @Inject(method = "sendChat", at = @At("HEAD"), cancellable = true)
    private void onSendChat(String message, CallbackInfo ci) {
        String sent = ChatToggleCommon.handle(message);
        if (sent != null) {
            sendCommand(sent);
            ci.cancel();
        }
    }
    *///?}
}
