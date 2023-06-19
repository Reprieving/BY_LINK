package com.byritium.domain.connection.entity;

import com.byritium.domain.connection.repository.ConnectionRepository;
import com.byritium.infra.SpringUtils;
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
