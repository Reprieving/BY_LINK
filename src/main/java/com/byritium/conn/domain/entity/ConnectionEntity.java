package com.byritium.conn.domain.entity;

import com.byritium.conn.domain.repository.ConnectionRepository;
import com.byritium.conn.infra.SpringUtils;
import lombok.Getter;

@Getter
public class ConnectionEntity {
    private String objectId;
    private String channelId;

    private final ConnectionRepository connectionRepository;

    public ConnectionEntity(){
        this.connectionRepository = SpringUtils.getBean(ConnectionRepository.class);
    }

}
