package com.byritium.conn.domain.message.entity;

import io.netty.channel.Channel;
import lombok.Getter;

@Getter
public class ConnectionVo {
    private String objectId;
    private String channelId;

}
