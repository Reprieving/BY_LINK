package com.byritium.conn.domain.protocol.base;

import com.byritium.conn.domain.protocol.http.HttpRequestHandler;
import com.byritium.conn.domain.protocol.mqtt.MqttChannelInboundHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.mqtt.MqttDecoder;
import io.netty.handler.codec.mqtt.MqttEncoder;

import java.net.InetSocketAddress;

public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        InetSocketAddress socketAddress = socketChannel.localAddress();
        int port = socketAddress.getPort();
        switch (port) {
            case 1000:
                pipeline.addLast(new HttpServerCodec());
                pipeline.addLast("httpAggregator", new HttpObjectAggregator(1024 * 1024 * 100));
                pipeline.addLast(new HttpRequestHandler());
                break;

            case 2000:
                pipeline.addLast("encoder", MqttEncoder.INSTANCE);
                pipeline.addLast("decoder", new MqttDecoder());
                pipeline.addLast(new MqttChannelInboundHandler());
                break;

        }
    }
}
