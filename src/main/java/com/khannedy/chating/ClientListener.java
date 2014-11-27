package com.khannedy.chating;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author Eko Khannedy
 * @since 11/27/14
 */
public class ClientListener extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {

        String json = msg.toString();
        ChatMessage chatMessage = ChatMessageConverter.convertFromJSON(json);

        if (chatMessage.getAction().equals(ChatAction.LOGIN)) {
            // ada client yang login
            String pesan = chatMessage.getSender() + " masuk chat room";
            System.out.println(pesan);

        } else if (chatMessage.getAction().equals(ChatAction.LOGOUT)) {
            // ada client yang logout
            String pesan = chatMessage.getSender() + " keluar chat room";
            System.out.println(pesan);

        } else if (chatMessage.getAction().equals(ChatAction.CHAT)) {
            // ada client mengirim pesan
            String pesan = chatMessage.getSender() + " : "
                    + chatMessage.getMessage();
            System.out.println(pesan);
        }

    }
}
