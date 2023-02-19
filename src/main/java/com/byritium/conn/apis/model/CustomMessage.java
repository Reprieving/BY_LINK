package com.byritium.conn.apis.model;

import lombok.Data;

@Data
public class CustomMessage {
    private CustomMessageHeader header;
    private ConnectionInfo body;
}
