package com.byritium;

import com.byritium.apis.netty.*;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.mqtt.MqttDecoder;
import io.netty.handler.codec.mqtt.MqttEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

import java.net.InetSocketAddress;

public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) {
        ChannelPipeline pipeline = socketChannel.pipeline();
        InetSocketAddress socketAddress = socketChannel.localAddress();
        int port = socketAddress.getPort();
        switch (port) {
            case 1000:
                pipeline.addLast(new LoggingHandler(LogLevel.INFO));
                pipeline.addLast(new TcpChannelHandler());
                break;

            case 2000:
                pipeline.addLast(new LoggingHandler(LogLevel.INFO));
                pipeline.addLast(new UdpChannelHandler());
                break;

            case 3000:
                pipeline.addLast(new LoggingHandler(LogLevel.INFO));
                pipeline.addLast("http-codec", new HttpServerCodec());
                pipeline.addLast("http-aggregator", new HttpObjectAggregator(1024 * 1024 * 100));
                pipeline.addLast("http-chunked", new ChunkedWriteHandler());
                pipeline.addLast(new HttpChannelHandler());
                break;

            case 4000:
                pipeline.addLast(new LoggingHandler(LogLevel.INFO));
                pipeline.addLast("http-codec", new HttpServerCodec());
                pipeline.addLast("http-aggregator", new HttpObjectAggregator(65536));
                pipeline.addLast("http-chunked", new ChunkedWriteHandler());
                pipeline.addLast(new WebSocketChannelHandler());
                break;


            case 5000:
                pipeline.addLast(new LoggingHandler(LogLevel.INFO));
                pipeline.addLast("encoder", MqttEncoder.INSTANCE);
                pipeline.addLast("decoder", new MqttDecoder());
                pipeline.addLast(new MqttChannelHandler());
                break;

        }
    }
}
