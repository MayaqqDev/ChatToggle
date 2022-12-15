package dev.mayaqq.chattoggle.mixin;

import dev.mayaqq.chattoggle.Config;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerEntityMixin {
    @Shadow
    public abstract void sendCommand(String command, @Nullable Text preview);

    @Inject(
            method = "sendChatMessage",
            at = @At("HEAD"),
            cancellable = true
    )
    private void onSendMessage(String message, Text preview, CallbackInfo ci) {
        if (Config.INSTANCE.on) {
            if (!message.startsWith("/")) {
                // call with modified message
                String modified = Config.INSTANCE.messagePrefix + message;
                sendCommand(modified, preview);
                // cancel original method
                ci.cancel();
            }
        }
    }
}
