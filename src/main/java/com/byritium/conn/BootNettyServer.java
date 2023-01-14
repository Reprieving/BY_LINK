package com.byritium.conn;

import com.byritium.conn.protocol.domain.base.NettyServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.net.InetSocketAddress;
import java.util.List;

public class BootNettyServer {

    public void startup() throws InterruptedException {
        ServerBootstrap bootstrap = new ServerBootstrap();
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();
        bootstrap.group(boss, work)
                .handler(new LoggingHandler(LogLevel.DEBUG))
                .channel(NioServerSocketChannel.class)
                .childHandler(new NettyServerInitializer());

        List<Integer> ports = List.of(1000, 2000, 3000, 4000, 5000);
        for (int port : ports) {
            ChannelFuture f = bootstrap.bind(new InetSocketAddress(port)).sync();
            f.channel().closeFuture();
        }

    }
}
