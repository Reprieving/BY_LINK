package com.byritium.conn.apis.model;

import lombok.Data;

@Data
public class TcpCustomMessage {
    private int length;
    private byte identify;
    private byte userName;
    private byte password;
    private byte[] content;

    public TcpCustomMessage(int length, byte[] bytes) {
        this.length = length;
        this.content = bytes;
    }
}
