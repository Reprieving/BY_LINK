package com.byritium.conn.infra.repository;

import com.byritium.conn.domain.connection.repository.ConnectionRepository;
import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ConnectionRepositoryImpl implements ConnectionRepository {

    private static final ChannelGroup globalGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private static final ConcurrentMap<String, ChannelId> channelMap = new ConcurrentHashMap<>();

    @Override
    public Channel findChannelByObjId(String objectId) {
        ChannelId channelId = channelMap.get(objectId);
        return globalGroup.find(channelId);
    }

    @Override
    public void saveConnection(String objectId, Channel channel) {
        globalGroup.add(channel);
        channelMap.put(objectId, channel.id());
    }

    @Override
    public void removeConnection(String objectId) {
        ChannelId channelId = channelMap.get(objectId);
        Channel channel = globalGroup.find(channelId);
        globalGroup.remove(channel);
        channelMap.remove(objectId);
    }
}
