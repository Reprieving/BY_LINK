package com.byritium.conn.apis.model;

import com.byritium.conn.infra.general.constance.CustomerType;
import com.byritium.conn.infra.general.constance.ProtocolType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ConnectionInfo {
    private ProtocolType protocolType;//协议类型
    private CustomerType customerType;//用户类型
    private String customerId;//客户端Id
    private LocalDateTime createTime;//创建时间
}
