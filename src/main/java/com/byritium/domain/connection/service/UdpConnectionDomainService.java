package com.byritium.domain.connection.service;

import com.byritium.application.dto.ConnectionDto;
import com.byritium.domain.account.service.AccountAuthService;
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
    public ConnectionDto auth(Channel channel, Object message, Boolean authFlag, AccountAuthService accountAuthService) {
        ConnectionDto connectionDto = (ConnectionDto) message;
        accountAuthService.authenticate(connectionDto.getIdentifier());
        return connectionDto;
    }

    @Override
    public ConnectionDto messaged(Channel channel, Object message) {
        return (ConnectionDto) message;
    }
}
