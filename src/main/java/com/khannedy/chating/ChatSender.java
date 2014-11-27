package com.khannedy.chating;

import io.netty.channel.Channel;

import java.util.Date;
import java.util.Scanner;

/**
 * @author Eko Khannedy
 * @since 11/27/14
 */
public class ChatSender {

    /**
     * Scanner digunakan untuk menerima input dari user
     */
    private Scanner input = new Scanner(System.in);

    private String username;

    /**
     * Menjalankan chat sender
     *
     * @param server server channel
     */
    public void run(Channel server) {
        while (true) {
            // membaca pesan yang diketikkan user
            String message = input.nextLine();
            if (message.startsWith("login") && username == null) {
                // login user
                username = message.substring("login".length()).trim();
                sendLoginMessage(server);
            } else if (message.equals("logout")) {
                // logout user
                sendLogoutMessage(server);
                // selesai
                break;
            } else {
                // chat user
                sendChatMessage(server, message);
            }
        }
    }

    /**
     * Mengirim pesan login ke server
     *
     * @param server server channel
     */
    private void sendLoginMessage(Channel server) {
        // buat objek chat message
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setAction(ChatAction.LOGIN);
        chatMessage.setDatetime(new Date());
        chatMessage.setSender(username);

        // konver ke json dan kirim ke server
        String json = ChatMessageConverter.convertToJSON(chatMessage);
        server.writeAndFlush(json);
    }

    /**
     * Mengirim pesan logout ke server
     *
     * @param server server channel
     */
    private void sendLogoutMessage(Channel server) {
        // buat objek chat message
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setAction(ChatAction.LOGOUT);
        chatMessage.setDatetime(new Date());
        chatMessage.setSender(username);

        // konver ke json dan kirim ke server
        String json = ChatMessageConverter.convertToJSON(chatMessage);
        server.writeAndFlush(json);
    }

    /**
     * Mengirim pesan chat ke seluruh client
     *
     * @param server  server channel
     * @param message pesan chat
     */
    private void sendChatMessage(Channel server, String message) {
        // buat objek chat message
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setAction(ChatAction.CHAT);
        chatMessage.setDatetime(new Date());
        chatMessage.setSender(username);
        chatMessage.setMessage(message); // jangan lupa pesannya

        // konver ke json dan kirim ke server
        String json = ChatMessageConverter.convertToJSON(chatMessage);
        server.writeAndFlush(json);
    }

}
