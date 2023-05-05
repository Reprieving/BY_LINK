package com.byritium.conn.application.command;

import com.byritium.conn.infra.general.constance.ProtocolType;
import io.netty.channel.Channel;
import lombok.Getter;

@Getter
public class ConnectionCommand {
    private ProtocolType protocolType;
    private Channel channel;
    private Object message;
}
