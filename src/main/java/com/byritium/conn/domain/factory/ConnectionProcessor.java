package com.byritium.conn.domain.factory;

import com.byritium.conn.application.dto.ConnectionCommDto;
import com.byritium.conn.application.dto.ConnectionDto;
import com.byritium.conn.infra.general.constance.ProtocolType;
import io.netty.channel.Channel;

public interface ConnectionProcessor {

    ProtocolType protocolType();

    void auth(Channel channel, Object message);

    ConnectionCommDto messaged(Channel channel, Object message);
}
