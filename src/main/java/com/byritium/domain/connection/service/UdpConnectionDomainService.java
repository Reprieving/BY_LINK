package com.byritium.domain.connection.service;

import com.byritium.application.dto.ConnectionCommDto;
import com.byritium.types.constance.ProtocolType;
import io.netty.channel.Channel;
import org.springframework.stereotype.Service;

@Service
public class UdpConnectionDomainService implements ConnectionMessageService {
    @Override
    public ProtocolType protocolType() {
        return ProtocolType.UDP;
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
