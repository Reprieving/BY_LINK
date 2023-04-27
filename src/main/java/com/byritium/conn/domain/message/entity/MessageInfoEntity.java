package com.byritium.conn.domain.message.entity;

import lombok.Getter;

@Getter
public class MessageInfoEntity {
    private long time;
    private String partition;
    private String content;
    private String sender;
    private String receiver;
}
