package com.khannedy.chating;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author Eko Khannedy
 * @since 11/27/14
 */
public class ServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        // mengkonversi dari ByteBuf ke String JSON
        socketChannel.pipeline().addLast(new StringDecoder());
        socketChannel.pipeline().addLast(new StringEncoder());

        // menambahkan listener
        socketChannel.pipeline().addLast(new ServerListener());
    }
}
