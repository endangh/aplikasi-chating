package com.khannedy.chating;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author Eko Khannedy
 * @since 11/27/14
 */
public class ServerApp {

    public static void main(String[] args) throws InterruptedException {

        EventLoopGroup boosGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap()
                .group(boosGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ServerInitializer());

        bootstrap.bind(8000).sync().channel().closeFuture().sync();

        workerGroup.shutdownGracefully();
        boosGroup.shutdownGracefully();
    }

}
