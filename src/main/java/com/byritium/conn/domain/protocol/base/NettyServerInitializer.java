package com.byritium.conn.domain.protocol.base;

import com.byritium.conn.domain.protocol.http.HttpChannelHandler;
import com.byritium.conn.domain.protocol.mqtt.MqttChannelHandler;
import com.byritium.conn.infrastructure.utils.SpringUtils;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.mqtt.MqttDecoder;
import io.netty.handler.codec.mqtt.MqttEncoder;
import org.springframework.util.StringUtils;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;

public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) {
        ChannelPipeline pipeline = socketChannel.pipeline();
        InetSocketAddress socketAddress = socketChannel.localAddress();
        int port = socketAddress.getPort();
        ProtocolFactory protocolFactory = SpringUtils.getBean(ProtocolFactory.class);
        List<ProtocolHandler> protocolHandlerList = protocolFactory.get(port);
        for (ProtocolHandler protocolHandler : protocolHandlerList) {
            String name = protocolHandler.getName();
            ChannelHandler channelHandler = protocolHandler.getChannelHandler();
            if (channelHandler == null) {
                continue;
            }
            if (StringUtils.hasText(name)) {
                pipeline.addLast(name, channelHandler);
            } else {
                pipeline.addLast(channelHandler);
            }
        }
    }
}
