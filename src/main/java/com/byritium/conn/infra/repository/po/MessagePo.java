package com.byritium.conn.infra.repository.po;

import com.byritium.conn.infra.general.constance.ProtocolType;

public class MessagePo {
    private Long id;
    private ProtocolType protocolType;
    private Long identifier;
    private String content;
    private Long createTime;
}
