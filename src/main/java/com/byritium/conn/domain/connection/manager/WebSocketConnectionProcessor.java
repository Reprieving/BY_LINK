package com.byritium.conn.domain.connection.manager;

import com.byritium.conn.infra.general.constance.ProtocolType;
import org.springframework.stereotype.Component;

@Component
public class WebSocketConnectionProcessor implements ConnectionProcessor{
    @Override
    public ProtocolType protocolType() {
        return null;
    }

    @Override
    public void connect() {

    }

    @Override
    public void messaged() {

    }
}
