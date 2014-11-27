package com.khannedy.chating;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author Eko Khannedy
 * @since 11/27/14
 */
public class ClientApp {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap()
                .group(group)
                .channel(NioSocketChannel.class)
                .handler(new ClientInitializer());

        // konek ke server berlokasi di localhost dengan port 8000
        Channel server = bootstrap.connect("localhost", 8000).sync().channel();

        // jalankan ChatSender
        ChatSender chatSender = new ChatSender();
        chatSender.run(server);

        group.shutdownGracefully();
    }

}
