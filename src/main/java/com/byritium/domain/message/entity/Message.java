package com.byritium.domain.message.entity;

import com.byritium.types.constance.ProtocolType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Message {
    private ProtocolType protocolType;
    private String identifier;
    private Long times;
    private String content;
}
