package dev.mayaqq.chatToggle.client;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.tree.ArgumentCommandNode;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.mojang.brigadier.tree.RootCommandNode;
import dev.mayaqq.chatToggle.ChatToggle;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

//? if fabric {
/*//? =1.18.2 {
/^import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v1.FabricClientCommandSource;
^///?} else {
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
//?}
*///?}
//? if neoforge {
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterClientCommandsEvent;

@EventBusSubscriber(modid = ChatToggle.MOD_ID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
//?}
//? if forge {
/*import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ChatToggle.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
*///?}
public class ChatToggleCommands {
    //? if forge-like {
    @SubscribeEvent
    //?}
    public static void registerCommands(
            //? if forge-like {
            RegisterClientCommandsEvent event
            //?}
    ) {
        //? if fabric {
        /*//? =1.18.2 {

        /^register(ClientCommandManager.DISPATCHER, new FabricClientCommandManager());
        ^///?} else {
        ClientCommandRegistrationCallback.EVENT.register((dispatcherFabric, registryAccess) -> {
            register(dispatcherFabric, new FabricClientCommandManager());
        });
        //?}
        *///?}

        //? if forge-like {
        register(event.getDispatcher(), new ForgeClientCommandManager());
        //?}
    }

    public static <T> void register(CommandDispatcher<T> dispatcher, ChatToggleClientCommandManager<T> manager) {
        LiteralCommandNode<T> chat = dispatcher.register(manager.literal("chat").executes(ChatCommand::chat));
        LiteralCommandNode<T> toggle = dispatcher.register(manager.literal("toggle").executes(ChatCommand::toggle));
        LiteralCommandNode<T> message = dispatcher.register(manager.literal("message").executes(context -> {return 1;}));
        ArgumentCommandNode<T, String> messageArg = manager.argument("message", StringArgumentType.greedyString()).executes(ChatCommand::message).build();

        RootCommandNode<T> root = dispatcher.getRoot();


        chat.addChild(toggle);
        chat.addChild(message);
        message.addChild(messageArg);

        root.addChild(chat);
    }


    public interface ChatToggleClientCommandManager<T> {

        LiteralArgumentBuilder<T> literal(String name);

        <I> RequiredArgumentBuilder<T, I> argument(String name, ArgumentType<I> type);

        boolean hasPermission(T source, int permissionLevel);

        void sendFailure(T source, Component component);
    }

    //? if fabric {
    /*public static class FabricClientCommandManager implements ChatToggleClientCommandManager<FabricClientCommandSource> {

        @Override
        public LiteralArgumentBuilder<FabricClientCommandSource> literal(String name) {
            return ClientCommandManager.literal(name);
        }

        @Override
        public <I> RequiredArgumentBuilder<FabricClientCommandSource, I> argument(String name, ArgumentType<I> type) {
            return ClientCommandManager.argument(name, type);
        }

        @Override
        public boolean hasPermission(FabricClientCommandSource source, int permissionLevel) {
            return source.hasPermission(permissionLevel);
        }

        @Override
        public void sendFailure(FabricClientCommandSource source, Component component) {
            source.sendError(component);
        }
    }
    *///?}

    //? if forge-like {
    private static class ForgeClientCommandManager implements ChatToggleClientCommandManager<CommandSourceStack> {

        @Override
        public LiteralArgumentBuilder<CommandSourceStack> literal(String name) {
            return Commands.literal(name);
        }

        @Override
        public <I> RequiredArgumentBuilder<CommandSourceStack, I> argument(String name, ArgumentType<I> type) {
            return Commands.argument(name, type);
        }

        @Override
        public boolean hasPermission(CommandSourceStack source, int permissionLevel) {
            return source.hasPermission(permissionLevel);
        }

        @Override
        public void sendFailure(CommandSourceStack source, Component component) {
            source.sendFailure(component);
        }
    }
    //?}
}
