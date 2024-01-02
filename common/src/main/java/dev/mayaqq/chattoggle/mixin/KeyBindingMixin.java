package dev.mayaqq.chattoggle.mixin;

import dev.mayaqq.chattoggle.extensions.KeyBindingExtension;
import net.minecraft.client.KeyMapping;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.Map;

@Mixin(KeyMapping.class)
public class KeyBindingMixin implements KeyBindingExtension {
    @Shadow
    @Final
    private static Map<String, Integer> CATEGORY_SORT_ORDER;

    @Unique
    @Override
    public Map<String, Integer> chattoggle$getCategoryMap() {
        return CATEGORY_SORT_ORDER;
    }
}