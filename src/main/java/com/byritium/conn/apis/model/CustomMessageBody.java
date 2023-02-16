package com.byritium.conn.apis.model;

import lombok.Data;

@Data
public class CustomMessageBody {
    private CustomMessageHeader header;
    private ConnectionInfo body;
}
