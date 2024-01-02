package dev.mayaqq.chattoggle.extensions;

import org.spongepowered.asm.mixin.Unique;

import java.util.Map;

public interface KeyBindingExtension {
    @Unique
    Map<String, Integer> chattoggle$getCategoryMap();
}
