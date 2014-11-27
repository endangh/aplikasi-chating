package com.khannedy.chating;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author Eko Khannedy
 * @since 11/27/14
 */
public class ServerListener extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {

        String json = msg.toString();
        ChatMessage chatMessage = ChatMessageConverter.convertFromJSON(json);

        if (chatMessage.getAction().equals(ChatAction.LOGIN)) {
            // chat action adalah login, tambahkan ke database
            ClientDatabase.login(ctx.channel());
            // beritahu client yang lain jika ada yang login
            ClientDatabase.chat(ctx.channel(), json);
        } else if (chatMessage.getAction().equals(ChatAction.LOGOUT)) {
            // chat action adalah logout, hapus dari database
            ClientDatabase.logout(ctx.channel());
            // beritahu client yang lain jika ada yang logout
            ClientDatabase.chat(ctx.channel(), json);
            // close koneksi client
            ctx.channel().close();
        } else if (chatMessage.getAction().equals(ChatAction.CHAT)) {
            // kirim pesan ke client yang lain
            ClientDatabase.chat(ctx.channel(), json);
        }

    }
}
