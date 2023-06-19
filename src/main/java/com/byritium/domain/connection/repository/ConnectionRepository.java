package com.byritium.domain.connection.repository;

import io.netty.channel.Channel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConnectionRepository {
    Channel findChannelByObjId(String objectId);

    void saveConnection(String objectId, Channel channel);

    void removeConnection(String objectId);

    List<Channel> findAllChannel();

}
