package dev.mayaqq.chatToggle;

import dev.mayaqq.chatToggle.client.ChatToggleClient;

//? if fabric {
/*import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
*///?} elif neoforge {
/*import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

*///?} elif forge {
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
//?}
//? if forge-like
@Mod(ChatToggle.MOD_ID)
public class ChatToggleEntrypoint
    //? if fabric {
         /*implements ModInitializer, ClientModInitializer
    *///?}
{

    //? if fabric {
    /*@Override
    *///?}
    public void onInitialize(
            //? if forge-like {
             FMLCommonSetupEvent event
             //?}
    ) {
        ChatToggle.init();
    }

    //? if fabric {
    /*@Override
    *///?}
    public void onInitializeClient(
            //? if forge-like {
             FMLClientSetupEvent event
             //?}
    ) {
        ChatToggleClient.init();
    }

    //? if forge {
    public ChatToggleEntrypoint() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        setupForgeEvents(modEventBus);
    }
    //?}

    //? if neoforge {
    /*public ChatToggleEntrypoint(IEventBus modEventBus) {
        setupForgeEvents(modEventBus);
    }
    *///?}

    //? if forge-like {
     public void setupForgeEvents(IEventBus modEventBus) {
         modEventBus.addListener(this::onInitialize);
         modEventBus.addListener(this::onInitializeClient);
     }
    //?}
}