package com.byritium.conn.domain.connection.entity;

import com.byritium.conn.domain.connection.repository.ConnectionRepository;
import com.byritium.conn.infra.SpringUtils;
import io.netty.channel.Channel;
import lombok.Getter;

@Getter
public class ConnectionEntity {
    private String objectId;
    private String channelId;

    private final ConnectionRepository connectionRepository;

    public ConnectionEntity(){
        this.connectionRepository = SpringUtils.getBean(ConnectionRepository.class);
    }

    public void connect(String objectId, Channel channel){
        connectionRepository.saveConnection(objectId,channel);
    }

    public void disconnect(String objectId){
        connectionRepository.removeConnection(objectId);
    }
}
