package com.byritium.conn.domain.connection.manager;

import com.byritium.conn.infra.general.constance.ProtocolType;

public interface ConnectionProcessor {

    ProtocolType protocolType();

    void connect();

    void messaged();
}
