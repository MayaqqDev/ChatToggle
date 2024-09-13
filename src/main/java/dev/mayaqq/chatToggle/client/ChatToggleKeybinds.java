package dev.mayaqq.chatToggle.client;

//? if fabric {
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

//?}
//? if forge {
/*import net.minecraftforge.eventbus.api.SubscribeEvent;
  import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
  import net.minecraftforge.fml.common.Mod;
 *///?}
//? if neoforge {
/*import net.neoforged.bus.api.SubscribeEvent;
  import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
  import net.neoforged.fml.common.Mod;
 *///?}
//? if forge-like {
//? @Mod.EventBusSubscriber(modid = Estrogen.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
//? }
public class ChatToggleKeybinds {

    public static final KeyMapping TOGGLE = new KeyMapping("key.chattoggle.toggle", GLFW.GLFW_KEY_Y, "key.categories.chattoggle");

    //? if forge-like {
    /*@SubscribeEvent
     *///?}
    public static void registerKeybindings(
            //? if forge-like {
            /*RegisterKeyMappingsEvent event
             *///?}
    ) {
        //?if fabric {
        KeyBindingHelper.registerKeyBinding(TOGGLE);
        //?}
        //?if forge-like {
        /*event.register(TOGGLE);
         *///?}
    }
}
