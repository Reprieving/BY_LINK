package com.byritium.conn.apis.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ConnectionMessage {
    private ConnectionHeader header;
    private ConnectionBody body;
}
