package com.byritium.domain.connection;

import com.byritium.infra.general.constance.ProtocolType;
import lombok.Data;

@Data
public class ConnectionVo {
    private String identifier;//认证标志
    private ProtocolType protocolType;//协议类型

    public ConnectionVo(String identifier, ProtocolType protocolType) {
        this.identifier = identifier;
        this.protocolType = protocolType;
    }
}
