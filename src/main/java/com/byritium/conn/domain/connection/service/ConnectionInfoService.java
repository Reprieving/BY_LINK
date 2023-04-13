package com.byritium.conn.domain.connection.service;

import com.byritium.conn.domain.connection.repository.ConnectionRepository;
import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class ConnectionInfoService {

    @Autowired
    ConnectionRepository connectionRepository;


    public void connect(String objectId, Channel channel) {
        connectionRepository.saveConnection(objectId,channel);
    }

    public void disconnect(String objectId) {
        connectionRepository.removeConnection(objectId);
    }

    public void pointMessage(String objectId,String content){
        Channel channel = connectionRepository.findChannelByObjId(objectId);
        channel.writeAndFlush(content);
    }

    public void borderCastMessage(){

    }

}
