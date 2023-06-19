package com.byritium.conn.domain.service;

import com.byritium.conn.application.dto.ConnectionCommDto;
import com.byritium.conn.infra.general.constance.ProtocolType;
import io.netty.channel.Channel;

public interface ConnectionMessageService {

    ProtocolType protocolType();

    void auth(Channel channel, Object message);

    ConnectionCommDto messaged(Channel channel, Object message);
}
