package com.github.nichxlas98.uhcorebot.utils;

public class SendConsoleUtil {

    public static void sendNotice(String message) {
        System.out.println("[NOTICE] " + message);
    }

    public static void sendError(String error) {
        System.out.println("[ERROR] " + error);
    }

    public static void sendMessage(String message) {
        System.out.println("[MESSAGE] " + message);
    }
}
