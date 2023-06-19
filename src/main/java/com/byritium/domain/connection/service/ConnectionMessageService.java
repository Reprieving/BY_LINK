package com.byritium.domain.connection.service;

import com.byritium.application.dto.ConnectionCommDto;
import com.byritium.constance.ProtocolType;
import io.netty.channel.Channel;

public interface ConnectionMessageService {

    ProtocolType protocolType();

    void auth(Channel channel, Object message);

    ConnectionCommDto messaged(Channel channel, Object message);
}
