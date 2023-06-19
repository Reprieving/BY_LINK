package com.byritium.apis.model;

import com.byritium.infra.general.constance.ProtocolType;
import com.byritium.infra.general.constance.CustomerType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ConnectionInfo {
    private CustomerType customerType;//协议类型
    private ProtocolType protocolType;//用户类型
    private String customerId;//客户端Id
    private LocalDateTime createTime;//创建时间
}
