package com.byritium.application.command;

import com.byritium.types.constance.ProtocolType;
import io.netty.channel.Channel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConnectionCommand {
    private ProtocolType protocolType;
    private Channel channel;
    private Object message;
    private Boolean authFlag;

}
