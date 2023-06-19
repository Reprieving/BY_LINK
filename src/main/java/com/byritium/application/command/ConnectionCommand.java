package com.byritium.application.command;

import com.byritium.infra.general.constance.ProtocolType;
import io.netty.channel.Channel;
import lombok.Data;

@Data
public class ConnectionCommand {
    private ProtocolType protocolType;
    private Channel channel;
    private Object message;

    public ConnectionCommand(ProtocolType protocolType, Channel channel, Object msg) {
        this.protocolType = protocolType;
        this.channel = channel;
        this.message = msg;
    }
}
