package com.byritium.domain.connection.service;

import com.byritium.application.dto.ConnectionDto;
import com.byritium.types.constance.ProtocolType;
import io.netty.channel.Channel;
import org.springframework.stereotype.Service;

@Service
public class TcpConnectionDomainService implements ConnectionMessageService {
    @Override
    public ProtocolType protocolType() {
        return ProtocolType.TCP;
    }

    @Override
    public void auth(Channel channel, Object message) {

    }

    @Override
    public ConnectionDto messaged(Channel channel, Object message) {
        ConnectionDto connectionDto = new ConnectionDto();
        return connectionDto;
    }
}

