package dev.mayaqq.chatToggle.client;

public class ChatToggleClient {
    public static void init() {
        //?if fabric {
        ChatToggleKeybinds.registerKeybindings();
        //?}
    }
}
