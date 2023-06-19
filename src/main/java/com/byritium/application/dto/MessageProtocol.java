package com.byritium.application.dto;

import lombok.Data;

@Data
public class MessageProtocol {
    private int len;
    private byte[] content;
}
