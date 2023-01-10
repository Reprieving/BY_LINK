package com.byritium.conn.domain.protocol.tcp.dto;

import lombok.Data;

@Data
public class TcpCustomMessage {
    private int length;
    private byte[] content;

    public TcpCustomMessage(int length, byte[] bytes) {
        this.length = length;
        this.content = bytes;
    }
}
