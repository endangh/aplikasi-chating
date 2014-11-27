package com.khannedy.chating;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author Eko Khannedy
 * @since 11/27/14
 */
public class ChatMessageConverter {

    private static Gson gson = new GsonBuilder().
            setDateFormat("dd/MM/yyyy HH:mm:ss").
            create();

    public static String convertToJSON(ChatMessage chatMessage) {
        return gson.toJson(chatMessage);
    }

    public static ChatMessage convertFromJSON(String json) {
        return gson.fromJson(json, ChatMessage.class);
    }
}
