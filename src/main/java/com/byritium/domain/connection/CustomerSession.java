package com.byritium.domain.connection;

import com.byritium.constance.CustomerType;
import com.byritium.constance.ProtocolType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CustomerSession {
    private CustomerType customerType;//协议类型
    private ProtocolType protocolType;//用户类型
    private String customerId;//客户端Id
    private LocalDateTime createTime;//创建时间
}
