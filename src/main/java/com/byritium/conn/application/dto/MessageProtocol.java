package com.byritium.conn.application.dto;

import lombok.Data;

@Data
public class MessageProtocol {
    private int len;
    private byte[] content;
}
