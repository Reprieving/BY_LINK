package com.byritium.conn.domain.connection.factory;

import com.byritium.conn.infra.general.constance.ProtocolType;
import io.netty.channel.Channel;

public interface ConnectionProcessor {

    ProtocolType protocolType();

    void auth(Channel channel, Object message);

    void messaged(Channel channel,Object message);
}
