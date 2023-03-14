package com.byritium.conn.domain.connection;

import com.byritium.conn.infra.general.constance.CustomerType;
import com.byritium.conn.infra.general.constance.ProtocolType;
import lombok.Data;

@Data
public class ConnectionVo {
    private String identifier;//认证标志
    private CustomerType customerType;//客户端类型
    private ProtocolType protocolType;//协议类型

    public ConnectionVo(String identifier, String customerType, ProtocolType protocolType) {
        this.identifier = identifier;
        this.customerType = CustomerType.valueOf(customerType);
        this.protocolType = protocolType;
    }
}
