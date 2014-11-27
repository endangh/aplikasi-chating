package com.khannedy.chating;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author Eko Khannedy
 * @since 11/27/14
 */
public class ClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        // menambahkan string encoder dan decoder
        socketChannel.pipeline().addLast(new StringEncoder());
        socketChannel.pipeline().addLast(new StringDecoder());

        // menambahkan listener
        socketChannel.pipeline().addLast(new ClientListener());
    }
}
