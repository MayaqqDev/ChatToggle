package dev.mayaqq.chattoggle.mixin;

import com.mojang.brigadier.CommandDispatcher;
import dev.mayaqq.chattoggle.commands.ChatToggleCommands;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Commands.class)
public abstract class CommandManagerMixin {
    @Shadow
    @Final
    private CommandDispatcher<CommandSourceStack> dispatcher;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void addCommands(Commands.CommandSelection environment, CommandBuildContext registryAccess, CallbackInfo ci) {
        ChatToggleCommands.register(dispatcher, environment, registryAccess);
    }
}
