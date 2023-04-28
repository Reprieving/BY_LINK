package com.byritium.conn.domain.connection.entity;

import lombok.Getter;

@Getter
public class Connection {
    private String channelId; //连接ID

    //连接信息
    private ConnectionInfoEntity connectionInfo;

    //会话信息
    private ConnectionSessionEntity connectionSession;
}
