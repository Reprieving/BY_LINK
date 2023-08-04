package com.byritium.application.dto;

import com.byritium.types.constance.SendType;
import lombok.Data;

@Data
public class ConnectionDto {
    private String identifier;
    private SendType sendType;
    private String topic;
    private String message;

    public ConnectionDto() {

    }

    public ConnectionDto(String identifier) {
        this.identifier = identifier;
    }

}
