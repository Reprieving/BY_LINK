package com.byritium.conn.domain.connection.service;

import com.byritium.conn.domain.connection.repository.ConnectionRepository;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConnectionDomainService {

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
        List<Channel> channelList = connectionRepository.findAllChannel();
    }

}
