package com.byritium.conn.apis.model;

import lombok.Data;

@Data
public class CustomMessageHeader {
    private CustomMessageHeader header;
    private CustomMessageBody body;
}
