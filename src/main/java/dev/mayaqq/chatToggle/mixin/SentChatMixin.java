package dev.mayaqq.chatToggle.mixin;

import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import dev.mayaqq.chatToggle.client.ChatToggleCommon;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//? >=1.20.1 {
@Mixin(ClientPacketListener.class)
//?} else {
/*@Mixin(LocalPlayer.class)
*///?}
public class SentChatMixin {
    //? >=1.20 {
    @Shadow
    public void sendCommand(String string) {}

    @Inject(method = "sendChat", at = @At("HEAD"), cancellable = true)
    private void onSendChat(String message, CallbackInfo ci) {
        String sent = ChatToggleCommon.handle(message);
        if (sent != null) {
            sendCommand(sent);
            ci.cancel();
        }
    }
    //?}
    //? =1.18.2 {
    /*@Shadow
    public void chat(String command) {
        return;
    }

    @Inject(method = "chat", at = @At("HEAD"), cancellable = true)
    private void onSendMessage(String message, CallbackInfo ci) {
        String sent = ChatToggleCommon.handle(message);
        if (sent != null) {
            chat(sent);
            ci.cancel();
        }
    }
    *///?}
    //? =1.19.2 {
    /*@Shadow
    private void sendCommand(String command, @Nullable Component preview) {
        return;
    }

    @Inject(method = "sendChat", at = @At("HEAD"), cancellable = true)
    private void onSendMessage(String message, Component component, CallbackInfo ci) {
        String sent = ChatToggleCommon.handle(message);
        if (sent != null) {
            sendCommand(sent, component);
            ci.cancel();
        }
    }
    *///?}
}
