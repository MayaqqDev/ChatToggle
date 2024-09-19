package dev.mayaqq.chatToggle.client;

import dev.mayaqq.chatToggle.ChatToggleConfig;
//? =1.18.2 {
import net.minecraft.network.chat.TextComponent;
//?}

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class ChatToggleCommon {
    public static String handle(String message) {
        if (ChatToggleConfig.CONFIG.on) {
            if (message.startsWith("/")) return null;
            String modified = ChatToggleConfig.CONFIG.message + " " + message;
            //? =1.18.2 {
            if (!modified.startsWith("/")) modified = "/" + modified;
            //?} else {

            /*if (modified.startsWith("/")) modified = modified.substring(1);

            *///?}


            if (modified.endsWith(" ")) modified = modified.substring(0, modified.length() - 1);


            return(modified);
        }
        return null;
    }

    public static void sendChatMessage(String string, Player player) {
        //? =1.18.2 {
        player.displayClientMessage(new TextComponent(string), false);
        //?} else {
        /*player.displayClientMessage(Component.literal(string), false);
        *///?}
    }
}
