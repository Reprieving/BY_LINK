package com.byritium.conn.domain.connection.service;

import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.springframework.stereotype.Service;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class ConnectionInfoService {
    private static final ChannelGroup globalGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private static final ConcurrentMap<String, ChannelId> channelMap = new ConcurrentHashMap<>();

    public void connect(String objectId, Channel channel) {
        globalGroup.add(channel);
        channelMap.put(objectId, channel.id());
    }

    public void disconnect(String objectId) {
        ChannelId channelId = channelMap.get(objectId);
        Channel channel = globalGroup.find(channelId);
        globalGroup.remove(channel);
        channelMap.remove(objectId);
    }

    public void pointMessage(){

    }

    public void borderCastMessage(){

    }

}
