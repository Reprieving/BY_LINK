package com.byritium.conn.domain.protocol.base;

import io.netty.channel.ChannelHandler;
import lombok.Data;

@Data
public class ProtocolHandler {
    private String name;
    private ChannelHandler channelHandler;

    ProtocolHandler() {
    }

    ProtocolHandler(ChannelHandler channelHandler) {
        this.channelHandler = channelHandler;
    }

    ProtocolHandler(String name, ChannelHandler channelHandler) {
        this.name = name;
        this.channelHandler = channelHandler;
    }
}
