package com.byritium.conn.domain.connection.manager;

import com.byritium.conn.infra.general.constance.ProtocolType;
import org.springframework.stereotype.Component;

@Component
public class MqttConnectionProcessor implements ConnectionProcessor{
    private static final ProtocolType protocolType = ProtocolType.MQTT;

    @Override
    public ProtocolType protocolType() {
        return protocolType;
    }

    @Override
    public void connect() {

    }

    @Override
    public void messaged() {

    }
}
