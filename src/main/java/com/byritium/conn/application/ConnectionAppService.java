package com.byritium.conn.application;

import com.byritium.conn.apis.model.CustomMessageBody;
import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class ConnectionAppService {

    private static final ChannelGroup globalGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private static final ConcurrentMap<String, ChannelId> channelMap = new ConcurrentHashMap<>();

//    public static Channel findChannel(String id) {
//        return globalGroup.find(channelMap.get(id));
//    }
//
//    public static void send2All(TextWebSocketFrame tws) {
//        globalGroup.writeAndFlush(tws);
//    }

    public void connect(Channel channel, CustomMessageBody customMessageBody){
        globalGroup.add(channel);
        channelMap.put(channel.id().asShortText(), channel.id());
    }

    public void disconnect(Channel channel){
        globalGroup.remove(channel);
        channelMap.remove(channel.id().asShortText());
    }
}
