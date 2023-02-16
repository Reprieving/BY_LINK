package com.byritium.conn.apis.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ConnectionBody {
    private String protocolType;//协议类型
    private String customerType;//用户类型
    private String customerId;//客户端Id
    private LocalDateTime createTime;//创建时间
}
