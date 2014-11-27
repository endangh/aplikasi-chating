package com.khannedy.chating;

import java.util.Date;

/**
 * @author Eko Khannedy
 * @since 11/27/14
 */
public class ChatMessage {

    private String sender; // informasi pengirim
    private String message; // pesan yang dikirim
    private ChatAction action; // jenis aksi (login, logout, chat)
    private Date datetime; // waktu pengiriman pesan

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ChatAction getAction() {
        return action;
    }

    public void setAction(ChatAction action) {
        this.action = action;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
}
