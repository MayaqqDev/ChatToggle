package dev.mayaqq.chatToggle.mixin;

import dev.mayaqq.chatToggle.client.ChatToggleCommon;
import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocalPlayer.class)
public class LocalPlayerMixin {
    //? <=1.18.2 {
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
        String sent = ChatToggleCommon.handle(message);
        if (sent != null) {
            chat(sent);
            ci.cancel();
        }
    }
    //?}
    //? =1.19.2 {
    /*@Shadow
    private void sendCommand(String command, @Nullable Component preview) {
        return;
    }

    @Inject(
            method = "sendChat",
            at = @At("HEAD"),
            cancellable = true
    )
    private void onSendMessage(String message, Component component, CallbackInfo ci) {
        String sent = ChatToggleCommon.handle(message);
        if (sent != null) {
            sendCommand(sent, component);
            ci.cancel();
        }
    }
    *///?}
}
