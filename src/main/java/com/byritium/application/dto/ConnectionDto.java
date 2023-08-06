package com.byritium.application.dto;

import com.byritium.types.constance.SendType;
import lombok.Data;

@Data
public class ConnectionDto {
    private Long appId;
    private String identifier;
    private Long groupId;
    private SendType sendType;
    private String topic;
    private String message;

    public ConnectionDto() {

    }

    public ConnectionDto(String identifier) {
        this.identifier = identifier;
    }

}
