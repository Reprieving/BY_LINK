package com.byritium.conn.domain.connection.entity;

import com.byritium.conn.infra.general.constance.CustomerType;
import com.byritium.conn.infra.general.constance.ProtocolType;
import com.byritium.conn.infra.general.constance.TerminalType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
public class ConnectionRoot {
    private String channelId; //连接ID

    //连接信息
    private ConnectionInfoEntity connectionInfo;

    //会话信息
    private ConnectionSessionEntity connectionSession;
}
