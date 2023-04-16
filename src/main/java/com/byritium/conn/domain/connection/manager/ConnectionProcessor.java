package com.byritium.conn.domain.connection.manager;

import com.byritium.conn.infra.general.constance.ProtocolType;
import io.netty.channel.Channel;

public interface ConnectionProcessor {

    ProtocolType protocolType();

    void connect(Channel channel, Object message);

    void messaged(Channel channel,Object message);
}
