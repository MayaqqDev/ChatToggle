package dev.mayaqq.chattoggle.mixin;

import dev.mayaqq.chattoggle.Config;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @Inject(method = "sendMessage", at = @At("HEAD"))
    private void onSendMessage(Text message, boolean overlay, CallbackInfo ci) {

        System.out.println("1");
        if (Config.INSTANCE.on) {
            System.out.println("2");
            Text formattedMessage = Text.of(Config.INSTANCE.message + message.getString());
            message = formattedMessage;
        }
    }
}
