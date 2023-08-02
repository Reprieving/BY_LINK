package com.byritium.application.dto;

import lombok.Data;

@Data
public class ConnectionDto {
    private String identifier;
    private String message;

    public ConnectionDto() {

    }

    public ConnectionDto(String identifier) {
        this.identifier = identifier;
    }

}
