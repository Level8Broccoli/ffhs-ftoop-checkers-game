package ch.oliverbucher.checkers.view;

import ch.oliverbucher.checkers.resources.Config;

public class Message {

    public static void giveInfo(String messageType) {

        String message = messageType;

        if (Config.getValue(messageType) != null) {
            message = Config.getValue(messageType);
        }

        System.out.println(message);
    }
}
