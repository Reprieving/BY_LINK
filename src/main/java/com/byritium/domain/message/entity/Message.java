package com.byritium.domain.message.entity;

import com.byritium.constance.ProtocolType;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;


@Accessors(chain = true)
@Builder
@Data
public class Message {
    private ProtocolType protocolType;
    private String identifier;
    private Long times;
    private String content;
}
