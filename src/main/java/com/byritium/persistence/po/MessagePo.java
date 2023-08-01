package com.byritium.persistence.po;

import com.byritium.types.constance.ProtocolType;
import lombok.Data;

@Data
public class MessagePo {
    private Long id;
    private ProtocolType protocolType;
    private Long identifier;
    private String content;
    private Long createTime;
}
