package com.byritium.domain.connection.repository;

import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ConnectionRepository {
    Channel findChannelByObjId(String objectId);

    void saveConnection(String objectId, Channel channel);

    void removeConnection(String objectId);

    List<Channel> findAllChannel();

    void saveAuth(String objectId, Channel channel);

    ChannelId findAuthByChannelId(String channelId);
}
