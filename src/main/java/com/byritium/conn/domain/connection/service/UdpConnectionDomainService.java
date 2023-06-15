package com.byritium.conn.domain.connection.service;

import com.byritium.conn.domain.connection.factory.ConnectionProcessor;
import com.byritium.conn.infra.general.constance.ProtocolType;
import io.netty.channel.Channel;
import org.springframework.stereotype.Service;

@Service
public class UdpConnectionDomainService implements ConnectionProcessor {
    @Override
    public ProtocolType protocolType() {
        return null;
    }

    @Override
    public void auth(Channel channel, Object message) {

    }

    @Override
    public void messaged(Channel channel, Object message) {

    }
}
