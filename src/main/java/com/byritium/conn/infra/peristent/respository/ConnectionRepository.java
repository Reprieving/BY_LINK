package com.byritium.conn.infra.peristent.respository;

import com.byritium.conn.apis.model.CustomMessageBody;
import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ConnectionRepository {
    private static final ChannelGroup globalGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private static final ConcurrentMap<String, ChannelId> channelMap = new ConcurrentHashMap<>();

    public void connect(Channel channel){
        globalGroup.add(channel);
        channelMap.put(channel.id().asShortText(), channel.id());
    }

    public void disconnect(Channel channel){
        globalGroup.remove(channel);
        channelMap.remove(channel.id().asShortText());
    }
}
