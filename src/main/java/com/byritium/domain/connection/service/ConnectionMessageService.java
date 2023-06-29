package com.byritium.domain.connection.service;

import com.byritium.application.dto.ConnectionDto;
import com.byritium.types.constance.ProtocolType;
import io.netty.channel.Channel;

public interface ConnectionMessageService {

    ProtocolType protocolType();

    ConnectionDto auth(Channel channel, Object message);

    ConnectionDto messaged(Channel channel, Object message);
}
