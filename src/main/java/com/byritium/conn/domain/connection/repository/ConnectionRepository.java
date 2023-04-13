package com.byritium.conn.domain.connection.repository;

import io.netty.channel.Channel;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnectionRepository {
    Channel findChannelByObjId(String objectId);

    void saveConnection(String objectId, Channel channel);

    void removeConnection(String objectId);
}
