package com.byritium.conn.apis.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class CustomMessageHeader {
    private int crcCode = 0xabef0101;
    private int length;
    private long sessionId;
    private Map<String,Object> attachment = new HashMap<>();

}
