package com.byritium.conn.domain.connection.service;

import com.byritium.conn.application.dto.ConnectionCommDto;
import com.byritium.conn.domain.connection.factory.ConnectionProcessor;
import com.byritium.conn.infra.general.constance.ProtocolType;
import io.netty.channel.Channel;
import org.springframework.stereotype.Service;

@Service
public class HttpConnectionDomainService implements ConnectionProcessor {
    @Override
    public ProtocolType protocolType() {
        return ProtocolType.HTTP;
    }

    @Override
    public void auth(Channel channel, Object message) {

    }

    @Override
    public ConnectionCommDto messaged(Channel channel, Object message) {
        ConnectionCommDto connectionCommDto = new ConnectionCommDto();
        return connectionCommDto;
    }
}

