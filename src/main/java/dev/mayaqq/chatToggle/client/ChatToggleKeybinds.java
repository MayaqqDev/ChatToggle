package dev.mayaqq.chatToggle.client;

import dev.mayaqq.chatToggle.ChatToggle;
import net.minecraft.client.KeyMapping;

import org.lwjgl.glfw.GLFW;
//? if fabric {
/*import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
*///?}
//? if forge {
/*//? if >=1.19.2 {
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
//?} else {
/^import net.minecraftforge.client.ClientRegistry;
^///?}
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.api.distmarker.Dist;
@Mod.EventBusSubscriber(modid = ChatToggle.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
 *///?}
//? if neoforge {
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
@EventBusSubscriber(modid = ChatToggle.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
//?}
//? if forge-like {
//?}
public class ChatToggleKeybinds {

    public static final KeyMapping TOGGLE = new KeyMapping("key.chattoggle.toggle", GLFW.GLFW_KEY_Y, "key.categories.chattoggle");

    //? if forge-like {
    @SubscribeEvent
     //?}
    public static void registerKeybindings(
            //? if forge-like && >=1.19.2 {
            RegisterKeyMappingsEvent event
             //?}
    ) {
        //? if fabric {
        /*KeyBindingHelper.registerKeyBinding(TOGGLE);
        *///?}
        //? if forge-like && >=1.19.2 {
        event.register(TOGGLE);
         //?}
        //? if forge && =1.18.2 {
        /*ClientRegistry.registerKeyBinding(TOGGLE);
        *///?}
    }
}
